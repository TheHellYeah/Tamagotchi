package org.kostuychenkov.service;

import org.kostuychenkov.model.entities.*;

/**
 * Класс, отвечающий за создание различных типов питомцов(реализация паттерна фабрика).
 */
public class PetFactory {

    public Pet getPet(PetType type) {
        if(type == PetType.CAT) {
            return new Cat();
        }
        if(type == PetType.PIG) {
            return new Pig();
        }
        if(type == PetType.CHICKEN) {
            return new Chicken();
        }
        return null;
    }
}
