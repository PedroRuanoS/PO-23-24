package xxl.storage;


import xxl.Cell;
import xxl.visitor.RenderedContentVisitor;

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
}
