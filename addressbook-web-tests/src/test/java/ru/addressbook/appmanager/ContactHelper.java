package ru.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.addressbook.model.ContactData;
import ru.addressbook.model.Contacts;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase {
    private boolean acceptNextAlert = true;
    private Contacts contactCache = null;

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void initContactCreation() {
        wd.findElement(By.linkText("add new")).click();
    }

    public void fillNewContact(ContactData contactData) {
        type("firstname", contactData.getName());
        click("firstname");
        type("lastname", contactData.getLastname());
        click("lastname");
        type("address", contactData.getAddress());
        type("email", contactData.getEmail());

    }

    public void clickButton() {
        wd.findElement(By.name("submit")).click();
    }

    public void clickUpdate() {
        wd.findElement(By.name("update")).click();
    }

    public void clickToHome() {
        wd.findElement(By.linkText("home")).click();
    }

    public void deleteContactCheckBox(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
        wd.findElement(By.xpath("//input[@value='Delete']")).click();
        wd.switchTo().alert().accept();
    }

    public void modificationContactClick(int id) {
        wd.findElement(By.xpath("(//img[@alt='Edit'])[" + id + "]")).click();
    }

    public boolean isThereContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public void returnToContactPage() {
        wd.findElement(By.linkText("home page")).click();
    }

    public void create(ContactData contact) {
        initContactCreation();
        fillNewContact(contact);
        clickButton();
        contactCache = null;
        returnToContactPage();
    }

    public void modify(ContactData contact) {
        modificationContactClickById(contact.getId());
        fillNewContact(contact);
        clickUpdate();
        contactCache = null;
        returnToContactPage();
    }

    private void modificationContactClickById(int id) {
        wd.findElement(By.cssSelector("a[href*='edit.php?id=" + id + "']")).click();
    }

    public void delete(int index) {
        deleteContactCheckBox(index);
        clickToHome();

    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList<>();
        List<WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']"));
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.name("selected[]")).getAttribute("value"));
            String name = element.findElement(By.xpath(".//td[3]")).getText();
            String lastname = element.findElement(By.xpath(".//td[2]")).getText();
            contacts.add(new ContactData().withId(id).withName(name).withLastname(lastname));
        }
        return contacts;
    }

    public Set<ContactData> allContacts() {
        Set<ContactData> contacts = new HashSet<>();
        List<WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']"));
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.name("selected[]")).getAttribute("value"));
            String name = element.findElement(By.xpath(".//td[3]")).getText();
            String lastname = element.findElement(By.xpath(".//td[2]")).getText();
            //String[] phones = element.findElement(By.xpath(".//td[6]")).getText().split("\n");
            String allPhones = element.findElement(By.xpath(".//td[6]")).getText();
            //String allEmails = element.findElement(By.xpath(".//td[5]")).getText();
            //String address = element.findElement(By.xpath(".//td[4]")).getText();
            contacts.add(new ContactData().withId(id).withName(name).withLastname(lastname).withAllPhones(allPhones));


                    //withId(id).withName(name).withLastname(lastname).withHomePhone(phones[0]).withMobilePhone(phones[1]).withWorkPhone(phones[2]));
        }
        return contacts;
    }


    public void delete(ContactData contact) {
        deleteContactCheckBoxById(contact.getId());
        contactCache = null;
        clickToHome();
    }

    private void deleteContactCheckBoxById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
        wd.findElement(By.xpath("//input[@value='Delete']")).click();
        wd.switchTo().alert().accept();
    }

    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']"));
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String name = element.findElement(By.xpath(".//td[3]")).getText();
            String lastname = element.findElement(By.xpath(".//td[2]")).getText();
            contactCache.add(new ContactData().withId(id).withName(name).withLastname(lastname));
        }
        return contactCache;
    }

    public ContactData infoFromEditForm(ContactData contact) {
        modificationContactClickById(contact.getId());
        String name = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");

        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withName(name).withLastname(lastname)
                .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work);
    }

    private void initContactModificationById(int id) {
        WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
        WebElement row = checkbox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(7).findElement(By.tagName("a")).click();


    }
}

