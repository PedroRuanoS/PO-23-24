package xxl.search;

import xxl.content.Content;
import xxl.content.FunctionContent;
import xxl.storage.Storage;

import java.util.*;

public class SearchFunction implements SearchPredicate {
    public boolean test(String argument, Content sheetCellContent, Storage data) {
        if (sheetCellContent.isFunctionContent()) {
            return ((FunctionContent) sheetCellContent).getFunctionName().contains(argument);
        }
        return false;
    }

    public List<Map.Entry<Integer, Content>> sort(Map<Integer, Content> contents) {
        List<Map.Entry<Integer, Content>> contentList = new ArrayList<>(contents.entrySet());

        Comparator<Map.Entry<Integer, Content>> contentNameComparator = Comparator.comparing(
                entry -> ((FunctionContent) entry.getValue()).getFunctionName()
        );

        contentList.sort(contentNameComparator);
        return contentList;
    }
}
