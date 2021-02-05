package ru.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupCreationsTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().GroupPage();
    }

    @Test
    public void testGroupCreations() throws Exception {
        List<GroupData> before = app.group().list();
        GroupData group = new GroupData().withName("Test2").withHeader("1").withFooter("2");
        app.group().create(group);
        List<GroupData> after = app.group().list();
        Assert.assertEquals(after.size(), before.size() + 1);


        before.add(group);
        Comparator<? super GroupData> byId = Comparator.comparingInt(GroupData::getId);
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }

}
