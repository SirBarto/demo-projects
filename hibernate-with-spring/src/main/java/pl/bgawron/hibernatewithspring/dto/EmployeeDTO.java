package pl.bgawron.hibernatewithspring.dto;

import pl.bgawron.hibernatewithspring.model.Employee;
import pl.bgawron.hibernatewithspring.model.Gender;

import java.io.Serializable;
import java.util.Date;

public class EmployeeDTO implements Serializable
{

    private Long id;
    private String firstName, lastName;
    private final Gender gender;
    private Date createAt;

    /**
     * @param firstName Employee firstName
     * @param lastName Employee lastName
     * @param gender Gender gender
     */

    public EmployeeDTO(String firstName, String lastName, Gender gender, Date createAt) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.createAt = createAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Gender getGender() {
        return gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    @Override
    public String toString() {

        var builder = new StringBuilder();
        builder.append("Employee{id=").append(id)
                .append(", firstName=").append(firstName)
                .append(", lastName=").append(lastName)
                .append(", gender=").append(gender)
                 .append("}");

        return builder.toString();
    }

}
