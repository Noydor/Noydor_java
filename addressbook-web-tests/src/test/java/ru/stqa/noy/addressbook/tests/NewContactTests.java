package ru.stqa.noy.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.noy.addressbook.model.AddNewData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class NewContactTests extends TestBase{

  @Test
  public void testNewContact() {
    List<AddNewData> before = app.getContactHelper().getContactList();
    AddNewData contact = new AddNewData("Samuel", "Smith", "Smithy", "Mr", "Seastar", "Berlin, Niederkirchnerstrasse, 18", "4955123456", "4976543210", "samuelsmith@gmail.com", "samuelsmith.com", "test1");
    app.getContactHelper().createContact(contact, true);
    List<AddNewData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(contact);
    Comparator<? super AddNewData> byId = (c1, c2) -> Integer.compare(c1.getId(),c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
   }

}
