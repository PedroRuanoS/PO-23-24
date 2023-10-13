package xxl.functions;

import xxl.content.Content;
import xxl.content.Literal;

public class AddFunction implements FunctionStrategy {
    private Content _op1;
    private Content _op2;
    public AddFunction(Content op1, Content op2) {
        _op1 = op1;
        _op2 = op2;
    }
    @Override
    public int executeOperation() { return _op1.intValue() + _op2.intValue(); }
}