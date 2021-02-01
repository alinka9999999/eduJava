package ru.addressbook.test;

import org.testng.annotations.Test;
import ru.addressbook.model.GroupData;

public class GroupCreationsTests extends TestBase {

    @Test
    public void testGroupCreations() throws Exception {

        app.getNavigationHelper().goTogroup();
        app.getGroupHelper().initGroupCreation("new");
        app.getGroupHelper().initGroupCreation("group_name");
        app.getGroupHelper().fillGroupForm(new GroupData("test", "test", "test"));
        app.getGroupHelper().initGroupCreation("submit");
        app.getNavigationHelper().goTogroup();
    }

}
