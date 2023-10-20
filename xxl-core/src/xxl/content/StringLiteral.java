package xxl.content;

import xxl.visitor.ContentVisitor;

public class StringLiteral extends Literal<String> {
    private String _value;

    public StringLiteral(String content) {
        _value = content.substring(1);
    }

    @Override
    public void accept(ContentVisitor visitor) {visitor.visitString(this);}

    @Override
    public String getValue() {
        return _value;
    }

    @Override
    public String toString() {
        return "'" + _value;
    }
}
