package xxl.functions;

import xxl.Range;
import xxl.content.Content;
import xxl.content.Literal;

import java.util.List;

public interface FunctionStrategy {
    public Literal<?> executeOperation(Literal<?> firstOperand, Literal<?> secondOperand);
    public Literal<?> executeOperation(List<Literal<?>> operands);
}
