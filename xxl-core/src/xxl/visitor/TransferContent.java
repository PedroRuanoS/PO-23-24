package xxl.visitor;

import xxl.Cell;
import xxl.Range;
import xxl.content.Content;

import java.util.LinkedList;
import java.util.List;

public class TransferContent implements TransferVisitor {
    private List<Content> _transferedContent = new LinkedList<>();
    private Range _range;

    @Override
    public void visitCell(Cell cell) {
        _transferedContent.add(cell.getContent());
    }

    public void setRange(Range range) { _range = range; }

    public Range getRange() { return _range; }

    @Override
    public void addEmpty() {
        _transferedContent.add(null);
    }

    public List<Content> getTransferedContent() {
        return _transferedContent;
    }

    public boolean isEmpty() { return _transferedContent.isEmpty(); }
}
