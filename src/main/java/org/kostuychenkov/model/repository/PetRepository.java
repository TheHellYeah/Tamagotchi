package org.kostuychenkov.model.repository;

import org.kostuychenkov.model.entities.Pet;

/**
 * Интерфейс, определяющие основные методы при работе с данными питомцев.
 */
public interface PetRepository {

    Pet load();
    void save(Pet pet);
}
