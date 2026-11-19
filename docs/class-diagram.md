# UML class diagram

The following class diagram is rendered directly by GitHub. Its Mermaid source is editable.

```mermaid
classDiagram
    class Pet {
        <<abstract>>
        -String name
        -String species
        #Pet(name, species)
        +getName() String
        +getSpecies() String
        +getDescription()* String
    }
    class Reptile {
        +Reptile(name, species)
        +getDescription() String
    }
    class Arachnid {
        +Arachnid(name, species)
        +getDescription() String
    }
    class InvalidPetException {
        +InvalidPetException(message)
    }
    class PetFactory {
        +createPet(type, name, species) Pet
    }
    class PetCollection {
        -List~Pet~ pets
        +addPet(pet) void
        +size() int
        +getPetsSortedByName() List~Pet~
    }
    class PetStorage {
        +save(collection, file) void
        +load(file) PetCollection
    }
    class ConsoleApp {
        -Scanner scanner
        -PetFactory factory
        -PetStorage storage
        -PetCollection collection
        +main(args) void
    }
    Reptile --|> Pet
    Arachnid --|> Pet
    PetCollection "1" o-- "0..*" Pet : stores
    PetFactory ..> Pet : creates
    PetFactory ..> InvalidPetException : throws
    PetStorage ..> PetCollection : reads / writes
    ConsoleApp ..> PetFactory : uses
    ConsoleApp ..> PetStorage : uses
    ConsoleApp ..> PetCollection : manages
```

Visibility: `-` private, `#` protected, `+` public. The hollow diamond shows aggregation: one collection stores zero or more pets. Hollow triangles indicate inheritance, and dashed arrows show dependencies.

## Factory pattern participants

- **Factory:** `PetFactory`
- **Common product:** `Pet`
- **Concrete products:** `Reptile` and `Arachnid`
- **Client:** `ConsoleApp`

`ConsoleApp` asks the factory for a `Pet` instead of directly constructing a concrete subtype. Runtime polymorphism occurs when a `Pet` reference calls `getDescription()`.
