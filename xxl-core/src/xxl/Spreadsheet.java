package xxl;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import xxl.content.Content;
import xxl.content.ContentBuilder;
import xxl.exceptions.UnrecognizedEntryException;
import xxl.search.SearchFunction;
import xxl.search.SearchPredicate;
import xxl.search.SearchValue;
import xxl.storage.CutBuffer;
import xxl.storage.SpreadsheetData;
import xxl.visitor.RenderedContentVisitor;
import xxl.visitor.TransferCells;
import xxl.visitor.TransferVisitor;

/**
 * Class representing a spreadsheet.
 */
public class Spreadsheet implements Serializable {

    @Serial
    private static final long serialVersionUID = 202308312359L;

    private int _rowCount;
    private int _columnCount;
    private SpreadsheetData _sheetData;
    private CutBuffer _cutBuffer;
    private boolean _changed = true;
    private List<User> _users = new ArrayList<>();

    public Spreadsheet() {}

    public Spreadsheet(int rows, int columns) {
        _rowCount = rows;
        _columnCount = columns;
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
        if (!isRangeOk(range)) throw new UnrecognizedEntryException(rangeSpecification);

        ContentBuilder contentBuilder = new ContentBuilder();
        Content content = contentBuilder.build(contentSpecification);

        _sheetData.insertContents(range, content);
        changed(true);
    }

    public void requestContents(String rangeSpecification, RenderedContentVisitor renderer) throws UnrecognizedEntryException {
        Range range = new Range(rangeSpecification);
        if (!isRangeOk(range)) throw new UnrecognizedEntryException(rangeSpecification);

        _sheetData.renderContents(range, renderer);
    }

    public void deleteContents(String rangeSpecification) throws UnrecognizedEntryException {
        Range range = new Range(rangeSpecification);
        if (!isRangeOk(range)) throw new UnrecognizedEntryException(rangeSpecification);

        _sheetData.deleteContents(range);
        changed(true);
    }

    public void copyContents(String rangeSpecification) throws UnrecognizedEntryException {
        Range range = new Range(rangeSpecification);
        if (!isRangeOk(range)) throw new UnrecognizedEntryException(rangeSpecification);
        TransferVisitor transfer = new TransferCells();

        _cutBuffer = new CutBuffer(_rowCount, _columnCount);
        _sheetData.transferCellsTo(range, transfer);
        _cutBuffer.transferCellsFrom(transfer);
        changed(true);
    }

    public void cutContents(String rangeSpecification) throws UnrecognizedEntryException {
        copyContents(rangeSpecification);
        deleteContents(rangeSpecification);
        changed(true);
    }

    public void pasteContents(String rangeSpecification) throws UnrecognizedEntryException {
        Range range = new Range(rangeSpecification);
        if (!isRangeOk(range)) throw new UnrecognizedEntryException(rangeSpecification);
        TransferVisitor transfer = new TransferCells();

        _cutBuffer.transferCellsTo(transfer);
        _sheetData.transferCellsFrom(range, transfer);
        changed(true);
    }

    public void requestCutBufferContent(RenderedContentVisitor renderer) {
        _cutBuffer.renderContents(renderer);
    }

    public void searchValue(String value, RenderedContentVisitor renderer) {
        SearchPredicate valuePredicate = new SearchValue();
        _sheetData.search(valuePredicate, value, renderer);
    }

    public void searchFunction(String function, RenderedContentVisitor renderer) {
        SearchPredicate functionPredicate = new SearchFunction();
        _sheetData.search(functionPredicate, function, renderer);
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

        _rowCount = rows;
        _columnCount = columns;
        _sheetData = new SpreadsheetData(rows, columns);
        _cutBuffer = new CutBuffer(rows, columns);
    }

    public boolean isRangeOk(Range range) {
        return ((range.isVertical() || range.isHorizontal())) &&
                (range.getFirstRow() > 0 && range.getFirstColumn() > 0) &&
                (range.getLastRow() <= _rowCount && range.getLastColumn() <= _columnCount);
    }
}
