package xxl.functions;

import xxl.content.Content;

import java.io.Serializable;

// FIXME para todas as funcoes o metodo intValue() ja nao existe!

public class AddFunction implements FunctionStrategy , Serializable {
    @Override
    public int executeOperation(Content op1, Content op2) {
        return op1.intValue() + op2.intValue();
    }
}