package ru.addressbook.test;

import org.testng.annotations.Test;
import ru.addressbook.model.ContactData;

public class ContactModificationTest extends TestBase {

    @Test
    public void modificationContact() {
        if(!app.getContactHelper().isThereContact()) {
            app.getContactHelper().createContact(new ContactData("Михаил", "Лермонтов", "Кисловодск", "a@m.ru"));
        }
        app.getContactHelper().modificationContactClick();
        app.getContactHelper().fillNewContact(new ContactData("Александр", "Пушкин", "Москва", "s@m.ru"));
        app.getContactHelper().clickUpdate();
        app.getContactHelper().returnToContactPage();
    }

}
