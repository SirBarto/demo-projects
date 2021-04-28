package pl.bgawron.hibernatewithspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.bgawron.hibernatewithspring.dao.Dao;
import pl.bgawron.hibernatewithspring.dao.DogDao;
import pl.bgawron.hibernatewithspring.dao.EmployeeDAO;
import pl.bgawron.hibernatewithspring.model.Cat;
import pl.bgawron.hibernatewithspring.model.Dog;
import pl.bgawron.hibernatewithspring.model.Employee;

import java.util.Optional;

@SpringBootApplication
public class HibernateWithSpringApplication {

    private static Dao<Dog> dogDao;

    public static void main(String[] args) {
        SpringApplication.run(HibernateWithSpringApplication.class, args);

        //Wersja z Hiberante i wykorzystaniem CRUD-a

        //Wersja z mapowaniem po stronie Hibernate
        EmployeeDAO employeeDAO = new EmployeeDAO();
        Employee e1 = new Employee();
        e1.setFirstName("Jan");
        e1.setLastName("Nowak");
        employeeDAO.addEmployee(e1);

        Employee e2 = new Employee();
        e2.setFirstName("Mietek");
        e2.setLastName("Kowalski");
        employeeDAO.addEmployee(e2);

        //Wersja z Spring Data JPA i własnym mapowaniem obiektów
        Cat c1 = new Cat();
        c1.setName("Mruczek");
        c1.setAge(3);

        //Wersja z Własnym interfejsem DAO, lista obiektow w pamieci pelni roble DB
        dogDao = new DogDao();

        Dog d1 = getDog(0);
        System.out.println(d1);
        dogDao.update(d1,new String[]{"Ramzes","1"});

        Dog d2 = getDog(1);
        dogDao.delete(d2);
        dogDao.save(new Dog("Lena",2));

        dogDao.getAll().forEach(dog -> System.out.println(dog.getName()));

    }

    private static Dog getDog(long id)
    {
        Optional<Dog> dog = dogDao.get(id);
        return dog.orElseGet(
                () -> new Dog("non-existing dog",0));
    }

}
