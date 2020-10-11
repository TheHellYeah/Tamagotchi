package org.kostuychenkov.service;

import org.kostuychenkov.model.entities.Pet;

/**
 * Интерфейс сервиса, определящий методы, необходимые для связи слоя базы данных с слоем представления при работе с питомцами.
 */
public interface PetService {

    Pet load();
    void save(Pet pet);
}
