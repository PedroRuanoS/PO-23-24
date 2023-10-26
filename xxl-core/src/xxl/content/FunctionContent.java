package xxl.content;

import xxl.Range;
import xxl.Spreadsheet;
import xxl.functions.*;
import xxl.storage.SpreadsheetData;
import xxl.storage.Storage;
import xxl.visitor.ContentVisitor;

import java.io.Serializable;

public class FunctionContent extends Content implements Serializable {
    private String _functionName;
    private String _functionArguments;
    private Literal<?> _result;
    private FunctionStrategy _strategy;
    private Content _firstArgument;
    private Content _secondArgument;
    private Range _rangeArgument;

    public FunctionContent(String content) {
        _functionName = content.substring(1, content.indexOf("("));
        _functionArguments = content.substring(content.indexOf("(") + 1, content.indexOf(")"));
        ContentBuilder contentBuilder = new ContentBuilder();

        if (setStrategy(_functionName)) {
            String fields[] = _functionArguments.split(",");
            if (fields.length > 1) {
                if (fields[0].split(";").length == 2)
                    fields[0] = "=" + fields[0];
                _firstArgument = contentBuilder.build(fields[0]);

                if (fields[1].split(";").length == 2)
                    fields[1] = "=" + fields[1];
                _secondArgument = contentBuilder.build(fields[1]);
            } else {
                _rangeArgument = new Range(fields[0]);
            }
        }
    }

    public boolean setStrategy(String functionName) {
        return switch (functionName) {
            case ("ADD") -> {
                _strategy = new AddFunction();
                yield true;
            }
            case ("SUB") -> {
                _strategy = new SubFunction();
                yield true;
            }
            case ("MUL") -> {
                _strategy = new MulFunction();
                yield true;
            }
            case ("DIV") -> {
                _strategy = new DivFunction();
                yield true;
            }
            case ("AVERAGE") -> {
                _strategy = new AverageFunction();
                yield false;
            }
            case ("PRODUCT") -> {
                _strategy = new ProductFunction();
                yield false;
            }
            case ("CONCAT") -> {
                _strategy = new ConcatFunction();
                yield false;
            }
            case ("COALESCE") -> {
                _strategy = new CoalesceFunction();
                yield false;
            }
            default -> false;
        };
    }

    public Literal<?> executeBinaryFunction(Literal<?> firstOperand, Literal<?> secondOperand) {
        return _strategy.executeOperation(firstOperand, secondOperand);
    }

    public Literal<?> executeRangeFunction(Range rangeOperand) {
        return _strategy.executeOperation(rangeOperand);
    }

    @Override
    public void requestContent(ContentVisitor visitor, Storage data) { visitor.visitFunction(this, data); }

    public String getFunctionName() { return _functionName; }

    public String renderArguments() {
        return _functionArguments;
    }

    public Literal<?> getResult() { return _result; }

    public void setResult(Literal<?> result) { _result = result; }

    public boolean isBinaryFunction() {
        return (_rangeArgument == null);
    }

    public Content getFirstArgument() { return _firstArgument; }
    public Content getSecondArgument() { return _secondArgument; }

}
