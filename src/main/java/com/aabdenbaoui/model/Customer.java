package com.aabdenbaoui.model;

import java.util.regex.Pattern;

public class Customer {

    private final String firstName;
    private final String lastName;
    private final String email;

    String  regexPattern =  "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    Pattern pattern = Pattern.compile(regexPattern);

    public Customer(String email, String firstName, String lastName) {
        if(!pattern.matcher(email).matches()){
            throw new IllegalArgumentException("Error Invalid email");
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }


    public String getLastName() {
        return lastName;
    }


    public String getEmail() {
        return email;
    }


    @Override
    public String toString() {
        return "firstName: " + firstName  + ", lastName: " + lastName  + ", email: " + email;
    }
}
