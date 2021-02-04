package ru.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.addressbook.model.ContactData;
import ru.addressbook.model.GroupData;

import java.util.List;

public class ContactCreationTest extends TestBase {


    @Test
    public void testContactCreation() throws Exception {
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().initContactCreation();
        app.getContactHelper().fillNewContact(new ContactData("Федор", "Достоевский", "Оренбург", "alina.yahina37@gmail.com"));
        app.getContactHelper().clickButton();
        app.getContactHelper().returnToContactPage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);
    }
}

