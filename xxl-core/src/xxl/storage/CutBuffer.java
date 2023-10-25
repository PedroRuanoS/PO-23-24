package xxl.storage;


import xxl.Cell;
import xxl.Range;
import xxl.content.Content;
import xxl.visitor.RenderedContentVisitor;
import xxl.visitor.TransferVisitor;

public class CutBuffer extends Storage {
    public CutBuffer(int rows, int columns) {
        super(rows, columns);
    }

    private Range _copiedRange;

    public void setRange(Range range) { _copiedRange = range; }

    @Override
    public void renderContents(RenderedContentVisitor renderer) {
        for (int address: getCells().keySet()) {
            Cell currentCell = getCells().get(address);
            render(currentCell, revertCellIndex(address), renderer);
        }
    }

    public void transferCellsTo(TransferVisitor transfer) {
        for (Cell currentCell: getCells().values()) {
            transfer.addCell(currentCell);
        }
        transfer.setRange(_copiedRange);
    }

    public void transferCellsFrom(TransferVisitor transfer) {
        _copiedRange = transfer.getRange();
        int address = 0;

        for (Cell cell: transfer.getTransferedCells()) {
            if (cell == null)
                putContent(null, address);  // Putting null in content is the same as deleting the content
            else {
                Content currentContent = cell.getContent();
                currentContent.setState(true);         // Make content static since it's going to be in cutbuffer
                putContent(currentContent, address);
            }
            address = nextAddress(_copiedRange, address);
        }
    }
}
