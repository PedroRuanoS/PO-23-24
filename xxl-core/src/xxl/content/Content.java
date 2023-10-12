package xxl.content;

import xxl.exceptions.UnrecognizedEntryException;

import java.io.Serializable;

public class Content implements Serializable {
    public Content createContent(String contentSpecification) throws UnrecognizedEntryException {
        if (contentSpecification.matches("^'"))                // REGEX: strings which start with '
            return new StringContent(contentSpecification);
        if (contentSpecification.matches("^-?\\d+$"))          // REGEX: Integers
            return new IntegerContent(contentSpecification);
        if (contentSpecification.matches("^=[1-9]+;[1-9]+$"))  // REGEX: Reference to other cells
            return new ReferencedContent(contentSpecification);
        if (contentSpecification.matches("^="))                // REGEX: Find functions
            return new FunctionContent(contentSpecification);
        else
            throw new UnrecognizedEntryException(contentSpecification);
    }


}
