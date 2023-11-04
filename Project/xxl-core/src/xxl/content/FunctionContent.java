package xxl.content;


import xxl.Range;
import xxl.exceptions.InvalidFunctionException;
import xxl.functions.*;
import xxl.storage.Storage;
import xxl.visitor.ContentVisitor;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

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
            String[] fields = _functionArguments.split(",");
            if (fields.length > 1) {
                if (fields[0].split(";").length == 2)
                    fields[0] = "=" + fields[0];
                try {
                    _firstArgument = contentBuilder.build(fields[0]);
                } catch (InvalidFunctionException ignored) {}

                if (fields[1].split(";").length == 2)
                    fields[1] = "=" + fields[1];
                try {
                    _secondArgument = contentBuilder.build(fields[1]);
                } catch (InvalidFunctionException ignored) {}
            } else {
                _rangeArgument = new Range(fields[0]);
            }
        }
    }

    public boolean setStrategy(String functionName) {
        Map<String, FunctionStrategy> functionMap = Map.of(
            "ADD", new AddFunction(),
            "SUB", new SubFunction(),
            "MUL", new MulFunction(),
            "DIV", new DivFunction(),
            "AVERAGE", new AverageFunction(),
            "PRODUCT", new ProductFunction(),
            "CONCAT", new ConcatFunction(),
            "COALESCE", new CoalesceFunction()
        );

        FunctionStrategy strategy = functionMap.get(functionName);
        if (strategy != null) {
            _strategy = strategy;
            return true;
        }
        return false;
    }

    public Literal<?> executeBinaryFunction(Literal<?> firstOperand, Literal<?> secondOperand) {
        if (_strategy != null)
            return _strategy.executeOperation(firstOperand, secondOperand);
        return null;
    }

    public Literal<?> executeRangeFunction(List<Literal<?>> operands) {
        if (_strategy != null)
            return _strategy.executeOperation(operands);
        return null;
    }

    @Override
    public void requestContent(ContentVisitor visitor, Storage data) {
        if (isFirstVisit()) {
            if (!isBinaryFunction())
                data.registerObserverRange(_rangeArgument, this);
            firstVisitComplete();
        }
        visitor.visitFunction(this, data);
    }

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
    public Range getRangeArgument() { return _rangeArgument; }

    public boolean isInvalidFunction() { return _strategy == null; }

    @Override
    public boolean isFunctionContent() {
        return true;
    }
}
