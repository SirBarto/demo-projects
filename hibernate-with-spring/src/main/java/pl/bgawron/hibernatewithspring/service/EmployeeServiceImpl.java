package pl.bgawron.hibernatewithspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bgawron.hibernatewithspring.dto.EmployeeDTO;
import pl.bgawron.hibernatewithspring.exception.status.NotFoundException;
import pl.bgawron.hibernatewithspring.repository.EmployeeDAOImpl;
import pl.bgawron.hibernatewithspring.model.Employee;

import javax.persistence.EntityNotFoundException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeDAOImpl employeeDAOImpl;

    @Autowired
    public EmployeeServiceImpl(EmployeeDAOImpl employeeDAOImpl) {
        this.employeeDAOImpl = employeeDAOImpl;
    }

    public List<EmployeeDTO> getAllEmployees()
    {
        List<EmployeeDTO> list = new ArrayList<>();
        List<Employee> employees = this.employeeDAOImpl.getAllEmployee();

        for(Employee emp : employees)
        {
            list.add(new EmployeeDTO(
                    emp.getFirstName(),
                    emp.getLastName(),
                    emp.getGender()
            ));
        }
        return list;
    }

    public Optional<EmployeeDTO> findEmployeeById(Long id) throws NotFoundException
    {
        Optional<Employee> employee = Optional.of(this.employeeDAOImpl.getById(id)
                .orElseThrow(() -> new NotFoundException("No employee with such id: " + id)));

        Employee emp = employee.get();

        return Optional.of(new EmployeeDTO(
                emp.getFirstName(),
                emp.getLastName(),
                emp.getGender()
        ));
    }

    public Employee createEmployee(EmployeeDTO employeeDTO)
    {
        return this.employeeDAOImpl.addEmployee(new Employee(
                employeeDTO.getFirstName(),
                employeeDTO.getLastName(),
                employeeDTO.getGender(),
                Date.valueOf(LocalDate.now())
        ));
    }

    public Employee updateEmployee(Employee employeeDetails)
    {
        return this.employeeDAOImpl.updateEmployee(employeeDetails);
        //return this.employeeDAOImpl.addEmployee(employeeDetails);
    }

    public void deleteEmployee(Employee employee)
    {
        this.employeeDAOImpl.deleteEmployee(employee);
    }

    public Optional<Employee> getEmployeeId(Long id)
    {

        Optional<Employee> employee = this.employeeDAOImpl.getById(id);
        Employee emp = employee.get();

        return Optional.of(emp);
    }
}
