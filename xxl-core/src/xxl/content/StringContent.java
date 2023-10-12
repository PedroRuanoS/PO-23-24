package xxl.content;

public class StringContent extends Content {

    private String _value;
    public StringContent(String content) {
        _value = content.substring(1);
    }

    @Override
    public String toString() {
        return "'" + _value;
    }
}
