package ru.stqa.noy.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.noy.addressbook.model.GroupData;

import java.util.Set;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.goTo().groupPage();
    Set<GroupData> before = app.group().all();
    GroupData group = new GroupData().withName("test2");
    app.group().create(group);
    Set<GroupData> after = app.group().all();
    Assert.assertEquals(after.size(), before.size() + 1);

    group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());  //Новой группе нужно присвоить правильный id (сначала опред-ся max id)
                                                                               //Поток объектов типа GroupData преобр-ся в поток id, т. е. чисел
    before.add(group);
    Assert.assertEquals(before, after);
  }

}
