package com.exoticpettracker.domain;

import java.io.Serializable;

/** Common, validated state and contract for all tracked pets. */
public abstract class Pet implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String name;
    private final String species;

    protected Pet(String name, String species) {
        this.name = requireValue(name, "Name");
        this.species = requireValue(species, "Species");
    }

    private static String requireValue(String value, String field) {
        if (value == null || value.isBlank()) {
            throw new InvalidPetException(field + " cannot be empty.");
        }
        return value.trim();
    }

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }

    /** Overridden by subtypes; invoked through Pet references at runtime. */
    public abstract String getDescription();
}
