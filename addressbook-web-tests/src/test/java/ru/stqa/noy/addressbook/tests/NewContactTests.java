package ru.stqa.noy.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.noy.addressbook.model.AddNewData;

public class NewContactTests extends TestBase{

  @Test
  public void testNewContact() {
    int before = app.getContactHelper().getContactCount();
    app.getContactHelper().createContact(new AddNewData("Samuel", "Smith", "Smithy", "Mr", "Seastar", "Berlin, Niederkirchnerstrasse, 18", "4955123456", "4976543210", "samuelsmith@gmail.com", "samuelsmith.com", "test1"), true);
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before + 1);
   }

}
