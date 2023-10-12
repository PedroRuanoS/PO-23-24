package xxl.range;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class IntervalRange extends Range {
    private String _first_entry;
    private String _last_entry;
    private int[] _rows = new int[2];
    private int[] _columns = new int[2];

    public IntervalRange(String first_entry, String last_entry) {
        _first_entry = first_entry;
        _last_entry = last_entry;
    }

    public void processRange() throws NumberFormatException {
        String[] first_position = _first_entry.split(";");
        String[] last_position = _last_entry.split(";");

        _rows[0] = Integer.parseInt(first_position[0]);
        _rows[1] = Integer.parseInt(last_position[0]);

        _columns[0] = Integer.parseInt(first_position[1]);
        _columns[1] = Integer.parseInt(last_position[1]);

        Arrays.sort(_rows);
        Arrays.sort(_columns);
    }

    public List<Integer> getIndexedCells(int max_columns) {
        List<Integer> cells = new LinkedList<>();

        for (int row_i = _rows[0]; row_i <= _rows[1]; row_i++)
            for (int column_i = _columns[0]; column_i <= _columns[1]; column_i++)
                cells.add(computeIndex(max_columns, row_i, column_i));

        return cells;
    }
}
