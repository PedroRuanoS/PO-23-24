package xxl.search;

import xxl.Range;
import xxl.content.*;
import xxl.storage.Storage;
import xxl.visitor.ReadContent;

import java.util.List;
import java.util.Map;

public class SearchValue implements SearchPredicate {
    @Override
    public boolean test(String argument, Content sheetCellContent, Storage data) {
        ContentBuilder contentBuilder = new ContentBuilder();
        Content content = contentBuilder.build(argument);
        ReadContent visitor = new ReadContent();
        sheetCellContent.requestContent(visitor, data);
        if (content.isIntegerLiteral() && visitor.readContent().isIntegerLiteral()) {
            IntegerLiteral arg  = new IntegerLiteral(argument);
            return arg.getValue() == visitor.readContent().getValue();
        } else if (content.isStringLiteral() && visitor.readContent().isStringLiteral()) {
            StringLiteral arg = new StringLiteral(argument);
            return arg.getValue().equals(visitor.readContent().getValue());
        }
        return false;
    }

    @Override
    public void sort(List<Map.Entry<Integer, Content>> matchedContent) {
        // doesn't need sorting
    }

}
