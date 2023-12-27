package com.example.customer;

public class CustomerDetails {
    private long customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String city;
    private long phoneNumber;

    public CustomerDetails() {
    }

    public CustomerDetails(long customerId) {
        this.customerId = customerId;
    }

    public CustomerDetails(String firstName, String lastName, String email, String city, long phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.city = city;
        this.phoneNumber = phoneNumber;
    }

    public CustomerDetails(int customerId, String firstName, String lastName, String email, String city, long phoneNumber) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.city = city;
        this.phoneNumber = phoneNumber;
    }

    public long getCustomer() {
        return customerId;
    }

    public void setCustomer(int customer) {
        this.customerId = customer;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
