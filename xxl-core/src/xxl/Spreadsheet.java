package xxl;

// FIXME import classes

import java.io.Serial;
import java.io.Serializable;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import xxl.content.Content;
import xxl.content.ContentBuilder;
import xxl.exceptions.UnrecognizedEntryException;
import xxl.storage.CutBuffer;
import xxl.storage.SpreadsheetData;
import xxl.storage.Storage;
import xxl.visitor.ContentVisitor;

/**
 * Class representing a spreadsheet.
 */
public class Spreadsheet implements Serializable {

    @Serial
    private static final long serialVersionUID = 202308312359L;


    private SpreadsheetData _sheetData;
    private CutBuffer _cutBuffer;
    private boolean _changed = true;

    public Spreadsheet() {}

    public Spreadsheet(int rows, int columns) {
        _sheetData = new SpreadsheetData(rows, columns);
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
        Range range = new Range(rangeSpecification);

        ContentBuilder contentBuilder = new ContentBuilder();
        Content content = contentBuilder.build(contentSpecification);

        _sheetData.insert(range, content);
        _changed = true;
    }

    public void requestContents(String rangeSpecfication, ContentVisitor visitor) throws UnrecognizedEntryException {
        Range range = new Range(rangeSpecfication);
        _sheetData.requestContents(range, visitor);
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

        _sheetData = new SpreadsheetData(rows, columns);
        _cutBuffer = new CutBuffer(rows, columns);
    }
}
