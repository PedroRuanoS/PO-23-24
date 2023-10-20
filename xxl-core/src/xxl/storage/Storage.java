package xxl.storage;

import xxl.Cell;
import xxl.content.Content;
import xxl.Range;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Storage implements Serializable {
    private int _rowCount;
    private int _columnCount;
    private Map<Integer, Cell> _cells;

    public Storage(int rowCount, int columnCount) {
        _rowCount = rowCount;
        _columnCount = columnCount;
        _cells = new HashMap<>(rowCount * columnCount);
    }

    public int getRowCount() { return _rowCount; }

    public int getColumnCount() { return _columnCount;}

    public Map<Integer, Cell> getCells() { return _cells; }
}
