package ru.addressbook.test;

import org.testng.annotations.Test;
import ru.addressbook.model.ContactData;

public class ContactModificationTest extends TestBase {

    @Test
    public void modificationContact() {
        app.getContactHelper().clickToHome();
        app.getContactHelper().modificationContactClick();
        app.getContactHelper().fillNewContact(new ContactData("1", "2", "3", "4"));
        app.getContactHelper().clickUpdate();
    }

}
