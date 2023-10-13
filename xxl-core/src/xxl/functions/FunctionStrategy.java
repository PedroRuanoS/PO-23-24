package xxl.functions;

import xxl.content.Content;

public interface FunctionStrategy {
    int executeOperation(Content op1, Content op2);
}