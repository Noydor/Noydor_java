package ru.stqa.noy.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;
import ru.stqa.noy.addressbook.model.AddNewData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase{

  @Test
  public void testContactPhones() {
    app.goTo().homePage();
    AddNewData contact = app.contact().all().iterator().next();
    AddNewData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getHome(), equalTo(contactInfoFromEditForm.getHome()));
    assertThat(contact.getMobile(), equalTo(contactInfoFromEditForm.getMobile()));
    assertThat(contact.getWork(), equalTo(contactInfoFromEditForm.getHome()));
  }
}
