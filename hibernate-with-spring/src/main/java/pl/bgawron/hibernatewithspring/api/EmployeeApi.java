package pl.bgawron.hibernatewithspring.api;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.bgawron.hibernatewithspring.dto.EmployeeDTO;
import pl.bgawron.hibernatewithspring.exception.status.NotFoundException;
import pl.bgawron.hibernatewithspring.model.Employee;
import pl.bgawron.hibernatewithspring.service.EmployeeServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employeeApi")
public class EmployeeApi {

    private final EmployeeServiceImpl employeeServiceImpl;

    @Autowired
    public EmployeeApi(EmployeeServiceImpl employeeServiceImpl)
    {
        this.employeeServiceImpl = employeeServiceImpl;
    }

    @GetMapping("/all")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees()
    {
        return ResponseEntity.ok().body(employeeServiceImpl.getAllEmployees());
    }

    @ApiOperation(value = "Find employee by id",notes = "provide information about employee by id")
    @GetMapping("/employee/{id}")
    public ResponseEntity<EmployeeDTO> findEmployeeById(@ApiParam(value = "unique id of employee",example = "1") @PathVariable(value = "id") Long id)
    {
        return ResponseEntity.ok().body(employeeServiceImpl.findEmployeeById(id).get());
    }

    @PostMapping("/employee")
    public Employee createEmployee(@RequestBody EmployeeDTO dto)
    {
        return employeeServiceImpl.createEmployee(dto);
    }

    @PutMapping("/employee/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,
            @RequestBody Employee detailsEmployee)
            throws NotFoundException
    {
        Employee employee = employeeServiceImpl.getEmployeeId(employeeId)
                .orElseThrow(() -> new NotFoundException("Employee not found"+employeeId));

        employee.setFirstName(detailsEmployee.getFirstName());
        employee.setLastName(detailsEmployee.getLastName());
        final Employee updateEmployee = employeeServiceImpl.updateEmployee(employee);
        return ResponseEntity.ok(updateEmployee);
    }

    @DeleteMapping("/employee/{id}")
    public Map<String,Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId)
            throws NotFoundException
    {
        Employee employee = employeeServiceImpl.getEmployeeId(employeeId)
                .orElseThrow(() -> new NotFoundException("Employee not found"+employeeId));

        employeeServiceImpl.deleteEmployee(employee);
        Map<String,Boolean> response = new HashMap<>();
        response.put("delete",Boolean.TRUE);
        return response;
    }




}
