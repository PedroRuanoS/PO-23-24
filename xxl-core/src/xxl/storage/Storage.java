package xxl.storage;

import xxl.Cell;
import xxl.content.*;
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

    public Map<Integer, Cell> getCells() { return _cells; }

    public void insertContent(Range range, String contentSpecification) throws UnrecognizedEntryException, IllegalEntryException {
        for (int cell_index: range.getIndexedCells(_columns)) {
            if (cell_index + 1 > _size || cell_index + 1 < 0) {
                throw new IllegalEntryException(currentCell(cell_index));
            }

            Cell new_cell = new Cell();
            new_cell.setContent(createContent(contentSpecification));
            _cells.put(cell_index, new_cell);
        }
    }

    public String showContent(Range range) throws IllegalEntryException {
        String content = "";
        for (int cell_index: range.getIndexedCells(_columns)) {
            if (cell_index + 1 > _size || cell_index + 1 < 0)                   // check if index is out of bounds
                throw new IllegalEntryException(currentCell(cell_index));

            Cell cell;
            String default_output = "\n" + currentCell(cell_index) + "|";       // Common part of the formatting structure

            if ((cell = _cells.get(cell_index)) != null) {
                if (cell.toString().matches("^'.*") ||
                        cell.toString().matches("^-?\\d+$")) {           // Match Integers
                    content += default_output + cell.toString();
                }
                else if (cell.toString().matches("^=[1-9]+;[1-9]+$")) {   // Match References
                    Cell reference = _cells.get(cell.getContent().getIndex());  // check if reference exists
                    if (reference != null)
                        content += default_output + cell.getContent().intValue() + cell.toString();
                    else
                        content += default_output + "#VALUE" + cell.getContent().toString();
                }
                else if (cell.toString().matches("^=.+")) {              // Match Functions
                    FunctionContent function = (FunctionContent) cell.getContent();
                    content += default_output + function.executeOperation() + cell.toString();
                }
            }
            else
                content += "\n" + currentCell(cell_index) + "|";
        }
        return content.substring(1);                                 // remove unwanted first \n
    }
    
    public String currentCell(int cell_index) {
        int row = (cell_index / _columns) + 1;
        int column = (cell_index % _columns) + 1;

        return String.format("%d;%d", row, column);
    }

    public Content createContent(String contentSpecification) throws UnrecognizedEntryException {
        if (contentSpecification.matches("^'.*"))                 // REGEX: strings which start with
            return new StringContent(contentSpecification);
        if (contentSpecification.matches("^-?\\d+$"))             // REGEX: Integers
            return new IntegerContent(contentSpecification);
        if (contentSpecification.matches("^=[1-9]+;[1-9]+$"))     // REGEX: Reference to other cells
            return new ReferencedContent(contentSpecification, _columns, this);
        if (contentSpecification.matches("^=.+")) {               // REGEX: Find functions
            return new FunctionContent(contentSpecification, _columns, this);
        }
        else {
            throw new UnrecognizedEntryException(contentSpecification);
        }
    }

}

