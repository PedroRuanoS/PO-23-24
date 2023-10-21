package xxl.storage;

import xxl.Cell;
import xxl.Range;
import xxl.content.Content;
import xxl.visitor.ContentVisitor;
import xxl.visitor.ReadContent;
import xxl.visitor.RenderedContentVisitor;

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

    // FIXME still need to find better way to reuse code instead of having these 2 functions that almost do the same
    public void requestContents(Range range, RenderedContentVisitor renderer) {
        for (int[] address: range.getRange()) {
            Cell currentCell = getCells().get(computeCellIndex(address));
            boolean empty = (currentCell == null);

            renderer.renderAddress(address, empty);

            if (!empty)
                currentCell.requestContent(renderer, this);
        }
    }

    public void readContents(Range range, ReadContent reader) {
        for (int[] address: range.getRange()) {
            Cell currentCell = getCells().get(computeCellIndex(address));
            boolean empty = (currentCell == null);

            if (!empty)
                currentCell.requestContent(reader, this);
        }
    }

    /*for (int[] address: range.getRange()) {
            Cell currentCell = getCells().get(computeCellIndex(address));
            boolean empty = (currentCell == null);

            ReadContent reader = null;
            if (!silent)
                reader = new ReadContent();

            if (!silent)
                visitor.visitAddress(address, empty);

            if (!empty)
                if (!silent)
                    currentCell.requestContent(reader, this);
                else
                    currentCell.requestContent(visitor, this);

            if (!silent)
                visitor.
        }*/



    private int computeCellIndex(int[] address) {
        return (address[0] - 1) * getColumnCount() + (address[1] - 1);  // address -> [row, column]
    }

}
