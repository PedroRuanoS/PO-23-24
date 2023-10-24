package xxl.functions;

import xxl.Range;
import xxl.content.Literal;

public interface RangeFunctionStrategy {
    public Literal<?> executeOperation(Range range);

}
