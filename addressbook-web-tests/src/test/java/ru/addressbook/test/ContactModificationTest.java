package ru.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactModificationTest extends TestBase {

    @Test
    public void modificationContact() {
        List<ContactData> before = app.getContactHelper().getContactList();
        if(!app.getContactHelper().isThereContact()) {
            app.getContactHelper().createContact(new ContactData( "Михаил", "Лермонтов", "Кисловодск", "a@m.ru"));
        }
        app.getContactHelper().modificationContactClick(before.size() - 1);
        ContactData contact = new ContactData(before.get(before.size() - 1).getId(),"Александр", "Пушкин", "Москва", "s@m.ru");
        app.getContactHelper().fillNewContact(contact);
        app.getContactHelper().clickUpdate();
        app.getContactHelper().returnToContactPage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());


       before.remove(before.size() - 1);
       before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
       Assert.assertEquals(before,after);
    }

}
