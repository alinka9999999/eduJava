package ru.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

public class HelperBase {

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


    private void click(String locator) {
        wd.findElement(By.name(locator)).click();
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
