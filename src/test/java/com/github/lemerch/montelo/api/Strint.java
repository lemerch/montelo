package com.github.lemerch.montelo.api;

import com.github.lemerch.montelo.Montelo;
import com.github.lemerch.montelo.exception.UserException;
import com.github.lemerch.montelo.handler.IntegerHandler;
import com.github.lemerch.montelo.handler.StringHandler;
import com.github.lemerch.montelo.utils.Ingredients;

public class Strint {
    private static final Montelo api = new Montelo() {
        @Override
        public Object menu(Ingredients ingredients) throws UserException {
            return connector(ingredients, new StringHandler(), new IntegerHandler());
        }
    };
}
