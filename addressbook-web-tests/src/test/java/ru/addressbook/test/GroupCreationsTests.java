package ru.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.addressbook.model.GroupData;

public class GroupCreationsTests extends TestBase {

    @Test
    public void testGroupCreations() throws Exception {
        app.getNavigationHelper().gotoGroup();
        int before = app.getGroupHelper().getGroupCount();
        app.getGroupHelper().createGroup(new GroupData("2 попытка", "test", "test"));
        int after = app.getGroupHelper().getGroupCount();
        Assert.assertEquals(after, before + 1);
    }

}
