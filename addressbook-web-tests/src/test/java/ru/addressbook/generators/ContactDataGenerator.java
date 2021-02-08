package ru.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import ru.addressbook.model.ContactData;
import ru.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

    @Parameter(names = "-c", description = "Group count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex) {
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private static List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        for (int i =0; i < count; i++) {
            contacts.add(new ContactData().withName(String.format("test %s", i)).
                    withLastname((String.format("Lastname %s", i))).
                    withAddress((String.format("Address %s", i))));
        }
        return contacts;
    }

    private void run () throws IOException {
        List<ContactData> contacts = generateContacts(count);
        save(contacts, new File(file));
    }
    private static void save(List<ContactData> contacts, File file) throws IOException {

        Writer writer  = new FileWriter(file);
        for (ContactData contact : contacts){
            writer.write(String.format("%s;%s;%s\n", contact.getName(), contact.getLastname(), contact.getAddress()));
        }
        writer.close();
    }
}
