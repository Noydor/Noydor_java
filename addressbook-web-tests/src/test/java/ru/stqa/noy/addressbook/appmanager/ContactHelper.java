package ru.stqa.noy.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.noy.addressbook.model.AddNewData;
import ru.stqa.noy.addressbook.model.Contacts;
import ru.stqa.noy.addressbook.model.Groups;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitAddNew() {
    click(By.name("submit"));
  }

  public void fillAddNewForm(AddNewData addNewData, boolean creation) {
    type(By.name("firstname"), addNewData.getFirstname());
    type(By.name("lastname"), addNewData.getLastname());
    type(By.name("nickname"), addNewData.getNickname());
    type(By.name("title"), addNewData.getTitle());
    type(By.name("company"), addNewData.getCompany());
    type(By.name("address"), addNewData.getAddress());
    type(By.name("home"), addNewData.getHome());
    type(By.name("mobile"), addNewData.getMobile());
    type(By.name("email"), addNewData.getEmail());
    type(By.name("homepage"), addNewData.getHomepage());

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(addNewData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  private void initContactModification() {
    wd.findElement(By.cssSelector("a[href^='edit.php?id=']")).click();
  }

  public void submitContactModification() {
    click(By.name("update"));
  }


  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void deleteSelectedContact() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }

  public void create(AddNewData contact, boolean b) {
    gotoAddNewPage();
    fillAddNewForm(contact, b);
    submitAddNew();
    contactCache = null;
    returnToHomePage();
  }

  public void modify(AddNewData contact) {
    selectContactById(contact.getId());
    initContactModification();
    fillAddNewForm(contact, false);
    submitContactModification();
    contactCache = null;
    returnToHomePage();
  }

  public void delete(AddNewData contact) {
    selectContactById(contact.getId());
    deleteSelectedContact();
    contactCache = null;
    closeAlert();
    returnToHomePage();
  }

  private void closeAlert() {
    wd.switchTo().alert().accept();
  }

  private void gotoAddNewPage() {
    click(By.linkText("add new"));
  }

  private void returnToHomePage() {
    click(By.linkText("home"));
  }

  private Contacts contactCache = null;

  public Contacts all() {
    if (contactCache != null) {
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      List<WebElement> cells = element.findElements(By.tagName("td"));
      String firstname = cells.get(2).getText();
      String lastname = cells.get(1).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      contactCache.add(new AddNewData().withId(id).withFirstname(firstname).withLastname(lastname));
    }
    return new Contacts(contactCache);
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }
}
