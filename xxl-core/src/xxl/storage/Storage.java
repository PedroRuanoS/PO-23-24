package xxl.storage;

import xxl.Cell;
import xxl.content.Content;
import xxl.Range;
import xxl.visitor.ContentVisitor;
import xxl.visitor.RenderedContentVisitor;
import xxl.visitor.TransferVisitor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

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
            Cell newCell = new Cell(content);
            getCells().put(computeCellIndex(address), newCell);
        }
    }

    public void requestContents(Range range, RenderedContentVisitor renderer) {}

    public void requestContents(RenderedContentVisitor renderer) {}

    public void readContents(Range range, ContentVisitor reader) {
        for (int[] address: range.getRange()) {
            Cell currentCell = getCells().get(computeCellIndex(address));
            boolean empty = (currentCell == null);

            if (!empty)
                currentCell.requestContent(reader, this);
        }
    }

    public void deleteContents(Range range) {
        for (int[] address: range.getRange()) {
            getCells().remove(computeCellIndex(address));
        }
    }

    public void transferToContents(Range range, TransferVisitor transfer) {
        for (int[] address: range.getRange()) {
            int computedIndex = computeCellIndex(address);
            Cell currentCell = getCells().get(computedIndex);
            boolean empty = (currentCell == null);

            if (!empty)
                transfer.visitCell(currentCell);
            else
                transfer.addEmpty();
        }
    }

    public void transferFromContents(TransferVisitor transfer) {
        int address = 0;
        for (Content content: transfer.getTransferedContent()) {
            Cell newCell = new Cell(content);
            getCells().put(address, newCell);
            address++;
        }
//        for (int[] address: range.getRange()) {
//            int computedIndex = computeCellIndex(address);
//            Content transferedContent = transfer.getTransferedContent().get(computedIndex);
//
//            if (transferedContent != null) {
//                Cell newCell = new Cell(transferedContent);
//                getCells().put(computedIndex, newCell);
//            }
//        }
    }

    public int getRowCount() { return _rowCount; }

    public int getColumnCount() { return _columnCount; }

    public Map<Integer, Cell> getCells() { return _cells; }

    public int computeCellIndex(int[] address) {
        return (address[0] - 1) * getColumnCount() + (address[1] - 1);  // address -> [row, column]
    }

    public int[] revertCellIndex(int address) {
        return new int[]{
                (int) address / getColumnCount() + 1,
                address % getColumnCount() + 1

        };
    }
}
