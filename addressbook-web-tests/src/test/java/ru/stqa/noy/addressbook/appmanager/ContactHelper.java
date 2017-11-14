package ru.stqa.noy.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.noy.addressbook.model.AddNewData;

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

  public void initContactModification() {
    click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));

  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void selectContact() {
    click(By.name("selected[]"));
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
}
