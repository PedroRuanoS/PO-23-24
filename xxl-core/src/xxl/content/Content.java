package xxl.content;

import xxl.storage.SpreadsheetData;
import xxl.visitor.ContentVisitor;

import java.io.Serializable;

public abstract class Content implements Serializable {
    public void requestContent(ContentVisitor visitor, SpreadsheetData data) {}
}
