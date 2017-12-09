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
    type(By.name("work"), addNewData.getWork());
    type(By.name("email"), addNewData.getEmail());
    type(By.name("homepage"), addNewData.getHomepage());

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(addNewData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  //private void initContactModification() {
    //wd.findElement(By.cssSelector("a[href^='edit.php?id=']")).click();
  //}

  private void initContactModificationById(int id) {
    wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
  }

  //private void initContactModificationById(int id) {
    //wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=" + id + "']"))).click();
  //}

  public void submitContactModification() {
    click(By.name("update"));
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
    initContactModificationById(contact.getId());
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
    //if (contactCache != null) {
      //return new Contacts(contactCache);
    //}
    Contacts contacts = new Contacts();
    List<WebElement> elements  = wd.findElements(By.name("entry"));
    for (WebElement element : elements ) {
      List<WebElement> cells = element.findElements(By.tagName("td"));
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      String firstname = cells.get(2).getText();
      String lastname = cells.get(1).getText();
      String address = cells.get(3).getText();
      String allPhones = cells.get(5).getText();
      contacts.add(new AddNewData().withId(id).withFirstname(firstname).withLastname(lastname).withAddress(address).withAllPhones(allPhones));
    }
    return contacts;
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public AddNewData infoFromEditForm(AddNewData contact) {
    initContactModificationById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    wd.navigate().back();
    return new AddNewData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname).withAddress(address).withHome(home).withMobile(mobile).withWork(work);
  }

}
