package ru.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.addressbook.model.ContactData;

import java.util.List;

public class ContactDeleteTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (!app.getContactHelper().isThereContact()) {
            app.getContactHelper().createContact(new ContactData("Михаил", "Лермонтов", "Кисловодск", "a@m.ru"));
        }
    }

    @Test(enabled = true)
    public void deletionContact() {
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().deleteContact();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        for (int i = 0; i < after.size(); i++) {
            Assert.assertEquals(before.get(1), after.get(1));
        }
    }
}
