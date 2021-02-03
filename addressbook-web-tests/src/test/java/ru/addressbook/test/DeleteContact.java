package ru.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.addressbook.model.ContactData;

public class DeleteContact extends TestBase {

    @Test
    public void deleteContact() {
        int before = app.getContactHelper().getContactCount();
        if (!app.getContactHelper().isThereContact()) {
            app.getContactHelper().createContact(new ContactData("Михаил", "Лермонтов", "Кисловодск", "a@m.ru"));
        }
        app.getContactHelper().deleteContact();
        app.getContactHelper().clickToHome();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before - 1);
    }

}
