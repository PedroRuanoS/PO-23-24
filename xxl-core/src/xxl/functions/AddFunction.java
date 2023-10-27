package xxl.functions;

import xxl.content.IntegerLiteral;
import xxl.content.Literal;

import java.io.Serializable;

public class AddFunction extends BinaryFunctionStrategy implements Serializable {
    @Override
    public Literal<?> executeOperation(Literal<?> firstOperand, Literal<?> secondOperand) {
        if (invalidArgument(firstOperand, secondOperand)) return null;

        int result = (int) firstOperand.getValue() + (int) secondOperand.getValue();
        return new IntegerLiteral(String.valueOf(result));
    }
}