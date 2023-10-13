package xxl.content;

import java.io.Serializable;

public class IntegerContent extends Literal implements Serializable {
    private int _value;
    public IntegerContent(String content) {
        _value = Integer.parseInt(content);
    }

    public int intValue() { return _value;}

    public String stringValue() { return toString(); }

    @Override
    public String toString() {
        return String.valueOf(_value);
    }

    @Override
    public Literal value() {
        return null;
    }
}
