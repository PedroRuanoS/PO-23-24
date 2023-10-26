package xxl.functions;

import xxl.Range;
import xxl.content.Literal;

import java.util.List;

public abstract class RangeFunctionStrategy implements FunctionStrategy {
    private final String INTEGER_REGEX = "^-?\\d+$";
    private final String STRING_REGEX = "^'.*";
    @Override
    public Literal<?> executeOperation(Literal<?> firstOperand, Literal<?> secondOperand) {
        return null;
    }

    public abstract Literal<?> executeOperation(List<Literal<?>> operands);

    public boolean checkIntArguments(Literal<?> operand) {
        return operand != null && !operand.toString().matches(INTEGER_REGEX);
    }
    public boolean checkStringArguments(Literal<?> operand) {
        return operand != null && !operand.toString().matches(STRING_REGEX);
    }
}
