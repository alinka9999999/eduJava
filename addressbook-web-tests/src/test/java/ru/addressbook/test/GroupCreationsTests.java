package ru.addressbook.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.addressbook.model.GroupData;
import ru.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationsTests extends TestBase {


    @DataProvider
    public Iterator<Object[]> validGroups(){
        List<Object[]> list = new ArrayList<Object[]>();
        list.add(new Object[] {new GroupData().withName("test1").withHeader("header 1").withFooter("footer 1")});
        list.add(new Object[] {new GroupData().withName("test2").withHeader("header 2").withFooter("footer 2")});
        list.add(new Object[] {new GroupData().withName("test3").withHeader("header 3").withFooter("footer 3")});
        return list.iterator();
    }

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().GroupPage();
    }

    @Test(dataProvider = "validGroups")
    public void testGroupCreations(GroupData group) throws Exception {
        Groups before = app.group().all();
        app.group().create(group);
        Groups after = app.group().all();
        assertThat(app.group().count(), equalTo(before.size() + 1));

        assertThat(after, equalTo(before.withAdded(
                group.withId(after.stream().mapToInt(GroupData::getId).max().getAsInt()))));
    }

    @Test
    public void testBadGroupCreations() throws Exception {
        Groups before = app.group().all();
        GroupData group = new GroupData().withName("Test2'").withHeader("1").withFooter("2");
        app.group().create(group);
        assertThat(app.group().count(), equalTo(before.size()));
        Groups after = app.group().all();

        assertThat(after, equalTo(before));
    }

}
