package xxl.search;


import xxl.content.Content;
import xxl.content.IntegerLiteral;
import xxl.storage.Storage;
import xxl.visitor.ReadContent;

import java.util.List;
import java.util.Map;

public class SearchValue implements SearchPredicate {
    @Override
    public boolean test(String argument, Content sheetCellContent, Storage data) {
        IntegerLiteral arg = new IntegerLiteral(argument);
        ReadContent visitor2 = new ReadContent();
        sheetCellContent.requestContent(visitor2, data);
        if (visitor2.readContent().isIntegerLiteral())
            return arg.getValue() == visitor2.readContent().getValue();
        return false;
    }

    @Override
    public void sort(List<Map.Entry<Integer, Content>> matchedContent) {
        // doesn't need sorting
    }
}
