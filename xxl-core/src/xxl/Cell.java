package xxl;

import xxl.content.Content;
import xxl.observer.Observer;
import xxl.observer.Subject;
import xxl.storage.Storage;
import xxl.visitor.ContentVisitor;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Cell implements Serializable, Subject {
    private Content _content;
    private List<Observer> observers = new LinkedList<>();

    public Cell(Content content) {
        _content = content;
        notifyObservers();
    }

    public Content getContent() { return _content; }

    public void setContent(Content content) {
        _content = content;
        notifyObservers();
    }

    public void requestContent(ContentVisitor visitor, Storage data) {
        _content.requestContent(visitor, data);
<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream
=======
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
        if (visitor.gotUpdated())
            notifyObservers();

>>>>>>> Stashed changes
    }

    public void deleteContent() { _content = null; }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            if (observer != null)
                observer.update();
        }
    }
}
