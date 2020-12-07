package ru.itis.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

public class SignUpForm {
    @NotEmpty
    @Length(min = 1, max = 20)
    private String firstName;
    @NotEmpty
    @Length(min = 1, max = 20)
    private String lastName;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    @Length(min = 8, max = 30)
    private String password;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
