package xxl.functions;

import xxl.content.ContentBuilder;
import xxl.content.Literal;
import java.util.List;
import java.io.Serializable;

public abstract class BinaryFunctionStrategy implements FunctionStrategy, Serializable {
    private final String INTEGER_PATTERN = new ContentBuilder().getIntegerPattern();
    public abstract Literal<?> executeOperation(Literal<?> firstOperand, Literal<?> secondOperand);

    @Override
    public Literal<?> executeOperation(List<Literal<?>> operands) {
        return null;
    }

    public boolean invalidArgument(Literal<?> firstOperand, Literal<?> secondOperand) {
        return (firstOperand == null || secondOperand == null ||
                !firstOperand.toString().matches(INTEGER_PATTERN) ||
                !secondOperand.toString().matches(INTEGER_PATTERN));
    }
}
