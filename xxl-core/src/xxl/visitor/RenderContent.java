package xxl.visitor;

import xxl.content.FunctionContent;
import xxl.content.IntegerLiteral;
import xxl.content.ReferencedContent;
import xxl.content.StringLiteral;

public class RenderContent implements ContentVisitor{
    private String _rendering = "";

    @Override
    public void visitAddress(int[] address, boolean last) {
        _rendering += address[0] + ";" + address[1] + "|" + (last ? "\n" : "");
    }

    @Override
    public void visitInteger(IntegerLiteral content) {
        _rendering += content.getValue() + "\n";
    }

    @Override
    public void visitString(StringLiteral content) {
        _rendering += "'" + content.getValue() + "\n";
    }

    @Override
    public void visitReference(ReferencedContent content) {}

    @Override
    public void visitFunction(FunctionContent content) {}

    @Override
    public String toString() { return _rendering; }
}
