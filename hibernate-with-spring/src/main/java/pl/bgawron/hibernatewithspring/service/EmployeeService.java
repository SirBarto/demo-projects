package pl.bgawron.hibernatewithspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bgawron.hibernatewithspring.dao.EmployeeDAO;
import pl.bgawron.hibernatewithspring.model.Employee;

import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeService(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    public Optional<Employee> findById(long id)
    {
        return employeeDAO.getById(id);
    }

}
