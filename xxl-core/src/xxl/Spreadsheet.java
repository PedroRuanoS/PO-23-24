package xxl;

// FIXME import classes

import java.io.*;

import xxl.exceptions.IllegalEntryException;
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
    private boolean _changed;

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
        Range range = new Range();
        try {
            range.processRange(rangeSpecification);
        } catch (NumberFormatException e) { throw new UnrecognizedEntryException(rangeSpecification + "|" + contentSpecification); }

        _storage.insertContent(range, contentSpecification);
        _changed = true;
    }

    public void showContents(String rangeSpecification) throws UnrecognizedEntryException {
        Range range = new Range();
        try {
            range.processRange(rangeSpecification);
        } catch (NumberFormatException e) { throw new UnrecognizedEntryException(rangeSpecification); }

        _storage.showContent(range);
    }

    public void importFile(String filename)
            throws IOException, UnrecognizedEntryException, NumberFormatException, IllegalEntryException {
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

    private void registerDimensions(BufferedReader reader) // FIXME Make this better looking and add exceptions?
            throws IOException, NumberFormatException /*,IllegalEntryException*/ {
        String[] line_rows = reader.readLine().split("=");
        String[] line_columns = reader.readLine().split("=");

        int rows = Integer.parseInt(line_rows[1]);
        int columns = Integer.parseInt(line_columns[1]);

        _storage = new Storage(rows, columns);
        _cutBuffer = new CutBuffer(rows, columns);
    }

}
