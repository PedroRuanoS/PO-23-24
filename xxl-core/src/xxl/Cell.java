package xxl;

import xxl.content.Content;
import xxl.exceptions.UnrecognizedEntryException;

import java.io.Serializable;

public class Cell implements Serializable {
    private Content _content = null;

    public void createContent(String contentSpecification) throws UnrecognizedEntryException {
        if (!contentSpecification.equals(""))
            _content = new Content().createContent(contentSpecification);
    }
    public Content getContent() {
        if (_content != null)
            return _content;
        return null;
    }

    @Override
    public String toString() {
        return _content.toString();
    }
}
