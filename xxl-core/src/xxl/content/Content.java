package xxl.content;

import xxl.exceptions.UnrecognizedEntryException;

import java.io.Serializable;

public abstract class Content implements Serializable {
    public abstract int intValue();

    public abstract int getIndex();
}
