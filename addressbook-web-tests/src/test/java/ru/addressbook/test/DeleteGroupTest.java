package ru.addressbook.test;

import org.testng.annotations.Test;
import ru.addressbook.model.GroupData;

public class DeleteGroupTest extends TestBase {

    @Test
    public void testDeleteGroup() throws Exception {
        app.getNavigationHelper().gotoGroup();
        if(!app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("2 попытка", "test", "test"));
        }
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().deleteSelectedGroup();
        app.getNavigationHelper().gotoGroup();
    }

}
