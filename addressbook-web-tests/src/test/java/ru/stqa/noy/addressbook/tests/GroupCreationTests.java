package ru.stqa.noy.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.noy.addressbook.model.GroupData;
import ru.stqa.noy.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.goTo().groupPage();
    Groups before = app.group().all();
    GroupData group = new GroupData().withName("test2");
    app.group().create(group);
    assertThat(app.group().count(), equalTo(before.size() + 1));
    Groups after = app.group().all();

    //Новой группе нужно присвоить правильный id (сначала опред-ся max id)
    //Поток объектов типа GroupData преобр-ся в поток id, т. е. чисел
    assertThat(after, equalTo(before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));  //Вид проверки после добавления статич. импорта
  }

}
