package pl.bgawron.hibernatewithspring.integrate;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.bgawron.hibernatewithspring.model.Employee;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class EmployeeApiControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

   // @Autowired Flyway flyway; /* Cleaning after tests */

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void shouldReturnSelectedEmployee() throws Exception {
        //give
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/employeeApi/all"))
                .andExpect(MockMvcResultMatchers.status().is(200)).andReturn();
        //when
        Employee[] employees = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Employee[].class);
        //then
        Assertions.assertEquals("Adrian",employees[0].getFirstName());
    }

    @Test
    public void shouldReturn404ThenGet() throws Exception {
        //give
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/employeeApi/employee/6"))
                .andExpect(MockMvcResultMatchers.status().is(404)).andReturn();
        //when
        String actual = mvcResult.getResolvedException().getMessage();
        //then
        Assertions.assertEquals("No employee with such id: 6",actual);
    }

    @Test
    public void shouldReturnCorrectStatusCodeWhenAddEmployee()
    {
        //give
        //when
        //then
    }

    @Test
    public void shouldReturnCorrectStatusCodeWhenUpdateEmployee()
    {
        //give
        //when
        //then
    }

    @Test
    public void shouldReturnCorrectStatusCodeWhenDeleteEmployee()
    {
        //give
        //when
        //then
    }

}
