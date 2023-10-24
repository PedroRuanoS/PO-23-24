package xxl.visitor;

import xxl.Cell;
import xxl.Range;
import xxl.content.Content;

import java.util.List;

public interface TransferVisitor {
    public void visitCell(Cell cell);

    public void addEmpty();

    public void setRange(Range range);

    public Range getRange();

    public List<Content> getTransferedContent();

    public boolean isEmpty();
}
