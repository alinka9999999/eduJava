package ru.addressbook.test;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.addressbook.model.ContactData;
import ru.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.contact().clickToHome();
        if (app.contact().list().size() == 0) {
            app.contact().create(new ContactData()
                    .withName("Михаил").withLastname("Лермонтов").withAddress("Кисловодск").withEmail("alina@mail.com"));
        }
    }

    @Test(enabled = true) //перегенирирован equals and hash
    public void testContactCreation() throws Exception {
        Contacts before = app.contact().all();
        File photo = new File("src/test/resources/92f.png");
        ContactData contact = new ContactData()
                .withName("Федор").withLastname("Достоевский").withPhoto(photo);
        app.contact().create(contact);
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();

        assertThat(after, CoreMatchers.equalTo(before.
                withAdded(contact.withId(after.stream().mapToInt(ContactData::getId).max().getAsInt()))));
    }


    @Test(enabled = true)
    public void testBadContactCreation() throws Exception {
        Contacts before = app.contact().all();
        ContactData contact = new ContactData()
                .withName("Федор'").withLastname("Достоевский").withAddress("Оренбург").withEmail("alina.yahina37@gmail.com");
        app.contact().create(contact);
        Contacts after = app.contact().all();
        assertThat(app.contact().count(), equalTo(before.size()));

        assertThat(after, CoreMatchers.equalTo(before));
    }
}

