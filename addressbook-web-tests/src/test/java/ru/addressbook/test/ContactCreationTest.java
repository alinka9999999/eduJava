package ru.addressbook.test;

import org.testng.annotations.Test;
import ru.addressbook.model.ContactData;

public class ContactCreationTest extends TestBase {


    @Test
    public void testContactCreation() throws Exception {
        app.getContactHelper().initContactCreation();
        app.getContactHelper().fillNewContact(new ContactData("Федор", "Достоевский", "Оренбург", "alina.yahina37@gmail.com"));
        app.getContactHelper().clickButton();
    }
}

