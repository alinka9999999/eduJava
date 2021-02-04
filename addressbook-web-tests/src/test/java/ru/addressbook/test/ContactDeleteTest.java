package ru.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.addressbook.model.ContactData;

import java.util.List;

public class ContactDeleteTest extends TestBase {

    @Test
    public void deleteContact() {
        List<ContactData> before = app.getContactHelper().getContactList();
        if (!app.getContactHelper().isThereContact()) {
            app.getContactHelper().createContact(new ContactData("Михаил", "Лермонтов", "Кисловодск", "a@m.ru"));
        }
        app.getContactHelper().deleteContact();
        app.getContactHelper().clickToHome();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() -1);
        for (int i = 0; i < after.size(); i++){
            Assert.assertEquals(before.get(1), after.get(1));
        }
    }

}
