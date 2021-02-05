package ru.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactCreationTest extends TestBase {


    @Test(enabled = true)
    public void testContactCreation() throws Exception {
        Set<ContactData> before = app.contact().all();
        ContactData contact = new ContactData()
                .withName("Федор").withLastname( "Достоевский").withAddress( "Оренбург").withEmail( "alina.yahina37@gmail.com");
        app.contact().create(contact);
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() + 1);


        contact.withId(after.stream().mapToInt(ContactData::getId).max().getAsInt());
        before.add(contact);
        Assert.assertEquals(before, after);
    }
}

