package ru.addressbook.test;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.addressbook.model.ContactData;
import ru.addressbook.model.Contacts;
import ru.addressbook.model.GroupData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContacts() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.csv")));
        String line = reader.readLine();
        while (line != null){
            String[] split = line.split(";");
            list.add(new Object[] {new ContactData().withName(split[0]).withLastname(split[1]).withAddress(split[2])});
            line = reader.readLine();
        }
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

