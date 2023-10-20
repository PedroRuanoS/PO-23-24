package xxl.content;

import xxl.Range;
import xxl.visitor.ContentVisitor;

import java.io.Serializable;

public class ReferencedContent extends Content implements Serializable {
    private Range _cellAddress;

    public ReferencedContent(String content) {
        _cellAddress = new Range(content.substring(1));
    }

    @Override
    public void requestContent(ContentVisitor visitor) { visitor.visitReference(this); }
}
