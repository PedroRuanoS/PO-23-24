package xxl.visitor;

import xxl.content.FunctionContent;
import xxl.content.IntegerLiteral;
import xxl.content.ReferencedContent;
import xxl.content.StringLiteral;
import xxl.storage.SpreadsheetData;

public interface ContentVisitor {
    void visitInteger(IntegerLiteral integerContent);

    void visitString(StringLiteral stringContent);

    void visitReference(ReferencedContent referenceContent, SpreadsheetData data);

    void visitFunction(FunctionContent functionContent);
}
