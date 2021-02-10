package ru.addressbook.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.addressbook.model.ContactData;
import ru.addressbook.model.Contacts;
import ru.addressbook.model.GroupData;
import ru.addressbook.model.Groups;

import java.util.Collection;
import java.util.HashSet;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddToGroup extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        app.contact().clickToHome();
        if(app.db().contacts().size()==0){
            app.contact().create(new ContactData()
                    .withName("Михаил").withLastname("Лермонтов").withAddress("Кисловодск").withEmail("alina@mail.com"));
        }
        if (app.contact().list().size() == 0) {
            app.contact().create(new ContactData()
                    .withName("Михаил").withLastname("Лермонтов").withAddress("Кисловодск").withEmail("alina@mail.com"));
        }
    }

    private void addingToGroup(ContactData addContact, GroupData toGroup) {
        app.contact().clickToHome();
        app.contact().addToGroup(addContact, toGroup);
        app.contact().clickToHome();
    }

    private ContactData selectContactById(ContactData addContact) {
        Contacts contactsById = app.db().contacts();
        return contactsById.iterator().next().withId(addContact.getId());
    }

    private ContactData selectContact() {
        Contacts allContacts = app.db().contacts();
        Groups allGroups = app.db().groups();
        for (ContactData contact : allContacts) {
            if (contact.getGroups().size() < allGroups.size()) {
                return contact;
            }
        }
        app.goTo().groupPage();
        app.group().create(new GroupData().withName("newGroup"));
        return allContacts.iterator().next();
    }

    private GroupData selectGroup(ContactData addContact) {
        Groups allGroups = app.db().groups();
        Groups addContactGroups = addContact.getGroups();
        Collection<GroupData> contactGroups = new HashSet<GroupData>(addContactGroups);
        Collection<GroupData> other = new HashSet<GroupData>(allGroups);
        other.removeAll(contactGroups);
        GroupData otherGroup = other.iterator().next();
        return otherGroup;
    }

    @Test
    public void addToGroup(){
        ContactData addContact = selectContact();
        GroupData toGroup = selectGroup(addContact);
        Groups before = addContact.getGroups();
        addingToGroup(addContact, toGroup);
        ContactData addContactAfter = selectContactById(addContact);
        Groups after = addContactAfter.getGroups();
        assertThat(after, equalTo(before.withAdded(toGroup)));
    }
}
