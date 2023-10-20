package xxl.content;

import xxl.range.CellRange;
import xxl.visitor.ContentVisitor;

public class ReferencedContent extends Content {
    private CellRange _cellAddress;

    public ReferencedContent(String content) {
        _cellAddress = new CellRange(content.substring(1));
        _cellAddress.processRange();
    }

    @Override
    public void accept(ContentVisitor visitor) { visitor.visitReference(this); }
}
