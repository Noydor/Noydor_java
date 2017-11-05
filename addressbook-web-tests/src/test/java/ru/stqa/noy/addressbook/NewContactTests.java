package ru.stqa.noy.addressbook;

import org.testng.annotations.Test;

public class NewContactTests extends TestBase{

  @Test
  public void testNewContact() {
    gotoAddNewPage();
    fillAddNewForm(new AddNewData("Samuel", "Smith", "Smithy", "Mr", "Seastar", "Berlin, Niederkirchnerstrasse, 18", "4955123456", "4976543210", "samuelsmith@gmail.com", "samuelsmith.com"));
    submitAddNew();
    returnToHomePage();
  }

}
