package pl.bgawron.hibernatewithspring.service;

import pl.bgawron.hibernatewithspring.dto.EmployeeDTO;
import pl.bgawron.hibernatewithspring.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    List<EmployeeDTO> getAllEmployees();

    Optional<EmployeeDTO> findEmployeeById(Long id);

    Optional<Employee> getEmployeeId(Long id);

    Employee createEmployee(EmployeeDTO employeeDTO);

    Employee updateEmployee(Employee employeeDetails);

    void deleteEmployee(Employee employee);
}
