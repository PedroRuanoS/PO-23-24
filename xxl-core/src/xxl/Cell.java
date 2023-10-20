package xxl;

import xxl.content.Content;
import xxl.visitor.ContentVisitor;

import java.io.Serializable;

public class Cell implements Serializable {
    private Content _content = null;

    public Cell(Content content) { _content = content; }

    public Content getContent() { return _content; }

    public void requestContent(ContentVisitor visitor) {
        _content.requestContent(visitor);
    }
}
