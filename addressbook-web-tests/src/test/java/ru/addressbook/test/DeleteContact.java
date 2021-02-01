package ru.addressbook.test;

import org.testng.annotations.Test;

public class DeleteContact extends TestBase{

    @Test
    public void deleteContact(){
        app.getContactHelper().clicktoElement("home");
        app.getContactHelper().deleteContact();
        app.getContactHelper().clicktoElement("home");
    }

}
