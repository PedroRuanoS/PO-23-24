package xxl.content;

import java.io.Serializable;

import xxl.functions.*;
import xxl.exceptions.UnrecognizedEntryException;
import xxl.storage.Storage;

public class FunctionContent extends Content implements Serializable {
    private String _function_raw;
    private Storage _storage;
    private Content _operand1;
    private Content _operand2;

    private FunctionStrategy _strategy;

    public FunctionContent(String content, int max_columns, Storage storage) throws UnrecognizedEntryException {
        _storage = storage;
        _function_raw = content;
        String function_name = content.substring(1, content.indexOf("("));
        String arg1 = content.substring(content.indexOf("(") + 1, content.indexOf(","));
        String arg2 = content.substring(content.indexOf(",") + 1, content.indexOf(")"));

        _operand1 = parse(arg1, max_columns);
        _operand2 = parse(arg2, max_columns);

        if (function_name.equals("ADD"))
            setStrategy(new AddFunction());
        else if (function_name.equals("SUB"))
            setStrategy(new SubFunction());
        else if (function_name.equals("MUL"))
            setStrategy(new MulFunction());
        else if (function_name.equals("DIV"))
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
            return new ReferencedContent(arg, max_columns, _storage);
        }
        else
            throw new UnrecognizedEntryException(arg);
    }

    public int executeOperation() {
        return _strategy.executeOperation(_operand1, _operand2);
    }

    @Override
    public String toString() {
        return _function_raw;
    }

    @Override
    public int intValue() {
        return 0;
    }

    @Override
    public int getIndex() {
        return 0;
    }

}
