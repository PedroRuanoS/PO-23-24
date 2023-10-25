package xxl.content;

import xxl.storage.CutBuffer;
import xxl.storage.SpreadsheetData;
import xxl.storage.Storage;
import xxl.visitor.ContentVisitor;
import xxl.Cell;

import java.io.Serializable;

public abstract class Content implements Serializable {
    private boolean _isStatic = false;

    public boolean isStatic() { return _isStatic; }

    public void setState(boolean state) {_isStatic = state;}

    public abstract void requestContent(ContentVisitor visitor, Storage data);
}
