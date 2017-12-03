package ru.stqa.noy.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.noy.addressbook.model.AddNewData;

import java.util.Set;

public class NewContactTests extends TestBase{

  @Test
  public void testNewContact() {
    Set<AddNewData> before = app.contact().all();
    AddNewData contact = new AddNewData().withFirstname("Samuel").withLastname("Smith").withNickname("Smithy").withTitle("Mr").withCompany("Seastar").withAddress("Berlin, Niederkirchnerstrasse, 18").withHome("4955123456").withMobile("4976543210").withEmail("samuelsmith@gmail.com").withHomepage("samuelsmith.com").withGroup("test1");
    app.contact().create(contact, true);
    Set<AddNewData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);

    contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(before, after);
   }

}
