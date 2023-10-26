package xxl.functions;

import xxl.Range;
import xxl.content.Content;
import xxl.content.IntegerLiteral;
import xxl.content.Literal;
import xxl.content.StringLiteral;

import java.io.Serializable;

// FIXME para todas as funcoes o metodo intValue() ja nao existe!

public class AddFunction extends BinaryFunctionStrategy implements Serializable {
    @Override
    public Literal<?> executeOperation(Literal<?> firstOperand, Literal<?> secondOperand) {
        if (!checkArguments(firstOperand, secondOperand)) return null;

        int result = (int) firstOperand.getValue() + (int) secondOperand.getValue();
        return new IntegerLiteral(String.valueOf(result));
    }
}