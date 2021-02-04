package ru.addressbook.model;

import java.util.Objects;

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

    @Override
    public String toString() {
        return "ContactData{" +
                "name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return Objects.equals(name, that.name) && Objects.equals(lastname, that.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastname);
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
