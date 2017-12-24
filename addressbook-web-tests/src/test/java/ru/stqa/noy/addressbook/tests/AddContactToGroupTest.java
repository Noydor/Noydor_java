package ru.stqa.noy.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.noy.addressbook.model.AddNewData;
import ru.stqa.noy.addressbook.model.GroupData;
import ru.stqa.noy.addressbook.model.Groups;

import java.io.File;

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
    Groups groups = app.db().groups();
    File photo = new File("src/test/resources/noydor.jpg");
    AddNewData newContact = new AddNewData().withFirstname("Samuel").withLastname("Smith").withPhoto(photo).inGroup(groups.iterator().next());
    app.goTo().gotoAddNewPage();
    app.contact().submitAddNew();
    app.contact().fillAddNewForm(newContact, true);
    app.contact().submitAddNew();
    app.contact().returnToHomePage();

  }
}
