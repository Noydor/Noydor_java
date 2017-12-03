package ru.stqa.noy.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.noy.addressbook.model.GroupData;
import ru.stqa.noy.addressbook.model.Groups;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GroupHelper extends HelperBase {

  public GroupHelper(WebDriver wd) {
    super(wd);
  }

  public void returnToGroupPage() {
    click(By.linkText("group page"));
  }

  public void submitGroupCreation() {
    click(By.name("submit"));
  }

  public void fillGroupForm(GroupData groupData) {
    type(By.name("group_name"), groupData.getName());
    type(By.name("group_header"), groupData.getHeader());
    type(By.name("group_footer"), groupData.getFooter());
  }

  public void initGroupCreation() {
    click(By.name("new"));
  }

  public void deleteSelectedGroups() {
    click(By.name("delete"));
  }

  public void selectGroupById(int id) {    //В кач-ве пар-ра передаётся id
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();  //Сразу находится нужный эл-т
  }

  public void initGroupModification() {
    click(By.name("edit"));
  }

  public void submitGroupModification() {
    click(By.name("update"));
  }

  public void create(GroupData group) {
    initGroupCreation();
    fillGroupForm(group);
    submitGroupCreation();
    groupCache = null;
    returnToGroupPage();
  }

  public void modify(GroupData group) {
    selectGroupById(group.getId());
    initGroupModification();
    fillGroupForm(group);
    submitGroupModification();
    groupCache = null;
    returnToGroupPage();
  }

  public void delete(GroupData group) {
    selectGroupById(group.getId());    //выбор группы по id
    deleteSelectedGroups();
    groupCache = null;
    returnToGroupPage();
  }

  public boolean isThereAGroup() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getGroupCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  private Groups groupCache = null;   //Реализация кеша

  public Groups all() {               //Set - тип возвращаемого значения
    if (groupCache != null) {         //Если кеш не пустой,
      return new Groups(groupCache);  //возврат копии
    }
    groupCache = new Groups();     //Создаётся множество элементов
    List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
    for (WebElement element : elements) {     //Проходим по всем элементам по указанному локатору
      String name = element.getText();        //Для каждого элемента достаём имя и id группы
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      groupCache.add(new GroupData().withId(id).withName(name)); //Создаём новый объект с именем и id и помещаем в множество
    }
    return new Groups(groupCache);
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }
}
