package ru.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class GroupCreationsTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().GroupPage();
    }

    @Test
    public void testGroupCreations() throws Exception {
        Set<GroupData> before = app.group().all();
        GroupData group = new GroupData().withName("Test2").withHeader("1").withFooter("2");
        app.group().create(group);
        Set<GroupData> after = app.group().all();
        Assert.assertEquals(after.size(), before.size() + 1);


        group.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt());
        before.add(group);
        Assert.assertEquals(before, after);
    }

}
