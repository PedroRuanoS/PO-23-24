package xxl.functions;

import xxl.content.IntegerLiteral;
import xxl.content.Literal;

import java.io.Serializable;

public class DivFunction extends BinaryFunctionStrategy implements Serializable {
    @Override
    public Literal<?> executeOperation(Literal<?> firstOperand, Literal<?> secondOperand) {
        if (invalidArgument(firstOperand, secondOperand)) return null;

        int result;
        try {
            result = (int) firstOperand.getValue() / (int) secondOperand.getValue();
        } catch (ArithmeticException e) { return null; }

        return new IntegerLiteral(String.valueOf(result));
    }
}