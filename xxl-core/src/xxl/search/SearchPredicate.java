package xxl.search;

import xxl.content.Content;
import xxl.content.IntegerLiteral;
import xxl.storage.Storage;

import java.util.List;
import java.util.Map;

public interface SearchPredicate {
    public boolean test(String argument, Content sheetContent, Storage data);
    public List<Map.Entry<Integer, Content>> sort(Map<Integer, Content> contents);
}
