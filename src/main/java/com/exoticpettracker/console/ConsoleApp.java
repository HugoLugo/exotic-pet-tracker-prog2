package com.exoticpettracker.console;

import com.exoticpettracker.domain.InvalidPetException;
import com.exoticpettracker.domain.Pet;
import com.exoticpettracker.persistence.PetStorage;
import com.exoticpettracker.service.PetCollection;
import com.exoticpettracker.service.PetFactory;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

/** The single required interface: a small, text-based menu. */
public final class ConsoleApp {
    private static final Path DATA_FILE = Path.of("data", "pets.dat");

    private final Scanner scanner;
    private final PetFactory factory = new PetFactory();
    private final PetStorage storage = new PetStorage();
    private PetCollection collection;

    private ConsoleApp(Scanner scanner) {
        this.scanner = scanner;
    }

    public static void main(String[] args) {
        new ConsoleApp(new Scanner(System.in)).run();
    }

    private void run() {
        try {
            collection = storage.load(DATA_FILE);
            System.out.println("Loaded " + collection.size() + " pet(s).");
        } catch (IOException exception) {
            System.out.println("Could not read saved pets: " + exception.getMessage());
            System.out.println("The saved file was not modified. Fix it before starting again.");
            return;
        }

        while (true) {
            System.out.println("\n=== EXOTIC PET TRACKER ===");
            System.out.println("1. Add pet");
            System.out.println("2. View pets (sorted by name)");
            System.out.println("3. Exit");
            System.out.print("Choose: ");

            if (!scanner.hasNextLine()) {
                return;
            }
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> addPet();
                case "2" -> showPets();
                case "3" -> { return; }
                default -> System.out.println("Choose 1, 2 or 3.");
            }
        }
    }

    private void addPet() {
        System.out.print("Type (1 = Reptile, 2 = Arachnid): ");
        if (!scanner.hasNextLine()) return;
        String option = scanner.nextLine().trim();
        String type = switch (option) {
            case "1" -> "reptile";
            case "2" -> "arachnid";
            default -> null;
        };
        if (type == null) {
            System.out.println("Choose 1 or 2 for the pet type.");
            return;
        }

        System.out.print("Name: ");
        if (!scanner.hasNextLine()) return;
        String name = scanner.nextLine();
        System.out.print("Species: ");
        if (!scanner.hasNextLine()) return;
        String species = scanner.nextLine();

        try {
            Pet pet = factory.createPet(type, name, species);
            collection.addPet(pet);
            try {
                storage.save(collection, DATA_FILE);
                System.out.println("Pet added and saved.");
            } catch (IOException exception) {
                System.out.println("Pet added in memory but NOT saved: " + exception.getMessage());
            }
        } catch (InvalidPetException exception) {
            System.out.println("Invalid pet: " + exception.getMessage());
        }
    }

    private void showPets() {
        List<Pet> pets = collection.getPetsSortedByName();
        if (pets.isEmpty()) {
            System.out.println("No pets registered yet.");
            return;
        }
        for (int i = 0; i < pets.size(); i++) {
            // Polymorphism: the concrete pet decides the description at runtime.
            System.out.println((i + 1) + ". " + pets.get(i).getDescription());
        }
    }
}
