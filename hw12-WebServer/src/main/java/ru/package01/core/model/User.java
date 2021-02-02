package ru.package01.core.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "login", unique = true, nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private AddressDataSet address;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<PhoneDataSet> phones;


    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAddress(AddressDataSet address) {
        this.address = address;
    }

    public void setPhone(List<PhoneDataSet> phones) {
        this.phones = phones;
    }


    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return String.valueOf(age);
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public AddressDataSet getAddress() {
        return address;
    }

    public List<PhoneDataSet> getPhones() {
        return phones;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", address=" + address +
                ", phone=" + phones +
                '}';
    }
}
