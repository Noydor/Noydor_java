package ru.stqa.noy.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Groups extends ForwardingSet<GroupData> {  //Класс с библиотекой для построения коллекций с расшир. набором методов (Set - множества)

  private Set<GroupData> delegate;       //Объект для делегирования

  public Groups(Groups groups) {         //Конструктор для построения копии существующего объекта
    this.delegate = new HashSet<GroupData>(groups.delegate);  //Берём мн-во из существующего объекта, кот. передан в кач-ве пар-ра, строим новое мн-во,
                                                              //кот. содержит те же эл-ты, и присваиваем это мн-во в кач-ве атрибута в новый объект
  }

  public Groups() {
    this.delegate = new HashSet<GroupData>();
  }

  public Groups(Collection<GroupData> groups) {
    this.delegate = new HashSet<GroupData>(groups);
  }

  @Override
  protected Set<GroupData> delegate() {  //Один из обязательных методов (delegate)
    return delegate;
  }

  public Groups withAdded(GroupData group) { //Метод для создани копии, в кот. добавлена группа
    Groups groups = new Groups(this);
    groups.add(group);                    //После создания копии в неё добавляется объект group
    return groups;                        //Возврат копии с добавленной группой
  }

  public Groups without(GroupData group) { //Метод для создании копии, из кот. удалена группа
    Groups groups = new Groups(this);
    groups.remove(group);
    return groups;
  }

  public Groups withNewContact(AddNewData newContact, GroupData group) {
    Groups groups = newContact.getGroups();
    groups.add(group);
    return groups;
  }

  public Groups withoutContact(AddNewData delContact, GroupData group) {
    Groups groups = delContact.getGroups();
    groups.remove(group);
    return groups;
  }
}


