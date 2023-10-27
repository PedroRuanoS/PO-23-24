package xxl.search;

import xxl.content.Content;
import xxl.content.FunctionContent;
import xxl.storage.Storage;

import java.util.*;
import java.util.stream.Stream;

public class SearchFunction implements SearchPredicate {
    public boolean test(String argument, Content sheetCellContent, Storage data) {
        if (sheetCellContent.isFunctionContent()) {
            return ((FunctionContent) sheetCellContent).getFunctionName().contains(argument);
        }
        return false;
    }

    @Override
    public Map<Integer, Content> sort(Map<Integer, Content> matchedContent) {
        return null;
    }
}
