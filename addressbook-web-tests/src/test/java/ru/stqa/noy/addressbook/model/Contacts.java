package ru.stqa.noy.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Contacts extends ForwardingSet<AddNewData> {

  private Set<AddNewData> delegate;

  public Contacts(Contacts contacts) {
    this.delegate = new HashSet<AddNewData>(contacts.delegate);
  }

  public Contacts() {
    this.delegate = new HashSet<AddNewData>();
  }

  public Contacts(Collection<AddNewData> contacts) {
    this.delegate = new HashSet<AddNewData>(contacts);
  }

  @Override
  protected Set<AddNewData> delegate() {
    return delegate;
  }

  public Contacts withAdded(AddNewData contact) {
    Contacts contacts = new Contacts(this);
    contacts.add(contact);
    return contacts;
  }

  public Contacts without(AddNewData contact) {
    Contacts contacts = new Contacts(this);
    contacts.remove(contact);
    return contacts;
  }

}
