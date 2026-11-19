package com.exoticpettracker.domain;

import com.exoticpettracker.service.PetFactory;
import org.junit.Test;

import static org.junit.Assert.*;

public class PetFactoryTest {
    private final PetFactory factory = new PetFactory();

    @Test
    public void factoryCreatesDifferentTypesWithPolymorphicDescriptions() {
        Pet reptile = factory.createPet("reptile", "Sandy", "Kenyan sand boa");
        Pet arachnid = factory.createPet("arachnid", "Lupita", "Phrynus");
        assertTrue(reptile instanceof Reptile);
        assertTrue(arachnid instanceof Arachnid);
        assertTrue(reptile.getDescription().contains("Reptile"));
        assertTrue(arachnid.getDescription().contains("Arachnid"));
    }

    @Test
    public void rejectsBlankName() {
        assertThrows(InvalidPetException.class,
                () -> factory.createPet("reptile", " ", "Python regius"));
    }

    @Test
    public void rejectsUnknownType() {
        assertThrows(InvalidPetException.class,
                () -> factory.createPet("bird", "Kiwi", "Parrot"));
    }
}
