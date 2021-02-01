package ru.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.addressbook.model.GroupData;

public class GroupHelper {
    private WebDriver wd;

    public GroupHelper(WebDriver wd) {
        this.wd = wd;
    }

    public void fillGroupForm(GroupData groupData) {
        wd.findElement(By.name("group_name")).clear();
        wd.findElement(By.name("group_name")).sendKeys(groupData.getName());
        initGroupCreation("group_header");
        wd.findElement(By.name("group_header")).clear();
        wd.findElement(By.name("group_header")).sendKeys(groupData.getHeader());
        initGroupCreation("group_footer");
        wd.findElement(By.name("group_footer")).clear();
        wd.findElement(By.name("group_footer")).sendKeys(groupData.getFooter());
    }

    public void initGroupCreation(String s) {
        wd.findElement(By.name(s)).click();
    }

    public void deleteSelectedGroup() {
      wd.findElement(By.name("delete")).click();
    }

    public void selectGroup() {
      wd.findElement(By.name("selected[]")).click();
    }
}
