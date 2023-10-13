package xxl.content;

import java.io.Serializable;
import xxl.functions.FunctionStrategy;

public class FunctionContent extends Content implements Serializable {
    private FunctionStrategy _strategy;
    private String _content;
    public FunctionContent(String content) {
        
    }   

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
