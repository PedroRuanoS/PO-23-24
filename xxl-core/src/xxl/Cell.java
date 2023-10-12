package xxl;

import xxl.content.Content;

import java.io.Serializable;

public class Cell implements Serializable {
    private Content _content;

    public Cell(String contentSpecification) {
        if (!contentSpecification.equals(""))
            _content = new Content().createContent(contentSpecification);
    }

    public Content getContent() { return _content; }
}
