package xxl.content;

public class Content {
    public Content createContent(String stringContent) {
        if (stringContent.matches("^'"))                // REGEX: strings start with '
            return new StringContent(stringContent);
        if (stringContent.matches("^-?[0-9]+$"))        // REGEX: Integers
            return new IntegerContent(stringContent);
        if (stringContent.matches("^=[1-9]+;[1-9]+$"))  // REGEX: Reference to other cells
            return new ReferencedContent(stringContent);
        if (stringContent.matches("^="))                // REGEX: Find functions
            return new FunctionContent(stringContent);
        /*else throw unrecognized*/
        return null;
    }

    @Override
    public String toString() {return "hello";}
}
