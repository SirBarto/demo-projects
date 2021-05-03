package pl.bgawron.hibernatewithspring.dao;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import pl.bgawron.hibernatewithspring.model.Employee;
import pl.bgawron.hibernatewithspring.model.Gender;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class EmployeeDAOTest {

    @Autowired
    private EmployeeDAO employeeDAO;

    Employee employee = new Employee();

    @Test
    @Rollback(value = false)
    void addEmployeeTest()
    {
        //given
        employee.setFirstName("User");
        employee.setLastName("Testowy");
        employee.setGender(Gender.FEMALE);
        employee.setCreatedAt(new Date());

        //when
        Employee savedEmployee = employeeDAO.addEmployee(employee);

        //then
        assertNotNull(savedEmployee);
        assertTrue(employee.getId()>0);
    }

    @Test
    @Rollback(value = true)
    void getByIdExist()
    {
        //given
        int id = 1;

        //when
        Optional<Employee> employee1 = employeeDAO.getById(id);

        //then
        assertEquals(employee1.get().getFirstName(),"User");
    }

    @Test
    @Rollback(value = false)
    void updateEmployee()
    {
        //given
        employee.setId((long) 2);
        employee.setFirstName("Adrian");
        employee.setLastName("Tester");
        employee.setGender(Gender.MALE);

        //when
        employeeDAO.updateEmployee(employee);

        //then
        assertEquals(employee.getFirstName(),"Adrian");
        assertEquals(employee.getLastName(),"Tester");
    }

    @Test
    @Rollback(value = false)
    void deleteEmployee()
    {
        //given
        int id = 1;
        boolean isExists = employeeDAO.getById(id).isPresent();

        //when
        employeeDAO.deleteEmployee(id);
        boolean notExist = employeeDAO.getById(id).isPresent();

        //then
        assertTrue(isExists);
        assertFalse(notExist);
    }

}