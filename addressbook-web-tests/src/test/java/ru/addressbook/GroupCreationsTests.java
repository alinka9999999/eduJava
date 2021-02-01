package ru.addressbook;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class GroupCreationsTests extends TestBase {

    @Test
    public void testGroupCreations() throws Exception {

        goTogroup();
        initGroupCreation("new");
        initGroupCreation("group_name");
        fillGroupForm(new GroupData("test", "test", "test"));
        initGroupCreation("submit");
        goTogroup();
    }

}
