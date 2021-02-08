package ru.addressbook.test;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.addressbook.model.ContactData;
import ru.addressbook.model.Contacts;
import ru.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContacts(){
        List<Object[]> list = new ArrayList<Object[]>();
        list.add(new Object[] {new ContactData().withName("test1").withLastname("Lastname 1").withAddress("Address 1")});
        list.add(new Object[] {new ContactData().withName("test2").withLastname("Lastname 2").withAddress("Address 2")});
        list.add(new Object[] {new ContactData().withName("test3").withLastname("Lastname 3").withAddress("Address 3")});
        return list.iterator();
    }

    @BeforeMethod
    public void ensurePreconditions() {
        app.contact().clickToHome();
    }

    @Test(dataProvider = "validContacts") //перегенирирован equals and hash
    public void testContactCreation(ContactData contact) throws Exception {
        Contacts before = app.contact().all();
        //File photo = new File("src/test/resources/92f.png");
        app.contact().create(contact);
        Contacts after = app.contact().all();
        assertThat(app.contact().count(), equalTo(before.size() + 1));

        assertThat(after, CoreMatchers.equalTo(before.
                withAdded(contact.withId(after.stream().mapToInt(ContactData::getId).max().getAsInt()))));
    }


    @Test(enabled = true)
    public void testBadContactCreation() throws Exception {
        Contacts before = app.contact().all();
        ContactData contact = new ContactData()
                .withName("Федор'").withLastname("Достоевский").withAddress("Оренбург").withEmail("alina.yahina37@gmail.com");
        app.contact().create(contact);
        Contacts after = app.contact().all();
        assertThat(app.contact().count(), equalTo(before.size()));

        assertThat(after, CoreMatchers.equalTo(before));
    }
}

