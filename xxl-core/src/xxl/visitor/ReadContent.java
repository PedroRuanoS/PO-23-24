package xxl.visitor;

import xxl.Cell;
import xxl.content.*;
import xxl.storage.Storage;

public class ReadContent implements ContentVisitor {
    private Literal<?> _literalContent;

    @Override
    public void visitInteger(IntegerLiteral integerContent) {
        _literalContent = integerContent;

    }

    @Override
    public void visitString(StringLiteral stringContent) {
        _literalContent = stringContent;
    }

    @Override
    public void visitReference(ReferencedContent referenceContent, Storage data) {
        if (referenceContent.isStatic())
            _literalContent = referenceContent.getValue();
        else {
            data.readContent(referenceContent.getCellAddress(), this);
            referenceContent.setValue(_literalContent);
        }
    }

    @Override
    public void visitFunction(FunctionContent functionContent, Storage data) {
    }

    public Literal<?> readContent() { return _literalContent; }
}
