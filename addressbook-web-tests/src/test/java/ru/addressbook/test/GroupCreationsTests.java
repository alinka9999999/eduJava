package ru.addressbook.test;

import org.testng.annotations.Test;
import ru.addressbook.model.GroupData;

public class GroupCreationsTests extends TestBase {

    @Test
    public void testGroupCreations() throws Exception {

        app.goTogroup();
        app.initGroupCreation("new");
        app.initGroupCreation("group_name");
        app.fillGroupForm(new GroupData("test", "test", "test"));
        app.initGroupCreation("submit");
        app.goTogroup();
    }

}
