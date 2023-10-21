package xxl;

import com.sun.source.tree.ClassTree;
import xxl.content.Content;
import xxl.storage.SpreadsheetData;
import xxl.visitor.ContentVisitor;

import java.io.Serializable;

public class Cell implements Serializable {
    private Content _content;

    public Cell(Content content) { _content = content; }

    public Content getContent() { return _content; }

    public void requestContent(ContentVisitor visitor, SpreadsheetData data) {
        _content.requestContent(visitor, data);
    }
}
