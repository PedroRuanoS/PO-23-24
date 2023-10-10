package xxl.storage;

import xxl.Cell;

import java.util.HashMap;
import java.util.Map;

public class Storage {
    private int _rows;
    private int _columns;

    private Map<Integer, Cell> _cells;

    public Storage(int rows, int columns) {
        _rows = rows;
        _columns = columns;
        _cells = new HashMap<>(rows * columns);
    }

    public int[] getStorageSize() {
        int[] size = new Int[2]
    }

}
