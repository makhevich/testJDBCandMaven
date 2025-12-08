package jm.task.core.jdbc.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

// Аннотации @Table, @Id, @Column нужны для Hibernate,

@Table // Это значит таблица
public class User {
    @Id
    private Long id;

    @Column // Это поле — колонка в таблице
    private String name;

    @Column
    private String lastName;

    @Column
    private Byte age;

    public User() { // пустой констрктор нужен для Hibernate
    }

    public User(String name, String lastName, Byte age) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }

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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
        this.age = age;
    }

    // Дефолтный выводит адресс в памяти, а этот на русском будет выводить.
    @Override

    // склеим строку "User{id=1, name='Ivan'...}".
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +  // escape character
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }

}


