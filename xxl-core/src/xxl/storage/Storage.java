package xxl.storage;

import xxl.Cell;
import xxl.range.Range;
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

    public void insertContent(Range range, String contentSpecification) {
        for (Integer cell_index: range.getIndexedCells(_rows))
            _cells.put(cell_index, new Cell(contentSpecification));
    }

    public void showContent(Range range) {
        // FIXME IMPLEMENT THIS
    }
}
