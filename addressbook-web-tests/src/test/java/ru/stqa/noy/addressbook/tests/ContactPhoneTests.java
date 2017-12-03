package ru.stqa.noy.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.noy.addressbook.model.AddNewData;

public class ContactPhoneTests extends TestBase{

  @Test
  public void testContactPhones() {
    app.goTo().homePage();
    AddNewData contact = app.contact().all().iterator().next();
    AddNewData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
  }
}
