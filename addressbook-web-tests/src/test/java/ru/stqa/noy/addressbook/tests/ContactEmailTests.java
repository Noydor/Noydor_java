package ru.stqa.noy.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.noy.addressbook.model.AddNewData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new AddNewData().withFirstname("Samuel").withLastname("Smith").withNickname("Smithy").withTitle("Mr").withCompany("Seastar").withAddress("Berlin, Niederkirchnerstrasse, 18").withHome("4955123456").withMobile("4976543210").withEmail("samuelsmith@gmail.com").withEmail2("s.smith@gmail.com").withHomepage("samuelsmith.com"), true);
    }
  }

  @Test
  public void testContactEmail() {
    app.goTo().homePage();
    AddNewData contact = app.contact().all().iterator().next();
    AddNewData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
  }

  private String mergeEmails(AddNewData contact) {
    return Arrays.asList(contact.getEmail(), contact.getEmail2())
            .stream().filter((s) -> ! s.equals(""))
            .map(ContactPhoneTests::cleaned)
            .collect(Collectors.joining("\n"));
  }

}
