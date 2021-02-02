package ru.addressbook.test;

import org.testng.annotations.Test;

public class DeleteContact extends TestBase{

    @Test
    public void deleteContact(){
        app.getContactHelper().clickToHome();
        app.getContactHelper().deleteContact();
        app.getContactHelper().clickToHome();
    }

}
