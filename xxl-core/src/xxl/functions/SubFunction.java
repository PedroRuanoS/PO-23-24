package xxl.functions;

import xxl.content.Content;
import xxl.content.IntegerLiteral;
import xxl.content.Literal;

import java.io.Serializable;

public class SubFunction implements BinaryFunctionStrategy , Serializable {

    @Override
    public Literal<?> executeOperation(Content op1, Content op2) { return new IntegerLiteral(""); }
}