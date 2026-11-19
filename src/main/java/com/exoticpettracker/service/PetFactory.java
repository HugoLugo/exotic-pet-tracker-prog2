package com.exoticpettracker.service;

import com.exoticpettracker.domain.Arachnid;
import com.exoticpettracker.domain.InvalidPetException;
import com.exoticpettracker.domain.Pet;
import com.exoticpettracker.domain.Reptile;

/** Factory pattern: client requests a Pet without choosing a concrete constructor. */
public final class PetFactory {
    public Pet createPet(String type, String name, String species) {
        if (type == null) {
            throw new InvalidPetException("Pet type is required.");
        }
        return switch (type.trim().toLowerCase(java.util.Locale.ROOT)) {
            case "reptile" -> new Reptile(name, species);
            case "arachnid" -> new Arachnid(name, species);
            default -> throw new InvalidPetException("Pet type must be reptile or arachnid.");
        };
    }
}
