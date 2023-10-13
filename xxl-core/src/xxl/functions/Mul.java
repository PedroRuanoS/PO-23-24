package xxl.functions;

public class Mul implements FunctionStrategy {
    @Override
    public int calculate(int[] values) {
        return values[0] * values[1];
    }
}