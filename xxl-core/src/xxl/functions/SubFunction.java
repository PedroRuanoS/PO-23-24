package xxl.functions;

import xxl.content.Content;

import java.io.Serializable;

public class SubFunction implements FunctionStrategy , Serializable {

    @Override
    public int executeOperation(Content op1, Content op2) {
        return 0;
    }
}