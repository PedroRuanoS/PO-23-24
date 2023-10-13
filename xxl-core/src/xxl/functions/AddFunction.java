package xxl.functions;

import xxl.content.Content;
import xxl.content.Literal;

import java.io.Serializable;

public class AddFunction implements FunctionStrategy , Serializable {
    @Override
    public int executeOperation(Content op1, Content op2) {
        return op1.intValue() + op2.intValue();
    }
}