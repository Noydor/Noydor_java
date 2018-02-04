package ru.stqa.noy.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.noy.addressbook.model.AddNewData;
import ru.stqa.noy.addressbook.model.GroupData;
import ru.stqa.noy.addressbook.model.Groups;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static ru.stqa.noy.addressbook.tests.TestBase.app;

public class DeleteContactFromGroupTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }
    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      app.contact().create(new AddNewData().withFirstname("Samuel").withLastname("Smith").withNickname("Smithy").withTitle("Mr").withCompany("Seastar").withAddress("Berlin, Niederkirchnerstrasse, 18").withHome("4955123456").withMobile("4976543210").withEmail("samuelsmith@gmail.com").withHomepage("samuelsmith.com"), true);
    }
  }

  @Test
  public void testDeleteContactFromGroup() {
    AddNewData delContact = app.db().contacts().iterator().next();
    Groups before = delContact.getGroups();
    GroupData fromGroup;

    if (before.isEmpty()) {
      fromGroup = app.db().groups().iterator().next();
      app.goTo().homePage();
      app.contact().addContactToGroup(delContact, fromGroup);
    }
    else {
      fromGroup = before.iterator().next();
    }

    app.goTo().homePage();
    app.contact().deleteContactFromGroup(delContact, fromGroup);
    Groups after = app.db().contactsId(delContact.getId()).iterator().next().getGroups();
    assertThat(after, equalTo(before.withoutContact(delContact, fromGroup)));
  }
}
