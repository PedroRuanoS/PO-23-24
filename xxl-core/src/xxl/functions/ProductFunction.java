package xxl.functions;

import xxl.Range;
import xxl.content.IntegerLiteral;
import xxl.content.Literal;

import java.io.Serializable;

public class ProductFunction extends RangeFunctionStrategy implements Serializable {
    @Override
    public Literal<?> executeOperation(Range range) {
        return new IntegerLiteral("");
    }
}
