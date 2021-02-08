package ru.addressbook.model;

import java.util.Objects;

public class ContactData {
    private int id = Integer.MAX_VALUE;
    private String name;
    private String lastname;
    private String address;
    private String group;
    private String homePhone;
    private String mobilePhone;
    private String workPhone;
    private String allPhones;
    private String allEmails;
    private String email;
    private String email2;
    private String email3;

    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public ContactData withAllEmails(String allEmails){
        this.allEmails = allEmails;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withName(String name) {
        this.name = name;
        return this;
    }

    public ContactData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public ContactData withHomePhone(String homePhone) {
        this.homePhone = homePhone;
        return this;
    }

    public ContactData withMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    public ContactData withWorkPhone(String workPhone) {
        this.workPhone = workPhone;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(lastname, that.lastname) && Objects.equals(address, that.address) && Objects.equals(email, that.email) && Objects.equals(group, that.group) && Objects.equals(homePhone, that.homePhone) && Objects.equals(mobilePhone, that.mobilePhone) && Objects.equals(workPhone, that.workPhone) && Objects.equals(allPhones, that.allPhones) && Objects.equals(allEmails, that.allEmails) && Objects.equals(email2, that.email2) && Objects.equals(email3, that.email3);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastname, address, email, group, homePhone, mobilePhone, workPhone, allPhones, allEmails, email2, email3);
    }

    public int getId() {
        return id;
    }


    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", group='" + group + '\'' +
                ", homePhone='" + homePhone + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", workPhone='" + workPhone + '\'' +
                ", allPhones='" + allPhones + '\'' +
                ", allEmails='" + allEmails + '\'' +
                ", email2='" + email2 + '\'' +
                ", email3='" + email3 + '\'' +
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

    public String getGroup() {
        return group;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public String getAllPhones() {
        return allPhones;
    }

    public String getEmail2() {
        return email2;
    }

    public String getAllEmails() {
        return allEmails;
    }

    public String getEmail3() {
        return email3;
    }
}