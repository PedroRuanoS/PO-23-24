package xxl.content;


import xxl.storage.Storage;
import xxl.visitor.ContentVisitor;

import java.io.Serializable;

public class IntegerLiteral extends Literal<Integer> implements Serializable {
    private int _value;

    public IntegerLiteral(String content) {
        _value = Integer.parseInt(content);
    }

    @Override
    public void requestContent(ContentVisitor visitor, Storage data) { visitor.visitInteger(this); }

    @Override
    public Integer getValue() {
        return _value;
    }

    @Override
    public String toString() {
        return String.valueOf(_value);
    }

<<<<<<< Updated upstream
<<<<<<< Updated upstream
    public boolean isIntegerLiteral() { return true; }
    public boolean isStringLiteral() { return false; }
    public boolean isFunctionContent() { return false; }
    public boolean isReferencedContent() { return false; }
=======
=======
>>>>>>> Stashed changes
    @Override
    public boolean isIntegerLiteral() {
        return true;
    }
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
}
