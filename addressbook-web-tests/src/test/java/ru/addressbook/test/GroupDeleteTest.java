package ru.addressbook.test;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.addressbook.model.GroupData;
import ru.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupDeleteTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().GroupPage();
        if (app.group().list().size() == 0) {
            app.group().create(new GroupData().withName("testDelete").withHeader("2").withFooter("1"));
        }
    }


    @Test
    public void testDeleteGroup() throws Exception {
        Groups before = app.group().all();
        GroupData deletedGroup = before.iterator().next();
        app.group().delete(deletedGroup);
        assertThat(app.group().count(), equalTo(before.size() - 1));
        Groups after = app.group().all();

        assertThat(after, CoreMatchers.equalTo(before.without(deletedGroup)));

    }
}
