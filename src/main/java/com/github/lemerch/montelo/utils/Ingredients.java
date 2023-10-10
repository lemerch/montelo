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
 * ⠀⠀⠀⠀⠀⠀⠀
 * ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠐⣶⣾⣿⣿⣿⣿⣿⣶⡆⠀⠀⠀⠀⠀⠀
 * ⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⡏⢤⡎⣿⣿⢡⣶⢹⣧⠀⠀⠀⠀⠀⠀
 * ⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣶⣶⣇⣸⣷⣶⣾⣿⠀⠀⠀⠀⠀⠀
 * ⠀⠀⠀⠀⠀⠀⠀⠀⠀⢨⣿⣿⣿⢟⣿⣿⣿⣿⣿⣧⡀⠀⠀⠀⠀
 * ⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⡏⣿⣿⣿⣿⣿⣿⣿⣿⡄⠀⠀⠀
 * ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⣿⣿⣿⣜⠿⣿⣿⣿⣿⣿⣿⣿⡄⠀⠀
 * ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠐⣷⣿⡿⣷⣮⣙⠿⣿⣿⣿⣿⣿⡄⠀
 * ⠀⠀⠠⢄⣀⡀⠀⠀⠀⠀⠀⠈⠫⡯⢿⣿⣿⣿⣶⣯⣿⣻⣿⣿⠀
 * ⠀⠀⠤⢆⠆⠈⠉⠳⠤⣄⡀⠀⠀⠀⠙⢻⣿⣿⠿⠿⠿⢻⣿⠙⠇
 *  ⠠⠤⣉⣁⣢⣄⣀⣀⣤⣿⠷⠦⠤⣠⡶⠿⣟⠀⠀⠀⠀⠻⡀⠀
 * ⠀⠀⠔⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠃⠃⠉⠉⠛⠛⠿⢷⡶⠀
 */
package com.github.lemerch.montelo.utils;


import java.lang.annotation.Annotation;

/**
 * <h3>This class is a simple IMMUTABLE container for transport information about field that Heart.dinner want to fill into Fillen.Diet.menu(...)</h3>
 *
 * <p>It contains:
 *  <ul>
 *     <li>field type</li>
 *     <li>field name</li>
 *     <li>field generic</li>
 *     <li>field declaredAnnotation</li>
 *     <li>field modifier</li>
 *  </ul>
 * </p>
 *
 * <p>
 *     Its peculiarity lies in the fact that its fields are public,
 *     but they are not changeable,
 *     since each setter creates a new object - this is done for the convenience and security of user handlers in {@link Fillen.Diet}
 * </p>
 */
public final class Ingredients {

    public final Class<?> type;
    public final String name;
    public final Generic generic;
    public final Annotation[] declaredAnnotations;
    public final Integer modifier;

    /////////////////////////////////////////////

    private Ingredients localIngredients;
    private Class<?> localType;
    private String localName;
    private Generic localGeneric;
    private Annotation[] localDeclaredAnnotations;
    private Integer localModifier;

    public Ingredients(Class<?> type, String name, Generic generic, Annotation[] declaredAnnotations, int modifier) {

        if (type == null) throw new NullPointerException("ingredients: `Class<?> type` can not be null");
        else if (name == null) throw new NullPointerException("ingredients: `String name` can not be null");
        else if (generic == null) throw new NullPointerException("ingredients: `Generic generic` can not be null");
        else if (declaredAnnotations == null) throw new NullPointerException("ingredients: `Annotation[] declaredAnnotation` can not be null");

        this.type = type;
        this.name = name;
        this.generic = generic;
        this.declaredAnnotations = declaredAnnotations;
        this.modifier = modifier;
    }

    public Ingredients setType(Class<?> type) {
        if (type == null) throw new NullPointerException("ingredients: `Class<?> type` can not be null");
        this.localType = type;
        return this;
    }
    public Ingredients setName(String name) {
        if (name == null) throw new NullPointerException("ingredients: `String name` can not be null");
        this.localName = name;
        return this;
    }
    public Ingredients setGeneric(Generic generic) {
        if (generic == null) throw new NullPointerException("ingredients: `Generic generic` can not be null");
        this.localGeneric = generic;
        return this;
    }
    public Ingredients setDeclredAnnotations(Annotation[] declaredAnnotations) {
        if (declaredAnnotations == null) throw new NullPointerException("ingredients: `Annotation[] declaredAnnotation` can not be null");
        this.localDeclaredAnnotations = declaredAnnotations;
        return this;
    }
    public Ingredients setModifier(int modifier) {
        this.localModifier = modifier;
        return this;
    }
    public Ingredients build() {
        if (this.localType == null) this.localType = this.type;
        if (this.localName == null) this.localName = this.name;
        if (this.localGeneric == null) this.localGeneric = this.generic;
        if (this.localDeclaredAnnotations == null) this.localDeclaredAnnotations = this.declaredAnnotations;
        if (this.localModifier == null) this.localModifier = this.modifier;

       return new Ingredients(localType, localName, localGeneric, localDeclaredAnnotations, localModifier);
    }

}
