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
    private Literal<?> _result;
    private BinaryFunctionStrategy _binaryStrategy;
    private RangeFunctionStrategy _rangeStrategy;

    public FunctionContent(String content) {
        _functionName = content.substring(1, content.indexOf("("));
        ContentBuilder contentBuilder = new ContentBuilder();
        if (setStrategy(_functionName)) {
            String arg1 = content.substring(content.indexOf("(") + 1, content.indexOf(","));
            String arg2 = content.substring(content.indexOf(",") + 1, content.indexOf(")"));
            Content operand1 = contentBuilder.build(arg1);
            Content operand2 = contentBuilder.build(arg2);
            if (arg1.charAt(1) == ';')
               operand1 = contentBuilder.build("=" + arg1);
            if (arg2.charAt(1) == ';')
                operand2 = contentBuilder.build("=" + arg2);
            _result = _binaryStrategy.executeOperation(operand1, operand2);
        } else {
            Range operands = new Range(content.substring(content.indexOf("(") + 1, content.indexOf(")")));
            _result = _rangeStrategy.executeOperation(operands);
        }
    }

    public boolean setStrategy(String functionName) {
        return switch (functionName) {
            case ("ADD") -> {
                _binaryStrategy = new AddFunction();
                yield true;
            }
            case ("SUB") -> {
                _binaryStrategy = new SubFunction();
                yield true;
            }
            case ("MUL") -> {
                _binaryStrategy = new MulFunction();
                yield true;
            }
            case ("DIV") -> {
                _binaryStrategy = new DivFunction();
                yield true;
            }
            case ("AVERAGE") -> {
                _rangeStrategy = new AverageFunction();
                yield false;
            }
            case ("PRODUCT") -> {
                _rangeStrategy = new ProductFunction();
                yield false;
            }
            case ("CONCAT") -> {
                _rangeStrategy = new ConcatFunction();
                yield false;
            }
            case ("COALESCE") -> {
                _rangeStrategy = new CoalesceFunction();
                yield false;
            }
            default -> false;
        };
    }

    @Override
    public void requestContent(ContentVisitor visitor, Storage data) { visitor.visitFunction(this, data); }

}
