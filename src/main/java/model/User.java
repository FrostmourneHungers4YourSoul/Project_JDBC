package model;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

@Table
public class User {

    @Id
    private Long id;

    @Column
    private String name;

    @Column
    private String lastName;

    @Column
    private Byte age;


    public User(String name, String lastName, Byte age){
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }
    public User(long id, String name, String lastName, Byte age){
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }

    public Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User: " + id  +" [" +
               " " + name +
               " " + lastName +
               " age: " + age +
               " ]";
    }
}
