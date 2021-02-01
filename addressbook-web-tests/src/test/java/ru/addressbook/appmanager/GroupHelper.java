package ru.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.addressbook.model.GroupData;

public class GroupHelper extends HelperBase {

    public GroupHelper(WebDriver wd) {
        super(wd);
    }

    public void fillGroupForm(GroupData groupData) {
        type("group_name", groupData.getName());
        initGroupCreation("group_header");
        type("group_header", groupData.getHeader());
        initGroupCreation("group_footer");
        type("group_footer", groupData.getFooter());
    }
    public void click(String text) { wd.findElement(By.name(text)).click(); }

    public void initGroupCreation(String locator) {
        wd.findElement(By.name(locator)).click();
    }

    public void deleteSelectedGroup() {
      wd.findElement(By.name("delete")).click();
    }

    public void selectGroup() {
      wd.findElement(By.name("selected[]")).click();
    }

    public void initGroupModification() {
        click("edit");
    }

    public void submitGroupModification() {
        click("update");
    }
}
