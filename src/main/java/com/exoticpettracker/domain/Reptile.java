package com.exoticpettracker.domain;

public final class Reptile extends Pet {
    private static final long serialVersionUID = 1L;

    public Reptile(String name, String species) {
        super(name, species);
    }

    @Override
    public String getDescription() {
        return getName() + " - Reptile (" + getSpecies() + ")";
    }
}
