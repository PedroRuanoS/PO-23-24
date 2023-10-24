package xxl;

import com.sun.source.tree.ClassTree;
import xxl.content.Content;
import xxl.storage.SpreadsheetData;
import xxl.storage.Storage;
import xxl.visitor.ContentVisitor;
import xxl.visitor.TransferVisitor;

import java.io.Serializable;

public class Cell implements Serializable {
    private Content _content;

    public Cell(Content content) { _content = content; }

    public Content getContent() { return _content; }

    public void requestContent(ContentVisitor visitor, Storage data) {
        _content.requestContent(visitor, data);
    }

    public void transferContent(TransferVisitor visitor, int address) {
        visitor.visitCell(this);
    }
}
