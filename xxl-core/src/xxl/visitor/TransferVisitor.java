package xxl.visitor;

import xxl.Cell;
import xxl.Range;
import xxl.content.Content;

import java.util.List;

public interface TransferVisitor {
    public void addCell(Cell cell);

    public void setRange(Range range);

    public Range getRange();

    public List<Cell> getTransferedCells();

    public boolean isEmpty();
}
