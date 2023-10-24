package xxl.visitor;

import xxl.Cell;
import xxl.content.Content;

import java.util.List;

public interface TransferVisitor {
    public void visitCell(Cell cell);

    public void addEmpty();

    public List<Content> getTransferedContent();

    public boolean isEmpty();
}
