package xxl.content;

import xxl.exceptions.UnrecognizedEntryException;

import java.io.Serializable;

public abstract class Content implements Serializable {
    public abstract String stringValue();
    public abstract int intValue();

    public abstract Literal value();
}
