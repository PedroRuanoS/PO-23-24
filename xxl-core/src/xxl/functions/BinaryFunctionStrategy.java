package xxl.functions;

import xxl.Range;
import xxl.content.Content;
import xxl.content.Literal;

public abstract class BinaryFunctionStrategy implements FunctionStrategy {
    private final String INTEGER_REGEX = "^-?\\d+$";
    public abstract Literal<?> executeOperation(Literal<?> firstOperand, Literal<?> secondOperand);

    @Override
    public Literal<?> executeOperation(Range range) {
        return null;
    }

    public boolean checkArguments(Literal<?> firstOperand, Literal<?> secondOperand) {
        if (firstOperand == null || secondOperand == null ||
                !firstOperand.toString().matches(INTEGER_REGEX) ||
                !secondOperand.toString().matches(INTEGER_REGEX))
            return false;
        return true;
    }
}
