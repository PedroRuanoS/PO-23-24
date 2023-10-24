package xxl.functions;

import xxl.Range;
import xxl.content.IntegerLiteral;
import xxl.content.Literal;

public class AverageFunction implements RangeFunctionStrategy{
    @Override
    public Literal<?> executeOperation(Range range) { return new IntegerLiteral(""); }
}
