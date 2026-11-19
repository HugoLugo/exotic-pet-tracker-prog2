package com.exoticpettracker.service;

import com.exoticpettracker.domain.Pet;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/** Aggregate of zero or more pets; the internal collection is never exposed. */
public final class PetCollection implements Serializable {
    private static final long serialVersionUID = 1L;

    private final List<Pet> pets = new ArrayList<>();

    public void addPet(Pet pet) {
        pets.add(Objects.requireNonNull(pet, "Pet cannot be null."));
    }

    public int size() {
        return pets.size();
    }

    public List<Pet> getPetsSortedByName() {
        List<Pet> sorted = new ArrayList<>(pets);
        sorted.sort(Comparator.comparing(Pet::getName, String.CASE_INSENSITIVE_ORDER)
                .thenComparing(Pet::getSpecies, String.CASE_INSENSITIVE_ORDER));
        return List.copyOf(sorted);
    }
}
