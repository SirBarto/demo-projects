package pl.bgawron.hibernatewithspring.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TBL_CATS")
public class Cat implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Cat{"+"id="+id+", name="+name+", age="+age+"}";
    }

}
