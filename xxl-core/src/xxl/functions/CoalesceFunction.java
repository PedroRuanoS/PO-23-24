package xxl.functions;

import xxl.content.Literal;
import xxl.content.StringLiteral;

import java.io.Serializable;
import java.util.List;

public class CoalesceFunction extends RangeFunctionStrategy implements Serializable {
    @Override
    public Literal<?> executeOperation(List<Literal<?>> operands) {
        for (Literal<?> operand: operands) {
            if (invalidStringArgument(operand))
                return new StringLiteral(operand.getValue().toString());
        }
        return new StringLiteral("");
    }
}
