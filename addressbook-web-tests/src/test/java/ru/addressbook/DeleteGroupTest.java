package ru.addressbook;

import org.testng.annotations.*;

public class DeleteGroupTest extends TestBase{

  @Test
  public void testDeleteGroup() throws Exception {
    goTogroup();
    selectGroup();
    deleteSelectedGroup();
    goTogroup();
  }

}
