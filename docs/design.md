# Design documentation

## Problem statement

**User:** One exotic pet keeper with a small personal collection.

**Goal:** Record basic details of reptiles and arachnids, view the collection alphabetically, and find the same records after restarting the program.

**Problem:** An in-memory list disappears when a program closes. The application must maintain meaningful objects while saving them reliably across sessions.

## User stories

1. As an exotic pet keeper, I want to add a reptile or arachnid with a name and species so that I can keep track of my animals.
2. As an exotic pet keeper, I want to view all my pets alphabetically so that I can find an animal quickly.
3. As an exotic pet keeper, I want my list saved and reloaded so that I do not lose entries when I close the program.
4. As an exotic pet keeper, I want invalid entries to be rejected so that records contain meaningful information.

## Main design decisions

1. The console has three options: add, view sorted, and exit. No GUI is necessary.
2. `Pet` is abstract, with shared validated state and a polymorphic description method. `Reptile` and `Arachnid` override the method.
3. `PetFactory` follows the Factory pattern: the console uses `Pet` rather than directly choosing a concrete class constructor.
4. `PetCollection` aggregates `0..*` pets in an `ArrayList<Pet>` and returns a sorted, read-only copy. It never exposes its mutable internal list.
5. `PetStorage` writes and reads the collection with Java serialization and try-with-resources. Data is saved after each successful addition.
6. The custom unchecked `InvalidPetException` rejects empty names/species and unknown types. File read errors are shown to the user; corrupted saved data is not overwritten automatically.
7. JUnit tests provide clean-checkout evidence despite the rubric's inconsistent wording about optional testing.

## Known limitations

- Reptile and arachnid are the only supported categories.
- No editing, deleting, searching, authentication, or GUI.
- Java-serialized data is intended only for the trusted local app and is not human-readable or exchangeable with other systems.
- If a save fails, the new pet remains in memory for the current session but is explicitly reported as unsaved. The user should resolve the storage problem before exiting.
