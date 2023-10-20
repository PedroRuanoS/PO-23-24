package xxl.content;

import xxl.visitor.ContentVisitor;

public class FunctionContent extends Content {
    private String _functionName;

    public FunctionContent(String content) {
        _functionName = content.substring(1, content.indexOf("("));
        String[] arguments = content.substring(content.indexOf("(") + 1, content.indexOf(")"))
                                    .split(",");

    }

    @Override
    public void accept(ContentVisitor visitor) { visitor.visitFunction(this); }
}
