package com.piotrek.apps.orderHaircutApp.dto;

import com.piotrek.apps.orderHaircutApp.entity.Role;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class UserDto {

    private int id;

    @NotNull(message = "pole wymagane")
    @Size(min = 1, message = "pole wymagane")
    private String userName;

    @NotNull(message = "pole wymagane")
    @Size(min = 1, message = "pole wymagane")
    private String password;

    @Email
    @NotNull(message = "pole wymagane")
    @Size(min = 1, message = "pole wymagane")
    private String email;

    @NotNull(message = "pole wymagane")
    @Size(min = 1, message = "pole wymagane")
    private String firstName;

    @NotNull(message = "pole wymagane")
    @Size(min = 1, message = "pole wymagane")
    private String lastName;

    private List<Role> role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public List<Role> getRole() {
        return role;
    }

    public void setRole(List<Role> role) {
        this.role = role;
    }
}
