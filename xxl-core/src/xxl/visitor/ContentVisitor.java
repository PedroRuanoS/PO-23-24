package xxl.visitor;

import xxl.content.FunctionContent;
import xxl.content.IntegerLiteral;
import xxl.content.ReferencedContent;
import xxl.content.StringLiteral;

public interface ContentVisitor {
    void visitInteger(IntegerLiteral integerContent);

    void visitString(StringLiteral stringContent);

    void visitReference(ReferencedContent referenceContent);

    void visitFunction(FunctionContent functionContent);

    void visitAddress(int[] address, boolean last);
}
