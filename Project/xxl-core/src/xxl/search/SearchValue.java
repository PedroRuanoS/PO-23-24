package xxl.search;


import xxl.content.Content;
import xxl.content.ContentBuilder;
import xxl.content.IntegerLiteral;
import xxl.content.StringLiteral;
import xxl.exceptions.InvalidFunctionException;
import xxl.storage.Storage;
import xxl.visitor.ReadContent;

import java.util.List;
import java.util.Map;

public class SearchValue implements SearchPredicate {
    @Override
    public boolean test(String argument, Content sheetCellContent, Storage data) {
        ContentBuilder contentBuilder = new ContentBuilder();
        try {
            Content content = contentBuilder.build(argument);
            ReadContent visitor = new ReadContent();
            sheetCellContent.requestContent(visitor, data);

            if (content.isIntegerLiteral() && visitor.readContent().isIntegerLiteral()) {
                IntegerLiteral literalArgument  = new IntegerLiteral(argument);
                return literalArgument.getValue() == visitor.readContent().getValue();
            } else if (content.isStringLiteral() && visitor.readContent().isStringLiteral()) {
                StringLiteral literalArgument = new StringLiteral(argument);
                return literalArgument.getValue().equals(visitor.readContent().getValue());
            }
        } catch (InvalidFunctionException ignored) {}   // ignore exception since its not useful here
        return false;
    }

    @Override
    public void sort(List<Map.Entry<Integer, Content>> matchedContent) {
        // doesn't need sorting
    }

}
