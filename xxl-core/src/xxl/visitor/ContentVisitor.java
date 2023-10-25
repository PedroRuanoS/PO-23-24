package xxl.visitor;

import xxl.content.*;
import xxl.storage.SpreadsheetData;
import xxl.storage.Storage;

public interface ContentVisitor {
    void visitInteger(IntegerLiteral integerContent);

    void visitString(StringLiteral stringContent);

    void visitReference(ReferencedContent referenceContent, Storage data);

    void visitFunction(FunctionContent functionContent, Storage data);
}
