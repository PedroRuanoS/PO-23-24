package xxl.content;

import xxl.visitor.ContentVisitor;

import java.io.Serializable;

public class IntegerLiteral extends Literal<Integer> implements Serializable {
    private int _value;

    public IntegerLiteral(String content) {
        _value = Integer.parseInt(content);
    }

    @Override
    public void requestContent(ContentVisitor visitor) { visitor.visitInteger(this); }

    @Override
    public Integer getValue() {
        return _value;
    }

    @Override
    public String toString() {
        return String.valueOf(_value);
    }
}
