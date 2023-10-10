package com.github.lemerch.montelo.common;

import com.github.lemerch.montelo.utils.Ingredients;
import org.junit.Test;
import static org.junit.Assert.assertThrows;

public class IngredientsTests {
    @Test
    public void whenSendNull_expectNullPointerException() {
        assertThrows(NullPointerException.class, () -> {
           new Ingredients(null, null, null, null, 0);
        });
    }
}
