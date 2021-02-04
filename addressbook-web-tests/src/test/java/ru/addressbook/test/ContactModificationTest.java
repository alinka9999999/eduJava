package ru.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.addressbook.model.ContactData;

import java.util.List;

public class ContactModificationTest extends TestBase {

    @Test
    public void modificationContact() {
        List<ContactData> before = app.getContactHelper().getContactList();
        if(!app.getContactHelper().isThereContact()) {
            app.getContactHelper().createContact(new ContactData("Михаил", "Лермонтов", "Кисловодск", "a@m.ru"));
        }
        app.getContactHelper().modificationContactClick();
        app.getContactHelper().fillNewContact(new ContactData("Александр", "Пушкин", "Москва", "s@m.ru"));
        app.getContactHelper().clickUpdate();
        app.getContactHelper().returnToContactPage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());
    }

}
