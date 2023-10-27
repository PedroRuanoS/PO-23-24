package xxl.content;


import xxl.Range;
import xxl.storage.Storage;
import xxl.visitor.ContentVisitor;

import java.io.Serializable;

public class ReferencedContent extends Content implements Serializable {
    private Range _cellAddress;
    private Literal<?> _value;

    public ReferencedContent(String content) {
        _cellAddress = new Range(content.substring(1));
    }

    public Range getCellAddress() { return _cellAddress; }

    @Override
    public void requestContent(ContentVisitor visitor, Storage data) {
        visitor.visitReference(this, data);
    }

    public void setValue(Literal<?> value) { _value = value; }
    public Literal<?> getValue() { return _value; }

    @Override
    public boolean isReferenceContent() {
        return true;
    }

}
