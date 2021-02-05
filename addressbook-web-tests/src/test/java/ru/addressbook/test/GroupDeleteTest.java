package ru.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.addressbook.model.GroupData;

import java.util.List;

public class GroupDeleteTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.group().list().size() == 0) {
            app.group().create(new GroupData("2 попытка", "test", "test"));
        }
    }


    @Test
    public void testDeleteGroup() throws Exception {
        app.goTo().GroupPage();
        List<GroupData> before = app.group().list();
        int index = before.size() - 1;
        app.group().delete(index);
        List<GroupData> after = app.group().list();
        Assert.assertEquals(after.size(), before.size() - 1);


        before.remove(index);
        Assert.assertEquals(before, after);

    }
}
