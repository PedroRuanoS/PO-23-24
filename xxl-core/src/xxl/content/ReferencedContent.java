package xxl.content;

import java.io.Serializable;
import java.net.Inet4Address;
import java.util.Iterator;
import java.util.Map;

import xxl.Spreadsheet;
import xxl.exceptions.UnrecognizedEntryException;
import xxl.range.Range;
import xxl.Cell;
import xxl.storage.Storage;

public class ReferencedContent extends Content implements Serializable {
    private String _referenced_cell;
    private int _referenced_index;
    private Storage _storage;

    public ReferencedContent(String content, int max_columns, Storage storage) throws UnrecognizedEntryException {
        _storage = storage;
        _referenced_cell = content.substring(1);

        try {
            Range range = new Range().createRange(_referenced_cell);
            range.processRange();

            _referenced_index = range.getIndexedCells(max_columns).get(0);
        } catch (NumberFormatException e) {
            throw new UnrecognizedEntryException(content);
        }
    }

    @Override
    public String toString() {
        return "=" + _referenced_cell;
    }


    @Override
    public int intValue() {
        return _storage.getCells().get(_referenced_index).getContent().intValue();
    }

    public int getIndex() { return _referenced_index; }

}
