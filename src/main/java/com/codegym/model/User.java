package com.codegym.model;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Component
public class User implements Validator {
    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Size(min = 5, max = 45)
    private String firstName;
    private String lastName;

    private String number;

    @Min(18)
    private int age;

    private String email;

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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }


    @Override
    public void validate(Object target, Errors errors) {
        User usr = (User) target;
        String lastName = usr.getLastName();
        String firstName = usr.getFirstName();
        int age = usr.getAge();
        String number = usr.getNumber();
        String email = usr.getEmail();
        ValidationUtils.rejectIfEmpty(errors, "firstName", "firstName.empty");
        ValidationUtils.rejectIfEmpty(errors, "lastName", "lastName.empty");
        ValidationUtils.rejectIfEmpty(errors, "number", "number.empty");
        if (lastName.length() > 45 || lastName.length() < 5) {
            errors.rejectValue("lastName", "lastName.length()");
        }

        if (age < 18) {
            errors.rejectValue("age", "age.value()");
        }

        if (firstName.length() > 45 || firstName.length() < 5) {
            errors.rejectValue("firstName", "firstName.length()");
        }

        if (number.length() > 11 || number.length() < 10) {
            errors.rejectValue("number", "number.length()");
        }
        if (!number.startsWith("0")) {
            errors.rejectValue("number", "number.startsWith");
        }

        if (!number.matches("(^$|[0-9]*$)")) {
            errors.rejectValue("number", "number.matches");
        }

        ValidationUtils.rejectIfEmpty(errors, "email", "email.empty");
        if (email.matches("^\\w*\\@\\w*\\.com$")) {
            errors.rejectValue("email", "email.matches");
        }
    }
}
