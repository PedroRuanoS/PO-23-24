package xxl.functions;

import xxl.content.IntegerLiteral;
import xxl.content.Literal;

import java.io.Serializable;
import java.util.List;

public class AverageFunction extends RangeFunctionStrategy implements Serializable {
    @Override
    public Literal<?> executeOperation(List<Literal<?>> operands) {
        int sum = 0;
        for (Literal<?> operand: operands) {
            if (invalidIntegerArgument(operand)) return null;
            sum += (int) operand.getValue();
        }
        if (!operands.isEmpty())
            return new IntegerLiteral(String.valueOf(sum / operands.size()));
        return new IntegerLiteral(String.valueOf(0));
    }
}
