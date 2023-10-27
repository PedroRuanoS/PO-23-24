package xxl.visitor;


import xxl.Cell;
import xxl.Range;

import java.util.LinkedList;
import java.util.List;

public class TransferCells implements TransferVisitor {
    private List<Cell> _transferedCells = new LinkedList<>();
    private Range _range;

    @Override
    public void addCell(Cell cell) {
        _transferedCells.add(cell);
    }

    public void setRange(Range range) { _range = range; }

    public Range getRange() { return _range; }

    public List<Cell> getTransferredCells() {
        return _transferedCells;
    }

    public boolean isEmpty() { return _transferedCells.isEmpty(); }
}
