package com.github.lemerch.montelo;

import com.github.lemerch.montelo.utils.Bag;

public class ConstructorAdapter<T extends Montelo> {
    protected Bag<T> bag = new Bag<>();

    public ConstructorAdapter(Bag<T> bag) {
        this.bag = bag;
    }
    public ConstructorAdapter(T... montels) {
        for (T montel : montels) {
            this.bag.add(montel);
        }
    }

}
