package ru.addressbook.test;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.addressbook.model.GroupData;
import ru.addressbook.model.Groups;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationsTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().GroupPage();
    }

    @Test
    public void testGroupCreations() throws Exception {
        Groups before = app.group().all();
        GroupData group = new GroupData().withName("Test2").withHeader("1").withFooter("2");
        app.group().create(group);
        Groups after = app.group().all();
        assertThat(after.size(),equalTo( before.size() + 1));


        assertThat(after, equalTo(before.withAdded(
                group.withId(after.stream().mapToInt(GroupData::getId).max().getAsInt()))));
    }

}
