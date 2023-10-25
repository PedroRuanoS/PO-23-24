package xxl.storage;

import xxl.Cell;
import xxl.Range;
import xxl.content.Content;
import xxl.visitor.ReadContent;
import xxl.visitor.RenderedContentVisitor;
import xxl.visitor.TransferVisitor;

public class SpreadsheetData extends Storage {
    public SpreadsheetData(int rowCount, int columnCount) {
        super(rowCount, columnCount);
    }

    public void renderContents(Range range, RenderedContentVisitor renderer) {
        for (int[] address: range.getRange()) {
            Cell currentCell = getCells().get(computeCellIndex(address));
            render(currentCell, address, renderer);
        }
    }

    public void transferCellsTo(Range range, TransferVisitor transfer) {
        for (int[] address: range.getRange()) {
            Cell currentCell = getCells().get(computeCellIndex(address));

            if (currentCell != null) { // In case of reference, calculate cached value before sending it
                ReadContent updateValue = new ReadContent();
                currentCell.requestContent(updateValue, this);
            }

            transfer.addCell(currentCell);
        }
        transfer.setRange(range);
    }

    public void transferCellsFrom(Range range, TransferVisitor transfer) {
        if (!transfer.isEmpty()) {
            if (range.isSingle() || range.sameDimension(transfer.getRange())) {
                int startAddress = computeCellIndex(range.getRange().get(0));
                for (Cell cell : transfer.getTransferedCells()) {
                    Content currentContent = cell.getContent();
                    if (currentContent != null)
                        currentContent.setState(false);     // Remove static property since it's no longer in cut buffer

                    putContent(currentContent, startAddress);
                    startAddress = nextAddress(transfer.getRange(), startAddress);
                }
            }
        }
    }

}
