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
        _cells = new HashMap<>(_rowCount * _columnCount);
    }

    public void insert(Range range, Content content) {
        for (int[] address : range.getRange()) {
            Cell newCell = new Cell(content);
            _cells.put(computeCellIndex(address), newCell);
        }
    }

    public void extract(Range range) {

    }

    private int computeCellIndex(int[] address) {
        return (address[0] - 1) * _columnCount + (address[1] - 1);  // address -> [row, column]
    }

}
