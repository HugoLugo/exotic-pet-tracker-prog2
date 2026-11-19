package com.exoticpettracker.service;

import com.exoticpettracker.domain.Pet;
import com.exoticpettracker.domain.Reptile;
import com.exoticpettracker.domain.Arachnid;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class PetCollectionTest {
    @Test
    public void sortsPetsAlphabeticallyWithoutChangingTheOriginalCollection() {
        PetCollection collection = new PetCollection();
        collection.addPet(new Reptile("Zelda", "Corn snake"));
        collection.addPet(new Arachnid("annie", "Tarantula"));

        List<Pet> sorted = collection.getPetsSortedByName();
        assertEquals(2, collection.size());
        assertEquals("annie", sorted.get(0).getName());
        assertEquals("Zelda", sorted.get(1).getName());
        assertThrows(UnsupportedOperationException.class,
                () -> sorted.add(new Reptile("X", "Gecko")));
    }
}
