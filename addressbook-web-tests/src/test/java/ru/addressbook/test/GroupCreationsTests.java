package ru.addressbook.test;

import org.testng.annotations.Test;
import ru.addressbook.model.GroupData;

public class GroupCreationsTests extends TestBase {

    @Test
    public void testGroupCreations() throws Exception {

        app.getNavigationHelper().gotoGroup();
        app.getGroupHelper().clickNew();
        app.getGroupHelper().fillGroupForm(new GroupData("2 попытка", "test", "test"));
        app.getGroupHelper().clickSubmit();
        app.getNavigationHelper().gotoGroup();
    }

}
