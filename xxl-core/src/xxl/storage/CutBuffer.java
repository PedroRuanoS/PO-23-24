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
    public void requestContents(RenderedContentVisitor renderer) {
        for (int addresses: getCells().keySet()) {
            Cell currentCell = getCells().get(addresses);
            boolean empty = (currentCell.getContent() == null);

            renderer.renderAddress(revertCellIndex(addresses), empty);

            if (!empty)
                currentCell.requestContent(renderer, this);
        }
    }

    public void transferToContents(TransferVisitor transfer) {
        for (Cell currentCell: getCells().values()) {
            transfer.visitCell(currentCell);
        }
        transfer.setRange(_copiedRange);
    }

    public void transferFromContents(TransferVisitor transfer) {
        _copiedRange = transfer.getRange();
        int address = 0;

        for (Content content: transfer.getTransferedContent()) {
            Cell newCell = new Cell(content);
            getCells().put(address, newCell);
            if (_copiedRange.isHorizontal())
                address++;
            else
                address += getColumnCount();
        }
    }
}
