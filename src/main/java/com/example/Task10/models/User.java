package com.example.Task10.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2,max = 15, message = "Name should have 2-15 characters")
    @Pattern(regexp = "[A-Z]\\w+",message = "Name should be valid")
    private String name;

    @Column
    @NotEmpty(message = "Surname should not be empty")
    @Size(min = 2,max = 15, message = "Surname should have 2-15 characters")
    @Pattern(regexp = "[A-Z]\\w+",message = "Surname should be valid")
    private String surname;

    @Column
    @NotEmpty(message = "Nickname should not be empty")
    private String nickname;

    @Column
    @NotEmpty(message = "email should not be empty")
    @Email(message = "email should be valid")
    private String email;
    @Column
    @NotEmpty
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
                joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles = new ArrayList<>();



    public User() {
    }


    public void add(Role role) {
        this.roles.add(role);
    }
    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> role) {
        this.roles = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
