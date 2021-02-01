package ru.addressbook.test;

import org.testng.annotations.*;

public class DeleteGroupTest extends TestBase {

  @Test
  public void testDeleteGroup() throws Exception {
    app.goTogroup();
    app.selectGroup();
    app.deleteSelectedGroup();
    app.goTogroup();
  }

}
