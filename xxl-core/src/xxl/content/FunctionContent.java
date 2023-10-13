package xxl.content;

import java.io.Serializable;

public class FunctionContent extends Content implements Serializable {
    public FunctionContent(String content) {}

    @Override
    public String stringValue() {
        return null;
    }

    @Override
    public int intValue() {
        return 0;
    }

    @Override
    public Literal value() {
        return null;
    }
}
