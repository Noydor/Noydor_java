package ru.stqa.noy.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.noy.addressbook.model.AddNewData;

public class ContactModificationTests extends TestBase{

  @Test
  public void testContactModification() {
    app.getNavigationHelper().gotoHomePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new AddNewData("Samuel", "Smith", "Smithy", "Mr", "Seastar", "Berlin, Niederkirchnerstrasse, 18", "4955123456", "4976543210", "samuelsmith@gmail.com", "samuelsmith.com", "test1"), true);
    }
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillAddNewForm(new AddNewData("Samuel", "Smith", "Smithy", "Mr", "Seastar", "Berlin, Niederkirchnerstrasse, 18", "4955123456", "4976543210", "samuelsmith@gmail.com", "samuelsmith.com", null), false);
    app.getContactHelper().submitContactModification();

  }

}
