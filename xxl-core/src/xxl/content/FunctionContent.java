package xxl.content;

import java.io.Serializable;
import java.util.Map;

import xxl.Cell;
import xxl.functions.*;
import xxl.exceptions.UnrecognizedEntryException;

public class FunctionContent extends Content implements Serializable {
    private String _function_raw;
    private FunctionStrategy _strategy;
    private Map<Integer, Cell> _cells;
    private Content _op1;
    private Content _op2;

    public FunctionContent(String content, int max_columns, Map<Integer, Cell> cells) throws UnrecognizedEntryException {
        _cells = cells;
        _function_raw = content;
        String function = content.substring(1, content.indexOf("("));
        String arg1 = content.substring(content.indexOf("(") + 1, content.indexOf(","));
        String arg2 = content.substring(content.indexOf(",") + 1, content.indexOf(")"));

        _op1 = parse(arg1, max_columns);
        _op2 = parse(arg2, max_columns);

        if (function.equals("ADD"))
            setStrategy(new AddFunction());
        else if (function.equals("SUB"))
            setStrategy(new SubFunction());
        else if (function.equals("MUL"))
            setStrategy(new MulFunction());
        else if (function.equals("DIV"))
            setStrategy(new DivFunction());
        else
            throw new UnrecognizedEntryException(content);

    }   

    public void setStrategy(FunctionStrategy function) {
        _strategy = function;
    }

    public Content parse(String arg, int max_columns) throws UnrecognizedEntryException {
        if (arg.matches("^-?\\d+$"))                 // REGEX: Integers
            return new IntegerContent(arg);
        else if (arg.matches("^[1-9]+;[1-9]+$")) {     // REGEX: Reference to other cells
            arg = "=" + arg;
            return new ReferencedContent(arg, max_columns, _cells);
        }
        else
            throw new UnrecognizedEntryException(arg);
    }

    public int executeOperation() {
        return _strategy.executeOperation(_op1, _op2);
    }

    @Override
    public String toString() {
        return _function_raw;
    }

    @Override
    public String stringValue() {
        return null;
    }

    @Override
    public int intValue() {
        return 0;
    }

    @Override
    public Literal value() {
        return null;
    }
}
