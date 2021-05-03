package pl.bgawron.hibernatewithspring.model;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "TBL_EMPLOYEE")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName, lastName;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @CreatedDate
    @Column(name = "createdDate", nullable = false, updatable = false)
    private Date createdAt;

    public Employee() {
    }

    public Employee(String firstName, String lastName, Gender gender,Date createAt) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.createdAt = createAt;
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(firstName, employee.firstName) &&
                Objects.equals(lastName, employee.lastName) &&
                Objects.equals(gender, employee.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, gender);
    }

    @Override
    public String toString() {

        var builder = new StringBuilder();
        builder.append("Employee{id=").append(id)
                .append(", firstName=").append(firstName)
                .append(", lastName=").append(lastName)
                .append(", gender=").append(gender)
                .append(", createdAt=").append(createdAt)
                .append("}");

        return builder.toString();
    }
}
