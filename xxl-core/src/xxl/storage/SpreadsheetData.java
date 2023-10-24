package xxl.storage;

import xxl.Cell;
import xxl.Range;
import xxl.content.Content;
import xxl.visitor.ContentVisitor;
import xxl.visitor.RenderedContentVisitor;
import xxl.visitor.TransferVisitor;

public class SpreadsheetData extends Storage {
    // FIXME still need to find a better way to reuse code
    public SpreadsheetData(int rowCount, int columnCount) {
        super(rowCount, columnCount);
    }

    public void requestContents(Range range, RenderedContentVisitor renderer) {
        for (int[] address: range.getRange()) {
            Cell currentCell = getCells().get(computeCellIndex(address));
            boolean empty = (currentCell == null);

            renderer.renderAddress(address, empty);

            if (!empty)
                currentCell.requestContent(renderer, this);
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

    public void transferFromContents(Range range, TransferVisitor transfer) {
        if (!transfer.isEmpty()) {
            if (range.isSingle()) {
                int startAddress = computeCellIndex(range.getRange().get(0));
                // int maxaddress;
            } else {

            }
        }
    }
}
