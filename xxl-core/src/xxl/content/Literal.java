package xxl.content;


import java.io.Serializable;

public abstract class Literal<Type> extends Content implements Serializable {
    public abstract Type getValue();
}
