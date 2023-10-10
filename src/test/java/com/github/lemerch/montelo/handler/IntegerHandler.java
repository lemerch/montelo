package com.github.lemerch.montelo.handler;

import com.github.lemerch.montelo.Montelo;
import com.github.lemerch.montelo.exception.UserException;
import com.github.lemerch.montelo.utils.Ingredients;

public class IntegerHandler extends Montelo {
    @Override
    public Object menu(Ingredients ingredients) throws UserException {
        if (isTypesEquals(ingredients.type, Integer.class)) return 80085;
        return null;
    }
}