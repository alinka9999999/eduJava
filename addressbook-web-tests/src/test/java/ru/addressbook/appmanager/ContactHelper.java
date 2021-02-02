package ru.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import ru.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {
    private boolean acceptNextAlert = true;

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void initContactCreation(String locator) {
        wd.findElement(By.name(locator)).click();
    }

    public void fillNewContact(ContactData contactData) {
        type("firstname", contactData.getName());
        initContactCreation("firstname");
        type("lastname", contactData.getLastname());
        initContactCreation("lastname");
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

}
