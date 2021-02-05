package ru.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactModificationTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().list().size() == 0) {
            app.contact().create(new ContactData()
                    .withName("Михаил").withLastname( "Лермонтов").withAddress( "Кисловодск").withEmail("alina@mail.com"));
        }
    }

    @Test(enabled = true)
    public void modificationContact() {
        Set<ContactData> before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId())
                .withName("Александр").withLastname( "Пушкин").withAddress( "Москва").withEmail( "alina@mail.com");
        app.contact().modify(contact);
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size());


        before.remove(modifiedContact);
        before.add(contact);
        Assert.assertEquals(before, after);
    }
}
