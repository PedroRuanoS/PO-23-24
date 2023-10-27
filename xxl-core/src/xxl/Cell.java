package xxl;

import xxl.content.Content;
import xxl.storage.Storage;
import xxl.visitor.ContentVisitor;

import java.io.Serializable;

public class Cell implements Serializable {
    private Content _content;

    public Cell(Content content) { _content = content; }

    public Content getContent() { return _content; }

    public void setContent(Content content) { _content = content; }

    public void requestContent(ContentVisitor visitor, Storage data) {
        _content.requestContent(visitor, data);
    }


}
