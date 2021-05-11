package pl.bgawron.hibernatewithspring.repository;

import org.springframework.stereotype.Repository;
import pl.bgawron.hibernatewithspring.model.Dog;
import pl.bgawron.hibernatewithspring.repository.dao.Dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class DogDao implements Dao<Dog> {

    private final List<Dog> dogs = new ArrayList<>();

    public DogDao()
    {
        dogs.add(new Dog("Rex",3));
        dogs.add(new Dog("Luna",2));
    }

    @Override
    public Optional<Dog> get(long id) {
        return Optional.ofNullable(dogs.get((int) id));
    }

    @Override
    public List<Dog> getAll() {
        return dogs;
    }

    @Override
    public void save(Dog dog) {
        dogs.add(dog);
    }

    @Override
    public void update(Dog dog, String[] params) {
        dog.setName(Objects.requireNonNull(
                params[0],"Name cannot be null"));
        dogs.add(dog);
    }

    @Override
    public void delete(Dog dog) {
        dogs.remove(dog);
    }
}
