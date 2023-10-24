package xxl.visitor;

import xxl.Cell;
import xxl.content.Content;

import java.util.LinkedList;
import java.util.List;

public class TransferContent implements TransferVisitor {
    private List<Content> _transferedContent = new LinkedList<>();

    @Override
    public void visitCell(Cell cell) {
        _transferedContent.add(cell.getContent());
    }

    @Override
    public void addEmpty() {
        _transferedContent.add(null);
    }

    public List<Content> getTransferedContent() {
        return _transferedContent;
    }

    public boolean isEmpty() { return _transferedContent.isEmpty(); }
}
