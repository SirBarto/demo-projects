package pl.bgawron.hibernatewithspring.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.bgawron.hibernatewithspring.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeApi {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeApi(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
/*
    @GetMapping("/{id}")
    public ResponseEntity<?> findEmployeeById(@PathVariable long id)
    {
        return employeeService.findOne(id)
                .map(EmployeeDTO::)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }*/
}
