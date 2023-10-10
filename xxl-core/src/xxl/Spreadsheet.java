package xxl;

// FIXME import classes

import java.io.*;
import java.util.Collection;
import java.util.LinkedList;

import xxl.exceptions.UnrecognizedEntryException;
import xxl.storage.CutBuffer;
import xxl.storage.Storage;

/**
 * Class representing a spreadsheet.
 */
public class Spreadsheet implements Serializable {

    @Serial
    private static final long serialVersionUID = 202308312359L;


    private Storage _storage;
    private CutBuffer _cutBuffer;
    private boolean _changed = false;

    public Spreadsheet() {
    }

    public Spreadsheet(int rows, int columns) {
        _storage = new Storage(rows, columns);
        _cutBuffer = new CutBuffer(rows, columns);
    }

    /**
     * Insert specified content in specified range.
     *
     * @param rangeSpecification
     * @param contentSpecification
     */
    public void insertContents(String rangeSpecification, String contentSpecification) throws UnrecognizedEntryException {
        Range range = new Range(rangeSpecification);

    }


    public void importFile(String filename)
            throws IOException, UnrecognizedEntryException, NumberFormatException {
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

    private void registerDimensions(BufferedReader reader)
            throws IOException, UnrecognizedEntryException, NumberFormatException {
        String line_rows = reader.readLine();
        String line_columns = reader.readLine();

        int rows = Integer.parseInt(line_rows);
        int columns = Integer.parseInt(line_columns);

        if (rows <= 0) throw new UnrecognizedEntryException(line_rows);
        if (columns <= 0) throw new UnrecognizedEntryException(line_columns);

        _storage = new Storage(rows, columns);
        _cutBuffer = new CutBuffer(rows, columns);
    }

}
