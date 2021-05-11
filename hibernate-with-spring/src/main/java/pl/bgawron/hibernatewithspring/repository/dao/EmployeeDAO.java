package pl.bgawron.hibernatewithspring.repository.dao;

import pl.bgawron.hibernatewithspring.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeDAO {

    List<Employee> getAllEmployee();

    Optional<Employee> getById(Long id);

    Employee addEmployee(Employee employee);

    void updateEmployee(Employee employee);
    void deleteEmployee(Employee employee);
}
