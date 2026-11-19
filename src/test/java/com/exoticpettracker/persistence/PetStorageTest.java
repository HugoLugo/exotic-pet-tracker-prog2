package com.exoticpettracker.persistence;

import com.exoticpettracker.domain.Arachnid;
import com.exoticpettracker.domain.Reptile;
import com.exoticpettracker.service.PetCollection;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.*;

public class PetStorageTest {
    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void savesAndLoadsPets() throws IOException {
        Path file = temporaryFolder.getRoot().toPath().resolve("data/pets.dat");
        PetCollection original = new PetCollection();
        original.addPet(new Reptile("Sandy", "Sand boa"));
        original.addPet(new Arachnid("Lupita", "Whip spider"));
        PetStorage storage = new PetStorage();

        storage.save(original, file);
        PetCollection restored = storage.load(file);

        assertEquals(2, restored.size());
        assertTrue(restored.getPetsSortedByName().get(0) instanceof Arachnid);
        assertEquals("Sandy", restored.getPetsSortedByName().get(1).getName());
    }

    @Test
    public void missingFileStartsWithEmptyCollection() throws IOException {
        Path file = temporaryFolder.getRoot().toPath().resolve("missing.dat");
        assertEquals(0, new PetStorage().load(file).size());
    }

    @Test
    public void corruptFileIsReportedInsteadOfSilentlyDiscarded() throws IOException {
        Path file = temporaryFolder.getRoot().toPath().resolve("bad.dat");
        Files.writeString(file, "not a serialized collection");
        assertThrows(IOException.class, () -> new PetStorage().load(file));
    }
}
