package xxl.content;

import java.io.Serializable;

public class StringContent extends Literal implements Serializable {
    private String _value;

    public StringContent(String content) {
        _value = content.substring(1);
        System.out.println("Result: " + _value);
    }



    @Override
    public String toString() {
        return "'" + _value;
    }


    @Override
    public int intValue() {
        return 0;
    }

    @Override
    public int getIndex() {
        return 0;
    }

}
