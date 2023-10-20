package xxl.content;

import xxl.visitor.ContentVisitor;

import java.io.Serializable;

public class FunctionContent extends Content implements Serializable {
    private String _functionName;

    public FunctionContent(String content) {
        _functionName = content.substring(1, content.indexOf("("));
        String[] arguments = content.substring(content.indexOf("(") + 1, content.indexOf(")"))
                                    .split(",");

    }

    @Override
    public void requestContent(ContentVisitor visitor) { visitor.visitFunction(this); }
}
