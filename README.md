# Exotic Pet Tracker — Programming 2

Small Java console application for recording reptiles and arachnids. It demonstrates the object-oriented programming and repository requirements of the Programming 2 assignment without extra features.

## Problem and user

An exotic pet keeper needs a simple list of their animals that is retained between application runs. This project lets the keeper register a pet and view the animals in alphabetical order.

## Prerequisites

- JDK 17 or later
- Apache Maven 3.8 or later

## Build and test from a clean checkout

```sh
mvn clean verify
```

This compiles the application, runs its JUnit tests, and creates `target/exotic-pet-tracker-1.0.0.jar`.

## Run

```sh
java -jar target/exotic-pet-tracker-1.0.0.jar
```

Run from the repository directory so the program uses the same `data/pets.dat` file each time. The `data/` folder is created automatically and is excluded from Git.

## Example usage

```text
Loaded 0 pet(s).

=== EXOTIC PET TRACKER ===
1. Add pet
2. View pets (sorted by name)
3. Exit
Choose: 1
Type (1 = Reptile, 2 = Arachnid): 1
Name: Sandy
Species: Kenyan sand boa
Pet added and saved.

=== EXOTIC PET TRACKER ===
1. Add pet
2. View pets (sorted by name)
3. Exit
Choose: 2
1. Sandy - Reptile (Kenyan sand boa)
```

After restarting, saved pets are loaded automatically. Blank fields and invalid choices are rejected; unreadable saved data is reported without being overwritten.

## Implemented rubric requirements

| Requirement | Implementation |
| --- | --- |
| Encapsulation | `Pet` has private immutable fields and validated constructors; `PetCollection` protects its list. |
| Abstraction | Abstract `Pet` class defines `getDescription()`. |
| Inheritance and polymorphism | `Reptile` and `Arachnid` override `getDescription()`, called through a `Pet` reference. |
| Relationships and multiplicities | One `PetCollection` aggregates zero or more `Pet` objects. |
| Generics and sorting | `ArrayList<Pet>` and `Comparator` sort by name and then species. |
| Exceptions | `InvalidPetException` enforces required pet fields/types; I/O failures are handled. |
| Persistence | `PetStorage` reads/writes a local Java-serialized collection with try-with-resources. |
| Design pattern | **Factory**: `PetFactory` creates `Reptile` or `Arachnid` and returns the shared `Pet` type. |
| Testing | JUnit 4 tests cover creation/validation, polymorphism, sorting and persistence. |
| Build | Standard Maven layout and runnable JAR. |

## Design pattern: Factory

`PetFactory` is the factory. `Pet` is its common product type, and `Reptile` and `Arachnid` are concrete products. `ConsoleApp` asks the factory to create an animal instead of constructing the two product types itself. This keeps creation and validation in one place and leaves the client working with `Pet` objects.

The matching UML classes and relationships are in [docs/class-diagram.md](docs/class-diagram.md).

## Documentation

- [Problem, user stories, design choices, and limitations](docs/design.md)
- [UML class diagram](docs/class-diagram.md)
- [Release notes draft](docs/release-notes.md)

## Known limitations

- Supports two animal categories only: reptiles and arachnids.
- Supports adding and viewing pets, not editing or deleting them.
- Stores data locally using Java serialization, not a database or portable text format.
- Intended for a single user on a trusted machine; it should not open untrusted serialized files.

## Contributor

- HugoLugo (individual coursework project)

## License

MIT — see [LICENSE](LICENSE).
