package xxl.storage;

import xxl.Cell;
import xxl.Range;
import xxl.exceptions.IllegalEntryException;

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

    private int getCellIndex(int row, int column) {
        return (row - 1) * _rows + (column - 1);
    }
    public void insertContent(Range range, String contentSpecification) {
        int firstCellIndex = getCellIndex(range.getFirstRow(), range.getFirstColumn());
        int lastCellIndex = getCellIndex(range.getLastRow(), range.getLastColumn());

        Cell cell = new Cell(contentSpecification);

        for (int ix = firstCellIndex; ix <= lastCellIndex; ix++) {
            _cells.put(ix, cell);
        }
    }

    public void showContent(Range range) {
        int firstCellIndex = getCellIndex(range.getFirstRow(), range.getFirstColumn());
        int lastCellIndex = getCellIndex(range.getLastRow(), range.getLastColumn());

        //
    }
}
