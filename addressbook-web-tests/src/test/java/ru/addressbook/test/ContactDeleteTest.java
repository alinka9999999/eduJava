package ru.addressbook.test;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.addressbook.model.ContactData;
import ru.addressbook.model.Contacts;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeleteTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.contact().clickToHome();
        if(app.db().contacts().size()==0){
            app.contact().create(new ContactData()
                    .withName("Михаил").withLastname("Лермонтов").withAddress("Кисловодск").withEmail("alina@mail.com"));
        }
        if (app.contact().list().size() == 0) {
            app.contact().create(new ContactData()
                    .withName("Михаил").withLastname("Лермонтов").withAddress("Кисловодск").withEmail("alina@mail.com"));
        }
    }

    @Test(enabled = true)
    public void deletionContact() {
        Contacts before = app.db().contacts();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        app.contact().clickToHome();
        Contacts after = app.db().contacts();
        assertEquals(after.size(), before.size() - 1);

        assertThat(after, CoreMatchers.equalTo(before.without(deletedContact)));
        verifyGroupListInUI();
    }

    @Test(enabled = true)
    public void deletionBadContact() {
        Contacts before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        app.contact().clickToHome();
        assertEquals(app.contact().count(), before.size() - 1);
        Contacts after = app.contact().all();

        assertThat(after, CoreMatchers.equalTo(before.without(deletedContact)));
        verifyGroupListInUI();
    }
}
