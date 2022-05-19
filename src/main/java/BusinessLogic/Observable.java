package BusinessLogic;

import GUI.Observer;

public abstract class Observable {//SUBJECT

    public abstract void registerObserver(Observer o);

    public abstract void notifyObservers(Order order);
}
