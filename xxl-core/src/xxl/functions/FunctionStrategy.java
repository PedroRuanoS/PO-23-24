package xxl.functions;

import xxl.content.Content;
import xxl.content.Literal;

public interface FunctionStrategy {
    public int executeOperation(Content op1, Content op2);
}