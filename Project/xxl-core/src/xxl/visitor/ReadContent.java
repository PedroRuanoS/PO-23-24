package xxl.visitor;


import xxl.Cell;
import xxl.content.*;
import xxl.storage.Storage;

import java.util.ArrayList;
import java.util.List;

public class ReadContent implements ContentVisitor {
    private Literal<?> _literalContent;
    private boolean _gotUpdated;

    @Override
    public void visitInteger(IntegerLiteral integerContent) { _literalContent = integerContent; }

    @Override
    public void visitString(StringLiteral stringContent) {
        _literalContent = stringContent;
    }

    @Override
    public void visitReference(ReferencedContent referenceContent, Storage data) {
        if (referenceContent.isStatic()) {
            _literalContent = referenceContent.getValue();
        }
        else {
            data.readContent(referenceContent.getCellAddress(), this);
            referenceContent.setValue(_literalContent);
        }
    }

    @Override
    public void visitFunction(FunctionContent functionContent, Storage data) {
        if (functionContent.isStatic() || (functionContent.isUpdated() && !functionContent.isBinaryFunction())) {
            _literalContent = functionContent.getResult();
        }
        else {
            if (functionContent.isBinaryFunction()) {
                ReadContent firstReader = new ReadContent();
                ReadContent secondReader = new ReadContent();

                Content firstArgument = functionContent.getFirstArgument();
                Content secondArgument = functionContent.getSecondArgument();

                if (firstArgument != null)
                    firstArgument.requestContent(firstReader, data);
                if (secondArgument != null)
                    secondArgument.requestContent(secondReader, data);

                _literalContent = functionContent.executeBinaryFunction(
                        firstReader.readContent(), secondReader.readContent());
            } else {
                List<Literal<?>> listedRangeArgs = new ArrayList<>();
                ReadContent reader = new ReadContent();
                for (int[] address: functionContent.getRangeArgument().getRange()) {

                    Cell currentCell = data.getCells().get(data.computeCellIndex(address));
                    if (currentCell == null || currentCell.getContent() == null) {
                        listedRangeArgs.add(null);
                        continue;
                    }
                    Content currentContent = data.getCells().get(data.computeCellIndex(address)).getContent();
                    currentContent.requestContent(reader, data);
                    listedRangeArgs.add(reader.readContent());
                }
                _literalContent = functionContent.executeRangeFunction(listedRangeArgs);
            }
            functionContent.setResult(_literalContent);
            functionContent.updated();
            _gotUpdated = true;
        }
    }

    @Override
    public boolean gotUpdated() {
        return _gotUpdated;
    }

    public Literal<?> readContent() { return _literalContent; }

}
