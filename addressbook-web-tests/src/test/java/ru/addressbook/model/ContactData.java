package ru.addressbook.model;

import java.util.Objects;

public class ContactData {
    private final String id;
    private final String name;
    private final String lastname;
    private final String address;
    private final String email;

    public ContactData(String name, String lastname, String address, String email) {
        this.id = null;
        this.name = name;
        this.lastname = lastname;
        this.address = address;
        this.email = email;
    }

    public ContactData(String id, String name, String lastname, String address, String email) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.address = address;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(lastname, that.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastname);
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }
}
