package xxl;

// FIXME import classes

import java.io.*;
import java.util.Collection;
import java.util.LinkedList;

import xxl.exceptions.UnrecognizedEntryException;

/**
 * Class representing a spreadsheet.
 */
public class Spreadsheet implements Serializable {

    @Serial
    private static final long serialVersionUID = 202308312359L;
    private static final int DIMENSION_ENTRIES = 2;
    // FIXME define attributes
    // FIXME define methods

    private Storage _storage;
    private CutBuffer _cutBuffer = new CutBuffer();
    private boolean _changed = false;

    public Spreadsheet() {
        _storage = new Storage();
    }
    public Spreadsheet(int rows, int columns) {
        _storage = new Storage(rows, columns);
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
            registerDimensions(reader);
            String line;

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split("\\|");
                insertContents(fields[0], fields[1]);
            }
        } catch (FileNotFoundException e) {
            throw new IOException();
        }
    }

    private void registerDimensions(BufferedReader reader) throws IOException, UnrecognizedEntryException {
        int rows = 0, columns = 0;
        String line = null;
        String[] fields;

        for (int i = 0; i < DIMENSION_ENTRIES; i++) {
            try {
                line = reader.readLine();
                fields = line.split("=");

                switch (fields[0]) {
                    case "linhas" -> rows = Integer.parseInt(fields[1]);
                    case "colunas" -> columns = Integer.parseInt(fields[1]);
                    default -> throw new UnrecognizedEntryException(line);
                }
            } catch (NumberFormatException e) {
                throw new UnrecognizedEntryException(line, e);
            }
        }
        if (rows != 0 && columns != 0) _storage = new Storage(rows, columns);
    }

}
