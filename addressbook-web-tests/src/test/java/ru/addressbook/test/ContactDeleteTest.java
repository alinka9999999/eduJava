package ru.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.addressbook.model.ContactData;

import java.util.List;

public class ContactDeleteTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().list().size() == 0) {
            app.contact().create(new ContactData()
                    .withName("Михаил").withLastname( "Лермонтов").withAddress( "Кисловодск").withEmail( "alina@mail.com"));
        }
    }

    @Test(enabled = true)
    public void deletionContact() {
        List<ContactData> before = app.contact().list();
        app.contact().deleteContact();
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        for (int i = 0; i < after.size(); i++) {
            Assert.assertEquals(before.get(1), after.get(1));
        }
    }
}
