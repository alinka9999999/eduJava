package ru.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.addressbook.model.GroupData;

public class GroupModificationTests extends TestBase{

    @Test
    public void testGroupModification(){
        app.getNavigationHelper().gotoGroup();
        int before = app.getGroupHelper().getGroupCount();
        if(!app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("2 попытка", "test", "test"));
        }
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(new GroupData("tets", "test", "-"));
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnToGroupPage();
        int after = app.getGroupHelper().getGroupCount();
        Assert.assertEquals(after, before);

    }
}
