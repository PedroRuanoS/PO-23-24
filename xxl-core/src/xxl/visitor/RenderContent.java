package xxl.visitor;

import xxl.content.FunctionContent;
import xxl.content.IntegerLiteral;
import xxl.content.ReferencedContent;
import xxl.content.StringLiteral;
import xxl.storage.SpreadsheetData;

public class RenderContent implements RenderedContentVisitor {
    private String _rendered = "";
    private ReadContent _readContent = new ReadContent();

    public String getAddress(int[] address) {return address[0] + ";" + address[1];}

    private String contentValue() {
        if (_readContent.readContent() == null)
            return "#VALUE";
        return _readContent.readContent().toString();
    }

    @Override
    public void visitInteger(IntegerLiteral integerContent) {
        _readContent.visitInteger(integerContent);
        _rendered += contentValue() + "\n";
    }

    @Override
    public void visitString(StringLiteral stringContent) {
        _readContent.visitString(stringContent);
        _rendered += contentValue() + "\n";
    }

    @Override
    public void visitReference(ReferencedContent referenceContent, SpreadsheetData data) {
        _readContent.visitReference(referenceContent, data);
        _rendered += contentValue() +
                "=" + getAddress(referenceContent.getCellAddress().getRange().get(0)) + "\n";
    }

    @Override
    public void visitFunction(FunctionContent functionContent) {

    }

    @Override
    public void renderAddress(int[] address, boolean empty) {
        _rendered += getAddress(address) + "|" + (empty ? "\n" : "");
    }

    @Override
    public String toString() { return _rendered; }

}
