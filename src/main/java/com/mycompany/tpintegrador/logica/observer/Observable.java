package com.mycompany.tpintegrador.logica.observer;

public abstract class Observable {
    public abstract void addObserver(Observer observer);
    public abstract void removeObserver(Observer observer);
    public abstract void notifyObservers();
}
