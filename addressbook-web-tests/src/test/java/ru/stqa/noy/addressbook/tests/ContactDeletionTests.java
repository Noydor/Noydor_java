package ru.stqa.noy.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.noy.addressbook.model.AddNewData;

public class ContactDeletionTests extends TestBase{

  @Test
  public void testContactDeletion() {
    app.getNavigationHelper().gotoHomePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new AddNewData("Samuel", "Smith", "Smithy", "Mr", "Seastar", "Berlin, Niederkirchnerstrasse, 18", "4955123456", "4976543210", "samuelsmith@gmail.com", "samuelsmith.com", "test1"), true);
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContact();
    app.closeAlert();

  }
}
