package xxl.content;

import java.io.Serializable;
import java.net.Inet4Address;
import java.util.Iterator;
import java.util.Map;

import xxl.Spreadsheet;
import xxl.range.Range;
import xxl.Cell;

public class ReferencedContent extends Content implements Serializable {
    private String _referenced_cell;
    private int _referenced_index;
    private Map<Integer, Cell> _cells;

    public ReferencedContent(String content, int max_columns, Map<Integer, Cell> cells) {
        _cells = cells;
        _referenced_cell = content.substring(1);

        Range range = new Range().createRange(_referenced_cell);
        range.processRange();

        _referenced_index = range.getIndexedCells(max_columns).get(0);
    }

    @Override
    public String toString() {
        return "=" + _referenced_cell;
    }

    @Override
    public String stringValue() {
        return _referenced_cell;
    }

    @Override
    public int intValue() {
        return _cells.get(_referenced_index).getContent().intValue();
    }

    @Override
    public Literal value() {
        return null;
    }
}
