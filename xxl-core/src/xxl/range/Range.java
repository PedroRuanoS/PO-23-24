package xxl.range;


import java.util.LinkedList;
import java.util.List;

public class Range {
    public Range createRange(String range) {
        String[] entry = range.split(":");

        if (entry.length > 1)
            return new IntervalRange(entry[0], entry[1]);
        return new CellRange(entry[0]);
    }

    public int computeIndex(int max_columns, int row, int column) {
        return (row - 1) * max_columns + (column - 1);
    }

    public void processRange() {}

    public List<Integer> getIndexedCells(int max_columns) {
        return new LinkedList<>();
    }
}
