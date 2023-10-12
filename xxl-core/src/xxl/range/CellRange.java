package xxl.range;

import java.util.LinkedList;
import java.util.List;

public class CellRange extends Range{
    private String _entry;
    private int _row;
    private int _column;

    public CellRange(String entry) {
        _entry = entry;
    }

    public void processRange() throws NumberFormatException {
        String[] position = _entry.split(";");

        _row = Integer.parseInt(position[0]);
        _column = Integer.parseInt(position[1]);
    }

    public List<Integer> getIndexedCells(int max_columns) {
        List<Integer> cells = new LinkedList<>();
        cells.add(computeIndex(max_columns, _row, _column));
        return cells;
    }
}
