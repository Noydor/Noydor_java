package ru.stqa.noy.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.noy.addressbook.model.GroupData;
import ru.stqa.noy.addressbook.model.Groups;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

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
    Groups before = app.group().all();
    GroupData deletedGroup = before.iterator().next();  //iterator последов-но перебирает эл-ты. next возвращает к.-л. эл-т мн-ва
    app.group().delete(deletedGroup);                   //В кач-ве пар-ра передаётся удаляемая группа
    assertThat(app.group().count(), equalTo(before.size() -1));
    Groups after = app.group().all();
    assertThat(after, equalTo(before.without(deletedGroup)));  //По индексу из мн-ва удалить ничего нельзя, т. к. порядок не определён. Нужно исп-ть объект (deletedGroup)
  }

}
