package xxl.visitor;

public interface RenderedContentVisitor extends ContentVisitor {
    void renderAddress(int[] address, boolean empty);
}
