package ru.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

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

    public void clickSubmit() {
        wd.findElement(By.name("submit")).click();
    }


    public void initGroupCreation(String locator) {
        wd.findElement(By.name(locator)).click();
    }

    public void clickNew() {
        wd.findElement(By.name("new")).click();
    }

    public void deleteSelectedGroup() {
        wd.findElement(By.name("delete")).click();
    }

    public void selectGroup(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
        //wd.findElement(By.name("selected[]")).click();
    }

    public void initGroupModification() {
        click("edit");
    }

    public void submitGroupModification() {
        click("update");
    }

    public void returnToGroupPage() {
        wd.findElement(By.linkText("group page")).click();
    }

    public void createGroup(GroupData group) {
        clickNew();
        fillGroupForm(group);
        clickSubmit();
        returnToGroupPage();
    }

    public void modifyGroup(int index, GroupData group) {
        selectGroup(index);
        initGroupModification();
        fillGroupForm(group);
        submitGroupModification();
        returnToGroupPage();
    }

    public void deleteGroup(int index) {
        selectGroup(index);
        deleteSelectedGroup();
        returnToGroupPage();
    }

    public boolean isThereAGroup() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getGroupCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<GroupData> getGroupList() {
        List<GroupData> groups = new ArrayList<>();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements) {
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            GroupData group = new GroupData(id, name, null, null);
            groups.add(group);
        }
        return groups;
    }
}
