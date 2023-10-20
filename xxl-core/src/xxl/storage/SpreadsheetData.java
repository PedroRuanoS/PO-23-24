package xxl.storage;

import xxl.Cell;
import xxl.Range;
import xxl.content.Content;
import xxl.visitor.ContentVisitor;

public class SpreadsheetData extends Storage {
    public SpreadsheetData(int rowCount, int columnCount) {
        super(rowCount, columnCount);
    }

    public void insert(Range range, Content content) {
        for (int[] address : range.getRange()) {
            Cell newCell = new Cell(content);
            getCells().put(computeCellIndex(address), newCell);
        }
    }

    public void requestContents(Range range, ContentVisitor visitor) {
        for (int[] address: range.getRange()) {
            Cell currentCell = getCells().get(computeCellIndex(address));
            boolean last = (currentCell == null);

            visitor.visitAddress(address, last);
            if (currentCell != null)
                currentCell.requestContent(visitor);
        }
    }

    private int computeCellIndex(int[] address) {
        return (address[0] - 1) * getColumnCount() + (address[1] - 1);  // address -> [row, column]
    }

}
