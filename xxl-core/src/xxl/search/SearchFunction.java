package xxl.search;

import xxl.content.Content;
import xxl.content.FunctionContent;
import xxl.storage.Storage;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class SearchFunction implements SearchPredicate {
    public boolean test(String argument, Content sheetCellContent, Storage data) {
        if (sheetCellContent.isFunctionContent()) {
            return ((FunctionContent) sheetCellContent).getFunctionName().contains(argument);
        }
        return false;
    }

    @Override
    public void sort(List<Map.Entry<Integer, Content>> matchedContent) {
        Comparator<Map.Entry<Integer, Content>> functionComparator = (firstEntry, secondEntry) -> {
            String firstFunctionName = ((FunctionContent) firstEntry.getValue()).getFunctionName();
            String secondFunctionName = ((FunctionContent) secondEntry.getValue()).getFunctionName();

            int functionNameComparison = firstFunctionName.compareTo(secondFunctionName);

            if (functionNameComparison == 0) {
                return firstEntry.getKey().compareTo(secondEntry.getKey());  // Use cell index for tiebreaker
            }
            return functionNameComparison;
        };

        matchedContent.sort(functionComparator);
    }
}
