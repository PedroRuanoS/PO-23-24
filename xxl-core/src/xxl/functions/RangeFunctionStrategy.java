package xxl.functions;

import xxl.content.ContentBuilder;
import xxl.content.Literal;

import java.io.Serializable;
import java.util.List;

public abstract class RangeFunctionStrategy implements FunctionStrategy, Serializable {
    private final String INTEGER_PATTERN = new ContentBuilder().getIntegerPattern();
    private final String STRING_PATTERN = new ContentBuilder().getStringPattern();
    @Override
    public Literal<?> executeOperation(Literal<?> firstOperand, Literal<?> secondOperand) {
        return null;
    }

    public abstract Literal<?> executeOperation(List<Literal<?>> operands);

    public boolean invalidIntegerArgument(Literal<?> operand) {
        return operand == null || !operand.toString().matches(INTEGER_PATTERN);
    }
    public boolean invalidStringArgument(Literal<?> operand) {
        return operand == null || !operand.toString().matches(STRING_PATTERN);
    }

}
