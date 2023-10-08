package xxl;

// FIXME import classes

import java.io.*;
import java.util.*;

import xxl.exceptions.UnrecognizedEntryException;

/**
 * Class representing a spreadsheet.
 */
public class Spreadsheet implements Serializable {

    @Serial
    private static final long serialVersionUID = 202308312359L;

    private Map<Integer, Cell> _cells;

    private int _rowCount;
    private int _columnCount;

    // FIXME define attributes
    // FIXME define contructor(s)
    // FIXME define methods

    public Spreadsheet() {
        _cells = new HashMap<>();
    }

    public Spreadsheet(int rows, int columns) {
        _rowCount = rows;
        _columnCount = columns;
        _cells = new HashMap<>(rows * columns);
    }
    /**
     * Insert specified content in specified range.
     *
     * @param rangeSpecification
     * @param contentSpecification
     */
    public void insertContents(String rangeSpecification, String contentSpecification) throws UnrecognizedEntryException /* FIXME maybe add exceptions */ {
        //FIXME implement method
    }

    public void copyContents(String rangeSpecification) throws UnrecognizedEntryException {
    }

    public void pasteContents(String rangeSpecification) throws UnrecognizedEntryException {

    }

    public void cutContents(String rangeSpecification) throws UnrecognizedEntryException {

    }

    public void deleteContents(String rangeSpecification) throws UnrecognizedEntryException {

    }

    public Collection<String/* temporary*/> showCutBuffer() {
        return new LinkedList<>();
    }

    public Collection<String/* temporary */> showContents(String rangeSpecification) throws UnrecognizedEntryException{
        return new LinkedList<>();
    }

    public Collection<String/* temporary */> findFunction(String functionName) throws UnrecognizedEntryException {
        return new LinkedList<>();
    }

    public Collection<String/* temporary */> findValue(String functionName) throws UnrecognizedEntryException {
        return new LinkedList<>();
    }

    public void importFile(String filename) throws IOException, UnrecognizedEntryException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int line_index = 0;
            while ((line = reader.readLine()) != null) {
                if (line_index < 2)
                    importDimensions(line.split("="));
                else
                    importCell(line.split("\\|"));
                line_index++;
            }
        }
    }

    private void importDimensions(String[] entry) throws UnrecognizedEntryException{
        if (entry.length != 2) throw new UnrecognizedEntryException(String.join("=", entry));

        try {
            switch (entry[0]) {
                case "linhas" -> _rowCount = Integer.parseInt(entry[1]);
                case "colunas" -> _columnCount = Integer.parseInt(entry[1]);
                default -> throw new UnrecognizedEntryException(entry[0]);
            }
        } catch ( NumberFormatException e) {
            throw new UnrecognizedEntryException(entry[1], e);
        }
    }

    private void importCell(String[] entry) throws UnrecognizedEntryException {
        if (entry.length != 2) throw new UnrecognizedEntryException(String.join("\\|", entry));

        insertContents(entry[0], entry[1]);
    }


}
