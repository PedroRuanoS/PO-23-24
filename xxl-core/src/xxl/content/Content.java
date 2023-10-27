package xxl.content;


import xxl.observer.Observer;
import xxl.storage.Storage;
import xxl.visitor.ContentVisitor;

import java.io.Serializable;

public abstract class Content implements Serializable, Observer {
    private boolean _isStatic = false;
    private boolean _firstVisit = true;     // Used for first time setup for observers
    private boolean _needsUpdate = true;

    public abstract void requestContent(ContentVisitor visitor, Storage data);

    public boolean isStatic() { return _isStatic; }

    public void setState(boolean state) { _isStatic = state; }

    public void update() { _needsUpdate = true; }

    public void updated() { _needsUpdate = false; }

    public boolean isUpdated() { return !_needsUpdate; }

    public boolean isFirstVisit() { return _firstVisit; }

    public void firstVisitComplete() { _firstVisit = false; }

    public boolean isIntegerLiteral() { return false; }
    public boolean isStringLiteral() { return false; }
    public boolean isReferenceContent() { return false; }
    public boolean isFunctionContent() { return false; }
}
