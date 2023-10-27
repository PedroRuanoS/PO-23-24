package xxl.search;

import xxl.content.Content;
import xxl.storage.Storage;

import java.util.List;
import java.util.Map;

public interface SearchPredicate {
    public boolean test(String argument, Content sheetContent, Storage data);
    public void sort(List<Map.Entry<Integer, Content>> matchedContent);
}
