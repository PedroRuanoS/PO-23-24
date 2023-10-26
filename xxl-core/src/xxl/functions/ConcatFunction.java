package xxl.functions;

import xxl.Range;
import xxl.content.IntegerLiteral;
import xxl.content.Literal;
import xxl.content.StringLiteral;

import java.io.Serializable;
import java.util.List;

public class ConcatFunction extends RangeFunctionStrategy implements Serializable {
    @Override
    public Literal<?> executeOperation(List<Literal<?>> operands) {
        StringBuilder result = new StringBuilder();
        for (Literal<?> operand: operands) {
            if (checkStringArguments(operand))
                result.append((String) operand.getValue());
        }
        return new StringLiteral(result.toString());
    }
}
