package com.exoticpettracker.domain;

public final class Arachnid extends Pet {
    private static final long serialVersionUID = 1L;

    public Arachnid(String name, String species) {
        super(name, species);
    }

    @Override
    public String getDescription() {
        return getName() + " - Arachnid (" + getSpecies() + ")";
    }
}
