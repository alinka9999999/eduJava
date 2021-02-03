package ru.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {
    private boolean acceptNextAlert = true;

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
        wd.findElement(By.name("address")).sendKeys(contactData.getCity());
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

    public void deleteContact() {
        wd.findElement(By.name("selected[]")).click();
        wd.findElement(By.xpath("//input[@value='Delete']")).click();
        wd.switchTo().alert().accept();
    }

    public void modificationContactClick() {
        wd.findElement(By.xpath("(//img[@alt='Edit'])[1]")).click();
    }

    public boolean isThereContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public void returnToContactPage() {wd.findElement(By.linkText("home page")).click();    }

    public void createContact(ContactData contact) {
        initContactCreation();
        fillNewContact(contact);
        clickButton();
        returnToContactPage();
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }
}
