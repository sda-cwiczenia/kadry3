package com.sda.kadry3.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.pl.PESEL;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

// DTO czyli Data Transfer Object będzie pomocny do przechwytywania danych z formularzy i przekazywania do innych metod
public class EmployeeDTO {
    @NotEmpty(message = "Pole imie nie może być puste")
    @Size(min=3)
    private String firstName;
    //@PESEL  sprawdzanie polskiego PESEL
    @NotEmpty
    @Size(min=3)
    @Length(min=3, max = 300)
    private String lastName;
    private int age;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
