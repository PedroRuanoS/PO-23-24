package xxl.visitor;


import xxl.content.FunctionContent;
import xxl.content.IntegerLiteral;
import xxl.content.ReferencedContent;
import xxl.content.StringLiteral;
import xxl.storage.Storage;

public interface ContentVisitor {
    void visitInteger(IntegerLiteral integerContent);

    void visitString(StringLiteral stringContent);

    void visitReference(ReferencedContent referenceContent, Storage data);

    void visitFunction(FunctionContent functionContent, Storage data);

    boolean gotUpdated();
}
