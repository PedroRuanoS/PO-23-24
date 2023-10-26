package xxl.functions;

import xxl.content.Literal;
import xxl.content.StringLiteral;

import java.io.Serializable;
import java.util.List;

public class ConcatFunction extends RangeFunctionStrategy implements Serializable {
    @Override
    public Literal<?> executeOperation(List<Literal<?>> operands) {
        StringBuilder result = new StringBuilder();
        result.append("'");

        for (Literal<?> operand: operands) {
            if (!invalidStringArgument(operand))
                result.append((String) operand.getValue());
        }
        return new StringLiteral(result.toString());
    }
}
