package xxl;

import xxl.content.Content;

import java.io.Serializable;

public class Cell implements Serializable {
    private Content _content = null;

    public Content getContent() { return _content; }

    public void setContent(Content content) { _content = content; }

    @Override
    public String toString() {
        return _content.toString();
    }
}
