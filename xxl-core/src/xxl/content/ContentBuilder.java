package xxl.content;

public class ContentBuilder {
    private final String STRING_REGEX = "^'.*";
    private final String INTEGER_REGEX = "^-?\\d+$";
    private final String REFERENCE_REGEX = "^=[1-9]+;[1-9]+$";
    private final String FUNCTION_REGEX = "^=[A-Z]+\\(.+\\)";


    public Content build(String contentSpecification) {
        if (contentSpecification.matches(STRING_REGEX))
            return new StringLiteral(contentSpecification);
        if (contentSpecification.matches(INTEGER_REGEX))
            return new IntegerLiteral(contentSpecification);
        // FIXME ADD REMAINING

        return null;
    }
}
