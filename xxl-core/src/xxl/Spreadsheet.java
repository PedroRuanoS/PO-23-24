package xxl;

// FIXME import classes

import java.io.*;

import xxl.exceptions.IllegalEntryException;
import xxl.exceptions.UnrecognizedEntryException;
import xxl.storage.CutBuffer;
import xxl.storage.Storage;
import xxl.range.Range;

/**
 * Class representing a spreadsheet.
 */
public class Spreadsheet implements Serializable {

    @Serial
    private static final long serialVersionUID = 202308312359L;


    private Storage _storage;
    private CutBuffer _cutBuffer;
    private boolean _changed = true;

    public Spreadsheet() {
    }

    public Spreadsheet(int rows, int columns) {
        _storage = new Storage(rows, columns);
        _cutBuffer = new CutBuffer(rows, columns);
    }

    public boolean hasChanged() { return _changed; }

    public void setChanged(boolean changed) { _changed = changed; }
    /**
     * Insert specified content in specified range.
     *
     * @param rangeSpecification
     * @param contentSpecification
     */
    public void insertContents(String rangeSpecification, String contentSpecification) throws UnrecognizedEntryException {
        Range range = new Range().createRange(rangeSpecification);
        try {
            range.processRange();
            _storage.insertContent(range, contentSpecification);
            _changed = true;
        } catch (NumberFormatException | IllegalEntryException e) { throw new UnrecognizedEntryException(rangeSpecification + "|" + contentSpecification); }
    }
    
    public String showContents(String rangeSpecification) throws UnrecognizedEntryException {
        Range range = new Range().createRange(rangeSpecification);
        try {
            range.processRange();
            return _storage.showContent(range);
        } catch (NumberFormatException | IllegalEntryException e) { throw new UnrecognizedEntryException(rangeSpecification); }
    }

    public void importFile(String filename)
            throws IOException, UnrecognizedEntryException, NumberFormatException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            registerDimensions(reader);

            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split("\\|");
                if (fields.length < 2)
                    insertContents(fields[0], "");
                else
                    insertContents(fields[0], fields[1]);
            }
        } catch (FileNotFoundException e) {
            throw new IOException();
        }
    }

    private void registerDimensions(BufferedReader reader) throws IOException, NumberFormatException {
        String[] line_rows = reader.readLine().split("=");
        String[] line_columns = reader.readLine().split("=");

        int rows = Integer.parseInt(line_rows[1]);
        int columns = Integer.parseInt(line_columns[1]);

        _storage = new Storage(rows, columns);
        _cutBuffer = new CutBuffer(rows, columns);
    }
}
