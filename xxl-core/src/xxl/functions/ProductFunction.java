package xxl.functions;

import xxl.Range;
import xxl.content.IntegerLiteral;
import xxl.content.Literal;

import java.io.Serializable;
import java.util.List;

public class ProductFunction extends RangeFunctionStrategy implements Serializable {
    @Override
    public Literal<?> executeOperation(List<Literal<?>> operands) {
        int result = 1;
        for (Literal<?> operand: operands) {
            if (!checkIntArguments(operand)) return null;
            result *= (int) operand.getValue();
        }
        if (!operands.isEmpty())
            return new IntegerLiteral(String.valueOf(result));
        return new IntegerLiteral(String.valueOf(0));
    }
}
