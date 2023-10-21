package xxl.content;

import xxl.storage.SpreadsheetData;
import xxl.visitor.ContentVisitor;

import java.io.Serializable;

public class StringLiteral extends Literal<String> implements Serializable {
    private String _value;

    public StringLiteral(String content) {
        _value = content.substring(1);
    }

    @Override
    public void requestContent(ContentVisitor visitor, SpreadsheetData data) {visitor.visitString(this);}

    @Override
    public String getValue() {
        return _value;
    }

    @Override
    public String toString() {
        return "'" + _value;
    }
}
