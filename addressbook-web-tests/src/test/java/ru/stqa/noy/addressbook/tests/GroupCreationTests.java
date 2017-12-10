package ru.stqa.noy.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.noy.addressbook.model.GroupData;
import ru.stqa.noy.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

  @DataProvider                                                //Провайдер тестовых данных
  public Iterator<Object[]> validGroups() {                    //Итератор массива объектов
    List<Object[]> List = new ArrayList<Object[]>();
    List.add(new Object[] {new GroupData().withName("test1").withHeader("header 1").withFooter("footer 1")});  //1-й набор тестовых данных
    List.add(new Object[] {new GroupData().withName("test2").withHeader("header 2").withFooter("footer 2")});
    List.add(new Object[] {new GroupData().withName("test3").withHeader("header 3").withFooter("footer 3")});
    return List.iterator();
  }

  @Test(dataProvider = "validGroups")                               //Привязка провайдера к тесту
  public void testGroupCreation(GroupData group) {                  //Данные передаются извне
      app.goTo().groupPage();
      Groups before = app.group().all();
      app.group().create(group);
      assertThat(app.group().count(), equalTo(before.size() + 1));
      Groups after = app.group().all();
      assertThat(after, equalTo(before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));  //Вид проверки после добавления статич. импорта
    }

  }
