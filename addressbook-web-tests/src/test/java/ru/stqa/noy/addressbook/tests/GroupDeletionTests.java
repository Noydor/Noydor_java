package ru.stqa.noy.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.noy.addressbook.model.GroupData;

import java.util.Set;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.group().all().size() == 0) {
      app.group().create(new GroupData().withName("test1"));
    }
  }

  @Test
  public void testGroupDeletion() {
    Set<GroupData> before = app.group().all();
    GroupData deletedGroup = before.iterator().next();  //iterator последов-но перебирает эл-ты. next возвращает к.-л. эл-т мн-ва
    app.group().delete(deletedGroup);                   //В кач-ве пар-ра передаётся удаляемая группа
    Set<GroupData> after = app.group().all();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(deletedGroup);    //По индексу из мн-ва удалить ничего нельзя, т. к. порядок не определён. Нужно исп-ть объект (deletedGroup)
    Assert.assertEquals(before, after);
  }

}
