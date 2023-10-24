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
package com.github.lemerch.montelo;

import com.github.lemerch.montelo.exception.UserException;
import com.github.lemerch.montelo.utils.Ingredients;

import java.util.List;
import java.util.ArrayList;

/**
 * <h3>Montelo - is a simply abstract class for your type handlers</h3>
 * <p>This class consists of inner available methods:
 *      <ul>
 *          <li>{@link Montelo#connector}</li>
 *          <li>{@link Montelo#isTypesEqual}</li>
 *          <li>{@link Montelo#getAllInterfaces}</li>
 *      </ul>
 * </p>
 *
 * <p>Montelo also contains abstact method for your realisation type handling - {@link Montelo#menu}</p>
 */
public abstract class Montelo {

    /**
     * This method can be used for merging your montelos creating kinda api
     * for your pack of type handling. Just create new {@link Montelo} class
     * and write menu with: {@code return connector(ingredients, new IntegerMontelo()...)}
     *
     * @param ingredients type of {@link Ingredients}
     * @param montelos type of {@link Montelo}
     * @return null safety value for montelos with your ingredients
     * @throws UserException custom user exception that you can throw
     */
    protected Object connector(Ingredients ingredients, Montelo... montelos) throws UserException {
        Object result = null;
        for (Montelo montel : montelos) {
            Object timed = montel.menu(ingredients);
            if (timed != null) {
                result = timed;
            }
        }
        return result;
    }

    /**
     * This is simple method for checking equals of tow class types
     *
     * @param one first type
     * @param two second type
     * @return true/false
     */
    protected Boolean isTypesEqual(Class<?> one, Class<?> two) {
        if (one == null ||  two == null) return false;
        return two.isAssignableFrom(one);
    }

    /**
     * This method is needed if you need to collect all the interfaces of your class,
     * including all the interfaces of parents, etc.
     *
     * @param clazz class type
     * @return list of interfaces
     */
    protected List<Class<?>> getAllInterfaces(Class<?> clazz) {
        if (clazz == null) {
            return new ArrayList<>();
        }
        List<Class<?>> interfacesFound = new ArrayList<>();
        getAllInterfaces(clazz, interfacesFound);
        return interfacesFound;
    }

    private void getAllInterfaces(Class<?> clazz,
                                         List<Class<?>> interfacesFound) {
        while (clazz != null) {
            Class<?>[] interfaces = clazz.getInterfaces();

            for (Class<?> anInterface : interfaces) {
                if (!interfacesFound.contains(anInterface)) {
                    interfacesFound.add(anInterface);
                    getAllInterfaces(anInterface, interfacesFound);
                }
            }
            clazz = clazz.getSuperclass();
        }
    }

    /**
     * This abstract method is a central method of this class that will be used for type handling
     *
     * @param ingredients type of {@link Ingredients}
     * @return value for one type
     * @throws UserException custom user exception that you can throw
     */
    abstract public Object menu(Ingredients ingredients) throws UserException;
}
