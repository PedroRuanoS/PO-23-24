package xxl;

import xxl.content.Content;
import xxl.content.ContentBuilder;
import xxl.content.FunctionContent;
import xxl.exceptions.InvalidFunctionException;
import xxl.exceptions.UnrecognizedEntryException;
import xxl.search.SearchFunction;
import xxl.search.SearchPredicate;
import xxl.search.SearchValue;
import xxl.storage.CutBuffer;
import xxl.storage.SpreadsheetData;
import xxl.visitor.RenderedContentVisitor;
import xxl.visitor.TransferCells;
import xxl.visitor.TransferVisitor;

import java.io.Serial;
import java.io.Serializable;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

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
    public void insertContents(String rangeSpecification, String contentSpecification)
            throws UnrecognizedEntryException, InvalidFunctionException {
        try {
            Range range = checkCreateRange(rangeSpecification);
            ContentBuilder contentBuilder = new ContentBuilder();
            Content content = contentBuilder.build(contentSpecification);

            if (content != null) {
                _sheetData.insertContents(range, content);
                changed(true);
            }
        } catch (NumberFormatException e) { throw new UnrecognizedEntryException(rangeSpecification); }
    }

    public void requestContents(String rangeSpecification, RenderedContentVisitor renderer) throws UnrecognizedEntryException {
        try {
            Range range = checkCreateRange(rangeSpecification);

            _sheetData.renderContents(range, renderer);
        } catch (NumberFormatException e) { throw new UnrecognizedEntryException(rangeSpecification); }
    }

    public void deleteContents(String rangeSpecification) throws UnrecognizedEntryException {
        try {
            Range range = checkCreateRange(rangeSpecification);

            _sheetData.deleteContents(range);
            changed(true);
        } catch (NumberFormatException e) { throw new UnrecognizedEntryException(rangeSpecification); }
    }

    public void copyContents(String rangeSpecification) throws UnrecognizedEntryException {
        try {
            Range range = checkCreateRange(rangeSpecification);
            TransferVisitor transfer = new TransferCells();

            _cutBuffer = new CutBuffer(_rowCount, _columnCount);
            _sheetData.transferCellsTo(range, transfer);
            _cutBuffer.transferCellsFrom(transfer);
            changed(true);
        } catch (NumberFormatException e) { throw new UnrecognizedEntryException(rangeSpecification); }
    }

    public void cutContents(String rangeSpecification) throws UnrecognizedEntryException {
        copyContents(rangeSpecification);
        deleteContents(rangeSpecification);
    }

    public void pasteContents(String rangeSpecification) throws UnrecognizedEntryException {
        try {
            Range range = checkCreateRange(rangeSpecification);
            TransferVisitor transfer = new TransferCells();

            _cutBuffer.transferCellsTo(transfer);
            _sheetData.transferCellsFrom(range, transfer);
            changed(true);
        } catch (NumberFormatException e) { throw new UnrecognizedEntryException(rangeSpecification); }
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
        } catch (InvalidFunctionException e) {
            throw new UnrecognizedEntryException(e.getEntrySpecification());
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

    public Range checkCreateRange(String rangeSpecification)
            throws NumberFormatException, UnrecognizedEntryException {
        if (!rangeSpecification.matches("\\d+;\\d+(?::\\d+;\\d+)?$"))
            throw new UnrecognizedEntryException(rangeSpecification);

        Range range = new Range(rangeSpecification);
        if (!isRangeOk(range))
            throw new UnrecognizedEntryException(rangeSpecification);

        return range;
    }
}
