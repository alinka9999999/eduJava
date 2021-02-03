package ru.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import ru.addressbook.test.TestBase;

public class HelperBase extends TestBase {

    protected WebDriver wd;

    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

    protected void type(String locator, String text) {
        click(locator);
        if (text != null) {
            String existingText = wd.findElement(By.name(locator)).getAttribute("value");
            if (!text.equals(existingText)) {
                wd.findElement(By.name(locator)).clear();
                wd.findElement(By.name(locator)).sendKeys(text);
            }
        }
    }

    void click(String locator) {
        wd.findElement(By.name(locator)).click();
    }

    boolean isElementPresent(By by) {
        try {
            wd.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }


}
