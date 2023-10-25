/**
 * Copyright 2023 Dmitry Terakov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *        .-``'.
 *      .`   .`~  Montelo~
 *  _.-'     '._          x(n+1) = rx(1-x)
 *
 */
package com.github.lemerch.montelo.utils;

import com.github.lemerch.montelo.Montelo;
import com.github.lemerch.montelo.exception.CommonMonteloException;
import com.github.lemerch.montelo.exception.MonteloAlreadyExistException;
import com.github.lemerch.montelo.exception.MonteloNotFoundException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * <h3>Bag is a collection that accept objects {@link Montelo}</h3>
 * <h3>Warrning - montelos objects can not be repeat in one bag</h3>
 */
public final class Bag<T extends Montelo> implements Iterable<T> {

    private List<T> bag;

    public Bag() {
        this.bag = new ArrayList<>();
    }
    public Bag(int size) {
        this.bag = new ArrayList<>(size);
    }

    public boolean add(T montelo) throws CommonMonteloException {
        try {
            get(montelo.getClass());
            throw new MonteloAlreadyExistException(montelo.getClass().toString());
        }catch (MonteloNotFoundException e) {
            return this.bag.add(montelo);
        }
    }

    public void addAll(Bag<T> bag) { this.bag.addAll(bag.getInner()); }

    public T remove(int index) { return this.bag.remove(index); }

    public Montelo remove(Class<? extends Montelo> associated) throws CommonMonteloException {
        int index = 0;
        for (Montelo montelo : this.bag) {
            Class<? extends Montelo> an = montelo.getClass();
            if (associated.getName().equals(an.getName()) && associated.getPackageName().equals(an.getPackageName())) {
                return remove(index);
            }
            index++;
        }
        throw new MonteloNotFoundException(associated.toString());
    }


    public T get(int index) {
        return this.bag.get(index);
    }

    public T get(Class<? extends Montelo> associated) throws CommonMonteloException {
        for (T montel : this.bag) {
            Class<? extends Montelo> an = montel.getClass();
            if (associated.getName().equals(an.getName()) && associated.getPackageName().equals(an.getPackageName())) {
                return montel;
            }
        }
        throw new MonteloNotFoundException(associated.toString());
    }

    public boolean swap(int firstIndex, int secondIndex) {
        T temp = this.bag.get(firstIndex);
        this.bag.set(firstIndex, this.bag.get(secondIndex));
        this.bag.set(secondIndex, temp);
        return true;
    }


    public boolean swap(Class<? extends Montelo> first, Class<? extends Montelo> second) throws CommonMonteloException {

        int firstIndex = 0, secondIndex = 0;
        int iterator = 0;

        for (T montel : this.bag) {
            if (secondIndex != 0 && firstIndex != 0) break;

            Class<? extends Montelo> an = montel.getClass();
            if (first.getName().equals(an.getName()) && first.getPackageName().equals(an.getPackageName())) {
                firstIndex = iterator;
            } else if (second.getName().equals(an.getName()) && second.getPackageName().equals(an.getPackageName())) {
                secondIndex = iterator;
            }
            iterator++;
        }
        if (firstIndex == 0) {
            throw new MonteloAlreadyExistException(first.toString());
        }else if(secondIndex == 0) {
            throw new MonteloAlreadyExistException(second.toString());
        }

        return swap(firstIndex, secondIndex);
    }

    public boolean contains(Class<? extends Montelo> associated) {
        for (T montel : this.bag)
            if (montel.getClass().equals(associated)) return true;

        return false;
    }
    public boolean contains(T montelo) { return this.bag.contains(montelo); }

    public Bag<T> clone() {
        Bag<T> bag = new Bag<>();
        bag.setInner(new ArrayList<>(this.bag));
        return bag;
    }

    private List<T> getInner() { return this.bag; }
    private void setInner(List<T> list) { this.bag = list; }

    @Override
    public Iterator<T> iterator() {
        return this.bag.iterator();
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<T> spliterator() {
        return Iterable.super.spliterator();
    }
}
