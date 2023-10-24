package xxl.functions;

import xxl.content.Content;
import xxl.content.Literal;

public interface BinaryFunctionStrategy {
    public Literal<?> executeOperation(Content op1, Content op2);
}
