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
