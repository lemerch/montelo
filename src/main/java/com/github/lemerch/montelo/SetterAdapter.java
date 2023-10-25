package com.github.lemerch.montelo;

import com.github.lemerch.montelo.utils.Bag;

public abstract class SetterAdapter<T extends Montelo> {

    protected Bag<T> bag = new Bag<>();

    public void setBag(Bag<T> bag) {
        this.bag = bag;
    }
    public void setBag(T... montels) {
        for (T montel : montels) {
            this.bag.add(montel);
        }
    }
}
