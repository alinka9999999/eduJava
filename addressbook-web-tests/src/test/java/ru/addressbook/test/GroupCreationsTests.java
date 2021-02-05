package ru.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class GroupCreationsTests extends TestBase {

    @Test
    public void testGroupCreations() throws Exception {
        app.getNavigationHelper().gotoGroup();
        List<GroupData> before = app.getGroupHelper().getGroupList();
        GroupData group = new GroupData("2 попытка", "test", "test");
        app.getGroupHelper().createGroup(group);
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size()  , before.size() + 1);


        int max = 0;
        for (GroupData g : after) {
            if (g.getId() > max) {
                max = g.getId();
            }
        }
        group.setId(max);
        before.add(group);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }

}
