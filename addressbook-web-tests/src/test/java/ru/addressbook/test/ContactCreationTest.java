package ru.addressbook.test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContacts() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.csv")))) {
            String line = reader.readLine();
        while(line !=null)

            {
                String[] split = line.split(";");
                list.add(new Object[]{new ContactData().withName(split[0]).withLastname(split[1]).withAddress(split[2])});
                line = reader.readLine();
            }
        return list.iterator();
        }
    }

    @DataProvider
    public Iterator<Object[]> validContactsFromJson() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))) {
            String json = "";
            String line = reader.readLine();
            while (line != null) {
                json += line;
                line = reader.readLine();
            }
            Gson gson = new Gson();
            List<GroupData> contacts = gson.fromJson(json, new TypeToken<List<GroupData>>() {
            }.getType());
            return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
    }

    @BeforeMethod
    public void ensurePreconditions() {
        app.contact().clickToHome();
    }

    @Test(dataProvider = "validContactsFromJson") //перегенирирован equals and hash
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

