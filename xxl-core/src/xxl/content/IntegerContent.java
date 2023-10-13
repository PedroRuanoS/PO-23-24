package xxl.content;

import java.io.Serializable;

public class IntegerContent extends Literal implements Serializable {
    private int _value;
    public IntegerContent(String content) {
        _value = Integer.parseInt(content);
    }

    public int intValue() { return _value;}

    @Override
    public int getIndex() {
        return 0;
    }

    @Override
    public String toString() {
        return String.valueOf(_value);
    }

}
