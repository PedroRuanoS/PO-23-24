package xxl.storage;


import xxl.Cell;
import xxl.content.Content;
import xxl.visitor.RenderedContentVisitor;
import xxl.visitor.TransferVisitor;

public class CutBuffer extends Storage {
    public CutBuffer(int rows, int columns) {
        super(rows, columns);
    }

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
    }

    public void transferFromContents(TransferVisitor transfer) {
        int address = 0;
        for (Content content: transfer.getTransferedContent()) {
            Cell newCell = new Cell(content);
            getCells().put(address, newCell);
            address++;
        }
    }
}
