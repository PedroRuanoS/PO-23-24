package xxl.content;

public class IntegerContent extends Content {
    private int _value;
    public IntegerContent(String content) {
        _value = Integer.parseInt(content);
    }

    @Override
    public String toString() {
        return String.valueOf(_value);
    }
}
