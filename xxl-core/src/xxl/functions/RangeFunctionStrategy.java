package xxl.functions;

import xxl.Range;
import xxl.content.Literal;

public abstract class RangeFunctionStrategy implements FunctionStrategy {

    @Override
    public Literal<?> executeOperation(Literal<?> firstOperand, Literal<?> secondOperand) {
        return null;
    }

    public abstract Literal<?> executeOperation(Range range);
}
