package xxl.visitor;

import xxl.content.*;
import xxl.storage.SpreadsheetData;
import xxl.storage.Storage;

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
    public void visitReference(ReferencedContent referenceContent, Storage data) {
        data.readContents(referenceContent.getCellAddress(), this);
    }

    @Override
    public void visitFunction(FunctionContent functionContent, SpreadsheetData data) {
    }

    public Literal<?> readContent() { return _literalContent; }
}
