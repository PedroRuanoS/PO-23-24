package xxl.storage;

import xxl.range.Cell;

import java.util.HashMap;
import java.util.Map;

public class Storage {
    private Map<Integer, Cell> _cells;


    public Storage(int rows, int columns) {
        _cells = new HashMap<>(rows * columns);
    }


}
