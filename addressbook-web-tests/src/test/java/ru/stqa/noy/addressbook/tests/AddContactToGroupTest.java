package ru.stqa.noy.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.noy.addressbook.model.AddNewData;
import ru.stqa.noy.addressbook.model.GroupData;
import ru.stqa.noy.addressbook.model.Groups;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class AddContactToGroupTest extends TestBase {

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
  public void testAddContactToGroup() {
    AddNewData newContact = app.db().contacts().iterator().next();
    Groups before = newContact.getGroups();
    GroupData inGroup;
    Groups absentGroups = app.db().groups();
    absentGroups.removeAll(before);

    if (absentGroups.isEmpty()) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
      Groups absentGroups2 = app.db().groups();
      absentGroups2.removeAll(before);
      inGroup = absentGroups2.iterator().next();
    }
    else {
      inGroup = absentGroups.iterator().next();
    }

    app.goTo().homePage();
    app.contact().addContactToGroup(newContact, inGroup);
    Groups after = app.db().contactsId(newContact.getId()).iterator().next().getGroups();
    assertThat(after, equalTo(before.withNewContact(newContact, inGroup)));
  }
}
