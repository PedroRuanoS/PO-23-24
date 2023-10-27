package xxl.storage;

import xxl.Cell;
import xxl.content.Content;
import xxl.Range;
import xxl.content.ContentBuilder;
import xxl.content.FunctionContent;
import xxl.content.Literal;
import xxl.search.SearchPredicate;
import xxl.visitor.ContentVisitor;
import xxl.visitor.ReadContent;
import xxl.visitor.RenderedContentVisitor;

import java.io.Serializable;
import java.util.*;

public abstract class Storage implements Serializable {
    private int _rowCount;
    private int _columnCount;
    private Map<Integer, Cell> _cells;

    public Storage(int rowCount, int columnCount) {
        _rowCount = rowCount;
        _columnCount = columnCount;
        _cells = new HashMap<>(rowCount * columnCount);
    }

    public void insertContents(Range range, Content content) {
        for (int[] address : range.getRange()) {
            putContent(content, computeCellIndex(address));
        }
    }

    public void putContent(Content content, int address) {
        Cell currentCell = _cells.get(address);

        if (currentCell == null) {
            currentCell = new Cell(content);
            _cells.put(address, currentCell);
        } else {
            currentCell.setContent(content);
        }
    }

    public void readContent(Range range, ContentVisitor reader) {
        // The range passed to readContent is always a single Cell
        int address = computeCellIndex(range.getRange().get(0));

        Cell currentCell = _cells.get(address);
        boolean empty = (currentCell == null);

        if (!empty)
            currentCell.requestContent(reader, this);
    }

    public void deleteContents(Range range) {
        for (int[] address: range.getRange()) {
            _cells.remove(computeCellIndex(address));
        }
    }

    public void search(SearchPredicate predicate, String argument, RenderedContentVisitor renderer) {
        Map<Integer, Content> matchingContents = new HashMap<>();
        for (Map.Entry<Integer, Cell> entry : _cells.entrySet()) {
            if (entry.getValue() != null) {
                if (predicate.test(argument, entry.getValue().getContent(), this)) {
                    matchingContents.put(entry.getKey(), entry.getValue().getContent());
                }
            }
        }
        List<Map.Entry<Integer, Content>> contentList = predicate.sort(matchingContents);
        for (Map.Entry<Integer, Content> entry : contentList) {
            renderer.renderAddress(revertCellIndex(entry.getKey()), false);
            entry.getValue().requestContent(renderer, this);
        }
    }

    public void render(Cell currentCell, int[] address, RenderedContentVisitor renderer) {
        boolean empty = (currentCell == null || currentCell.getContent() == null);

        renderer.renderAddress(address, empty);

        if (!empty)
            currentCell.requestContent(renderer, this);
    }

    public void renderContents(Range range, RenderedContentVisitor renderer) {}

    public void renderContents(RenderedContentVisitor renderer) {}

    public Map<Integer, Cell> getCells() { return _cells; }

    public int computeCellIndex(int[] address) {
        return (address[0] - 1) * _columnCount + (address[1] - 1);  // address -> [row, column]
    }

    public int[] revertCellIndex(int address) {
        return new int[]{
                (int) address / _columnCount + 1,
                address % _columnCount + 1

        };
    }

    public int nextAddress(Range range, int address) {
        if (range.isHorizontal())
            return address + 1;
        return address + _columnCount;
    }
}
