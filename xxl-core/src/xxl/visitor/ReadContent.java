package xxl.visitor;

import xxl.content.*;
import xxl.storage.SpreadsheetData;

public class ReadContent implements ContentVisitor {
    private Literal<?> _literalContent;

    @Override
    public void visitInteger(IntegerLiteral integerContent) {
        _literalContent = integerContent;
    }

    @Override
    public void visitString(StringLiteral stringContent) {
        _literalContent = stringContent;
    }

    @Override
    public void visitReference(ReferencedContent referenceContent, SpreadsheetData data) {
        data.readContents(referenceContent.getCellAddress(), this);
    }

    @Override
    public void visitFunction(FunctionContent functionContent) {
    }

    public Literal<?> readContent() {
        if (_literalContent == null)
            return null;
        return _literalContent;
    }
}
