package com.github.lemerch.montelo.common;

import com.github.lemerch.montelo.Montelo;
import com.github.lemerch.montelo.exception.GenericException;
import com.github.lemerch.montelo.exception.MonteloAlreadyExistException;
import com.github.lemerch.montelo.handler.IntegerHandler;
import com.github.lemerch.montelo.handler.StringHandler;
import com.github.lemerch.montelo.utils.Bag;
import com.github.lemerch.montelo.utils.Generic;
import com.github.lemerch.montelo.utils.Ingredients;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

public class BagTests {

    public Integer intField;
    public String strField;

    private Ingredients intIngr;
    private Ingredients strIngr;

    @Before
    public void setup() throws NoSuchFieldException, GenericException {
        Field ifield = this.getClass().getField("intField");
        Field sfield = this.getClass().getField("strField");

        this.intIngr = new Ingredients(ifield.getType(), ifield.getName(), new Generic(ifield.getGenericType()),
                ifield.getDeclaredAnnotations(), ifield.getModifiers());

        this.strIngr = new Ingredients(sfield.getType(), sfield.getName(), new Generic(sfield.getGenericType()),
                sfield.getDeclaredAnnotations(), sfield.getModifiers());
    }

    @Test
    public void whenDuplicateHandlersIntoBag_expectMonteloAlreadyExistException() {

        // #1 case
        assertThrows(MonteloAlreadyExistException.class, () -> {
            Bag bag = new Bag(2);
            bag.add(new StringHandler());
            bag.add(new StringHandler());
        });

        // #2 case
        assertThrows(MonteloAlreadyExistException.class, () -> {
            StringHandler strh = new StringHandler();
            Bag bag = new Bag(2);
            bag.add(new StringHandler());
            bag.add(strh);
        });

        // #3 case
        Bag bag = new Bag(2);
        assertTrue(bag.add(new IntegerHandler()));
        assertTrue(bag.add(new com.github.lemerch.montelo.handler.other.IntegerHandler()));
    }

    @Test
    public void whenGetIntegerHandlerAndCallMenu_expectValue80085() {
        StringHandler strh = new StringHandler();
        IntegerHandler inth = new IntegerHandler();

        Bag bag = new Bag(2);
        bag.add(strh);
        bag.add(inth);

        Montelo montel = bag.get(inth.getClass());
        assertEquals(80085, montel.menu(this.intIngr));
    }

}
