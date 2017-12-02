package ru.stqa.noy.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.noy.addressbook.model.AddNewData;

import java.util.Comparator;
import java.util.List;

public class NewContactTests extends TestBase{

  @Test
  public void testNewContact() {
    List<AddNewData> before = app.contact().list();
    AddNewData contact = new AddNewData().withFirstname("Samuel").withLastname("Smith").withNickname("Smithy").withTitle("Mr").withCompany("Seastar").withAddress("Berlin, Niederkirchnerstrasse, 18").withHome("4955123456").withMobile("4976543210").withEmail("samuelsmith@gmail.com").withHomepage("samuelsmith.com").withGroup("test1");
    app.contact().create(contact, true);
    List<AddNewData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(contact);
    Comparator<? super AddNewData> byId = (c1, c2) -> Integer.compare(c1.getId(),c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
   }

}
