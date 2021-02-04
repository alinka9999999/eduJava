package ru.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.addressbook.model.GroupData;

import java.util.List;

public class GroupCreationsTests extends TestBase {

    @Test
    public void testGroupCreations() throws Exception {
        app.getNavigationHelper().gotoGroup();
        List<GroupData> before = app.getGroupHelper().getGroupList();
        app.getGroupHelper().createGroup(new GroupData("2 попытка", "test", "test"));
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size()  , before.size() + 1);
    }

}
