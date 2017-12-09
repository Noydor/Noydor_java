package ru.stqa.noy.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.noy.addressbook.model.AddNewData;
import ru.stqa.noy.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class NewContactTests extends TestBase{

  @Test
  public void testNewContact() {
    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/noydor.jpg");
    AddNewData contact = new AddNewData().withFirstname("Samuel").withLastname("Smith").withNickname("Smithy")
            .withTitle("Mr").withCompany("Seastar").withAddress("Berlin, Niederkirchnerstrasse, 18")
            .withHome("49-55-1234").withMobile("4976543210").withWork("123").withEmail("samuelsmith@gmail.com").withEmail2("s.smith@gmail.com")
            .withHomepage("samuelsmith.com").withGroup("test1").withPhoto(photo);
    app.contact().create(contact, true);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
   }

   //@Test
   //public void testCurrentDir() {
     //File currentDir = new File(".");
     //System.out.println(currentDir.getAbsolutePath());
     //File photo = new File("src/test/resources/noydor.jpg");
     //System.out.println(photo.getAbsolutePath());
     //System.out.println(photo.exists());
   //}

}
