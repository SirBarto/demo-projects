package pl.bgawron.hibernatewithspring.api;

import org.springframework.beans.NotReadablePropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.bgawron.hibernatewithspring.dto.EmployeeDTO;
import pl.bgawron.hibernatewithspring.exception.ApiExceptionHandler;
import pl.bgawron.hibernatewithspring.exception.status.NotFoundException;
import pl.bgawron.hibernatewithspring.model.Employee;
import pl.bgawron.hibernatewithspring.service.EmployeeServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employee")
public class EmployeeApi {

    private final EmployeeServiceImpl employeeServiceImpl;

    @Autowired
    public EmployeeApi(EmployeeServiceImpl employeeServiceImpl) {
        this.employeeServiceImpl = employeeServiceImpl;
    }

    @GetMapping("/all")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees()
    {
        return ResponseEntity.ok().body(employeeServiceImpl.getAllEmployees());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> findEmployeeById(@PathVariable(value = "id") Long id)
    {
        return ResponseEntity.ok().body(employeeServiceImpl.findById(id).get());
    }

    @PostMapping("/employee")
    public Employee createEmployee(@RequestBody EmployeeDTO dto)
    {
        return employeeServiceImpl.createEmployee(dto);
    }

    @PutMapping("/employee/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,
            @RequestBody Employee detailsEmployee)
    {
        Employee employee = employeeServiceImpl.findById(employeeId).orElseThrow(
                () -> throw new NotFoundException("Employee not found: "+employeeId));

        employee.setId(detailsEmployee.getId());
        employee.setFirstName(detailsEmployee.getFirstName());
        employee.setLastName(detailsEmployee.getLastName());
        final Employee updateEmployee = employeeServiceImpl.updateEmployee(detailsEmployee);
        return ResponseEntity.ok(updateEmployee);
    }

    @DeleteMapping("/employee/{id}")
    public Map<String,Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId)
    {
        Employee employee = employeeServiceImpl.findById(employeeId).orElseThrow(
                ()-> throw new NotFoundException("Employee not found: "+employeeId));

        employeeServiceImpl.deleteEmployee(employee);
        Map<String,Boolean> response = new HashMap<>();
        response.put("delete",Boolean.TRUE);
        return response;
    }




}
