package xxl.content;

import java.io.Serializable;
import xxl.functions.*;
import xxl.exceptions.UnrecognizedEntryException;

public class FunctionContent extends Content implements Serializable {
    private FunctionStrategy _strategy;
    private String _content;
    public FunctionContent(String content, int max_columns) throws UnrecognizedEntryException {
        String function = content.substring(1, content.indexOf("("));
        String arg1 = content.substring(content.indexOf("(") + 1, content.indexOf(";"));
        String arg2 = content.substring(content.indexOf(";") + 1, content.indexOf(")"));

        switch (function) {
            case "ADD":
                setStrategy(new AddFunction(parse(arg1, max_columns), parse(arg2, max_columns)));
            case "SUB":
                setStrategy(new SubFunction(parse(arg1, max_columns), parse(arg2, max_columns)));
            case "MUL":
                setStrategy(new MulFunction(parse(arg1, max_columns), parse(arg2, max_columns)));
            case "DIV":
                setStrategy(new DivFunction(parse(arg1, max_columns), parse(arg2, max_columns)));
            default:
                //bolas
        }
    }   

    public void setStrategy(FunctionStrategy function) {
        _strategy = function;
    }

    public Content parse(String arg, int max_columns) throws UnrecognizedEntryException {
        if (arg.matches("^-?\\d+$"))             // REGEX: Integers
            return new IntegerContent(arg);
        if (arg.matches("^=[1-9]+;[1-9]+$"))     // REGEX: Reference to other cells
            return new ReferencedContent(arg, max_columns);
        else {
            throw new UnrecognizedEntryException(arg);
        }
    }

    public int executeOperation() {
        return _strategy.executeOperation();
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
