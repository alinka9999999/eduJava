package ru.addressbook.test;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.Test;
import ru.addressbook.model.ContactData;
import ru.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest extends TestBase {


    @Test(enabled = true)
    public void testContactCreation() throws Exception {
        Contacts before = app.contact().all();
        ContactData contact = new ContactData()
                .withName("Федор").withLastname( "Достоевский").withAddress( "Оренбург").withEmail( "alina.yahina37@gmail.com");
        app.contact().create(contact);
        Contacts after = app.contact().all();
        assertThat(after.size(),equalTo( before.size() + 1));


        assertThat(after, CoreMatchers.equalTo(before.
                withAdded(contact.withId(after.stream().mapToInt(ContactData::getId).max().getAsInt()))));
    }
}

