package xxl.content;

import xxl.storage.SpreadsheetData;
import xxl.storage.Storage;
import xxl.visitor.ContentVisitor;

import java.io.Serializable;

public abstract class Content implements Serializable {
    public abstract void requestContent(ContentVisitor visitor, Storage data);
}
