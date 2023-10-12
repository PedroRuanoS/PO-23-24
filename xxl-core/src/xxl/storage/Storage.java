package xxl.storage;

import xxl.Cell;
import xxl.exceptions.IllegalEntryException;
import xxl.exceptions.UnrecognizedEntryException;
import xxl.range.Range;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Storage implements Serializable {
    private int _rows;
    private int _columns;
    private int _size;

    private Map<Integer, Cell> _cells;

    public Storage(int rows, int columns) {
        _rows = rows;
        _columns = columns;
        _size = rows * columns;
        _cells = new HashMap<>(_size);
    }

    public void insertContent(Range range, String contentSpecification) throws UnrecognizedEntryException, IllegalEntryException {
        for (int cell_index: range.getIndexedCells(_columns)) {
            if (cell_index + 1 > _size || cell_index + 1 < 0) {
                throw new IllegalEntryException(currentCell(cell_index));
            }
            Cell new_cell = new Cell();
            new_cell.createContent(contentSpecification);
            _cells.put(cell_index, new_cell);
        }
    }

    public String showContent(Range range) throws IllegalEntryException {  // void??
        String content = "";
        for (int cell_index: range.getIndexedCells(_columns)) {
            if (cell_index + 1 > _size || cell_index + 1 < 0)
                throw new IllegalEntryException(currentCell(cell_index));

            Cell cell;
            if ((cell = _cells.get(cell_index)) != null)
                content += currentCell(cell_index) + "|" + cell.toString();
            else
                content += currentCell(cell_index) + "|";
        }
        return content;
    }
    
    public String currentCell(int cell_index) {
        int row = (cell_index / _columns) + 1;  // This will do integer division which is what we want
        int column = (cell_index % _columns) + 1;

        return String.format("%d;%d", row, column);
    }
}

