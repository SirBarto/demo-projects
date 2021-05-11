package pl.bgawron.hibernatewithspring.repository.dao;

import pl.bgawron.hibernatewithspring.model.Dog;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {

    Optional<T> get(long id);

    List<T> getAll();

    void save(T t);

    void update(Dog t, String[] params);

    void delete(T t);
}
