package xxl.content;

import xxl.Range;
import xxl.storage.SpreadsheetData;
import xxl.storage.Storage;
import xxl.visitor.ContentVisitor;

import java.io.Serializable;

public class ReferencedContent extends Content implements Serializable {
    private Range _cellAddress;

    public ReferencedContent(String content) {
        _cellAddress = new Range(content.substring(1));
    }

    public Range getCellAddress() { return _cellAddress; }

    @Override
    public void requestContent(ContentVisitor visitor, Storage data) {
        visitor.visitReference(this, data);
    }
}
