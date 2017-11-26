package ru.stqa.noy.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.noy.addressbook.model.AddNewData;

import java.util.ArrayList;
import java.util.List;

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

  public void initContactModification(int index) {
    wd.findElements(By.cssSelector("a[href^='edit.php?id=']")).get(index).click();
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void deleteSelectedContact() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }

  public void createContact(AddNewData contact, boolean b) {
    gotoAddNewPage();
    fillAddNewForm(contact, b);
    submitAddNew();
    returnToHomePage();
  }

  private void gotoAddNewPage() {
    click(By.linkText("add new"));
  }

  private void returnToHomePage() {
    click(By.linkText("home"));
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<AddNewData> getContactList() {
    List<AddNewData> contacts = new ArrayList<AddNewData>();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      String firstname = element.findElement(By.xpath("//div/div[4]/form[2]/table/tbody/tr[2]/td[3]")).getText();
      String lastname = element.findElement(By.xpath("//div/div[4]/form[2]/table/tbody/tr[2]/td[2]")).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      AddNewData contact = new AddNewData(id, firstname, lastname, null, null, null, null, null, null, null, null, null);
      contacts.add(contact);
    }
    return contacts;
  }
}
