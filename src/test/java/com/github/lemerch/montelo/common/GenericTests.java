package com.github.lemerch.montelo.common;

import com.github.lemerch.montelo.exception.GenericException;
import com.github.lemerch.montelo.utils.Generic;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class GenericTests {

    public List<Set<String>> collection;
    private Field field;

    @Before
    public void setup() throws NoSuchFieldException, GenericException {
        this.field = this.getClass().getField("collection");
    }

    @Test
    public void whenGetGenericTypesInList_thenOK() {
        Generic generic = new Generic(this.field.getGenericType());
        List<Class<?>> list = generic.getList();

        assertEquals(List.class, list.get(0));
        assertEquals(Set.class, list.get(1));
        assertEquals(String.class, list.get(2));
    }
}
