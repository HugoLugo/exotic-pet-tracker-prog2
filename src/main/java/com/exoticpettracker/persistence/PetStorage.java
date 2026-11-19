package com.exoticpettracker.persistence;

import com.exoticpettracker.service.PetCollection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

/** File persistence for the small, trusted local application. */
public final class PetStorage {
    public void save(PetCollection collection, Path file) throws IOException {
        Path parent = file.toAbsolutePath().getParent();
        if (parent != null) {
            Files.createDirectories(parent);
        }
        try (ObjectOutputStream output = new ObjectOutputStream(Files.newOutputStream(file))) {
            output.writeObject(collection);
        }
    }

    public PetCollection load(Path file) throws IOException {
        if (Files.notExists(file)) {
            return new PetCollection();
        }
        try (ObjectInputStream input = new ObjectInputStream(Files.newInputStream(file))) {
            Object loaded = input.readObject();
            if (loaded instanceof PetCollection collection) {
                return collection;
            }
            throw new IOException("The saved file does not contain a pet collection.");
        } catch (ClassNotFoundException exception) {
            throw new IOException("The saved file uses an unknown pet type.", exception);
        }
    }
}
