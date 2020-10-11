package org.kostuychenkov.service;

import org.kostuychenkov.model.entities.Pet;
import org.kostuychenkov.model.repository.PetRepository;

/**
 * Реализация интерфейса сервиса питомцев.
 */
public class PetServiceImpl implements PetService {

    private PetRepository repository;

    public PetServiceImpl(PetRepository repository) {
        this.repository = repository;
    }

    @Override
    public Pet load() {
        return repository.load();
    }

    @Override
    public void save(Pet pet) {
        repository.save(pet);
    }
}
