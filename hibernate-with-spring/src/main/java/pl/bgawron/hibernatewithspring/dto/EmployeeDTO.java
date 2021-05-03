package pl.bgawron.hibernatewithspring.dto;

import pl.bgawron.hibernatewithspring.model.Gender;

public class EmployeeDTO
{

    private Long id;
    private String firstName, lastName;
    private final Gender gender;

    public EmployeeDTO(Long id, String firstName, String lastName, Gender gender) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
