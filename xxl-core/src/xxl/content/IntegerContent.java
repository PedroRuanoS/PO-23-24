package xxl.content;

import java.io.Serializable;

public class IntegerContent extends Content implements Serializable {
    private int _value;
    public IntegerContent(String content) {
        _value = Integer.parseInt(content);
    }

    @Override
    public String toString() {
        return String.valueOf(_value);
    }
}
