package ru.addressbook;

public class ContactData {
    private final String name;
    private final String lastname;
    private final String city;
    private final String email;

    public ContactData(String name, String lastname, String city, String email) {
        this.name = name;
        this.lastname = lastname;
        this.city = city;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getCity() {
        return city;
    }

    public String getEmail() {
        return email;
    }
}
