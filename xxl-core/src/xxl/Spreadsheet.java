package xxl;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import xxl.content.Content;
import xxl.content.ContentBuilder;
import xxl.exceptions.UnrecognizedEntryException;
import xxl.storage.CutBuffer;
import xxl.storage.SpreadsheetData;
import xxl.visitor.RenderedContentVisitor;
import xxl.visitor.TransferContent;
import xxl.visitor.TransferVisitor;

/**
 * Class representing a spreadsheet.
 */
public class Spreadsheet implements Serializable {

    @Serial
    private static final long serialVersionUID = 202308312359L;


    private SpreadsheetData _sheetData;
    private CutBuffer _cutBuffer;
    private boolean _changed = true;
    private List<User> _users = new ArrayList<>();

    public Spreadsheet() {}

    public Spreadsheet(int rows, int columns) {
        _sheetData = new SpreadsheetData(rows, columns);
        _cutBuffer = new CutBuffer(rows, columns);
    }

    public boolean hasChanged() { return _changed; }

    public void changed(boolean value) { _changed = value; }

    public void addUser(User newUser) { _users.add(newUser); }

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

        _sheetData.insertContents(range, content);
        changed(true);
    }

    public void requestContents(String rangeSpecification, RenderedContentVisitor renderer) throws UnrecognizedEntryException {
        Range range = new Range(rangeSpecification);

        _sheetData.requestContents(range, renderer);
    }

    public void deleteContents(String rangeSpecification) throws UnrecognizedEntryException {
        Range range = new Range(rangeSpecification);

        _sheetData.deleteContents(range);
        changed(true);
    }

    public void copyContents(String rangeSpecification) throws UnrecognizedEntryException {
        Range range = new Range(rangeSpecification);
        TransferVisitor transfer = new TransferContent();

        _sheetData.transferToContents(range, transfer);
        _cutBuffer.transferFromContents(transfer);
        changed(true);
    }

    public void cutContents(String rangeSpecification) throws UnrecognizedEntryException {
        copyContents(rangeSpecification);
        deleteContents(rangeSpecification);
        changed(true);
    }

    public void pasteContents(String rangeSpecification) throws UnrecognizedEntryException {
        Range range = new Range(rangeSpecification);
        TransferVisitor transfer = new TransferContent();

        _cutBuffer.transferToContents(transfer);
        _sheetData.transferFromContents(range, transfer);
        changed(true);
    }

    public void requestCutBufferContent(RenderedContentVisitor renderer) throws UnrecognizedEntryException {
        _cutBuffer.requestContents(renderer);
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
