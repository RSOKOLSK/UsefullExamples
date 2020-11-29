package jackson;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JacksonPolymorphicExample {
    public static void main(String[] args) throws IOException {
        Dog dog = new Dog("Reksio", true);
        Cat cat = new Cat("Kitek", false);

        String resultDog = new ObjectMapper().writeValueAsString(dog);
        String resultCat = new ObjectMapper().writeValueAsString(cat);

        System.out.println("SERIALIZATION");
        System.out.println(resultDog);
        System.out.println(resultCat);
        System.out.println();

        String json = "{\"name\":\"lacy\",\"type\":\"cat\",\"claws\":\"true\"}";

        Animal animal = new ObjectMapper()
                .reader(Animal.class)
                .readValue(json);

        System.out.println("DESERIALIZATION");
        System.out.println(animal.getName());
        System.out.println(((Cat) animal).getClaws());
    }
}

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Dog.class, name = "dog"),
        @JsonSubTypes.Type(value = Cat.class, name = "cat")
})
interface Animal {
    String getName();
}

@JsonTypeName("dog")
class Dog implements Animal{
    Boolean barks;
    String name;

    public Dog() {}

    public Dog(String name, Boolean barks) {
        this.name = name;
        this.barks = barks;
    }

    @Override
    public String getName() {
        return name;
    }

    public Boolean getBarks() {
        return barks;
    }
}

@JsonTypeName("cat")
class Cat implements Animal{
    Boolean claws;
    String name;

    public Cat() {}

    public Cat(String name, Boolean claws) {
        this.name = name;
        this.claws = claws;
    }

    @Override
    public String getName() {
        return name;
    }

    public Boolean getClaws() {
        return claws;
    }
}