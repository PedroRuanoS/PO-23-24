package xxl.content;

import java.io.Serializable;

public class StringContent extends Content implements Serializable {

    private String _value;
    public StringContent(String content) {
        _value = content.substring(1);
    }

    @Override
    public String toString() {
        return "'" + _value;
    }
}
