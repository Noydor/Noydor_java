package ru.stqa.noy.addressbook.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;

@Entity
@Table(name = "addressbook")

@XStreamAlias("contact")
public class AddNewData {
  @Id
  @Column(name = "id")
  @XStreamOmitField
  private int id = Integer.MAX_VALUE;

  @Column(name = "firstname")
  private String firstname;

  @Column(name = "lastname")
  private String lastname;

  private String nickname;

  private String title;

  private String company;

  @Column(name = "address")
  @Type(type = "text")
  private String address;

  @Column(name = "home")
  @Type(type = "text")
  private String home;

  @Column(name = "mobile")
  @Type(type = "text")
  private String mobile;

  @Column(name = "work")
  @Type(type = "text")
  private String work;

  @Column(name = "email")
  @Type(type = "text")
  private String email;

  @Transient
  private String email2;

  @Transient
  private String allEmails;

  @Column(name = "homepage")
  @Type(type = "text")
  private String homepage;

  @Transient
  private String group;

  @Transient
  private String allPhones;

  @Column(name = "photo")
  @Type(type = "text")
  private String photo;


  public File getPhoto() {
    return new File(photo);
  }

  public AddNewData withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
  }

  public String getAllPhones() {
    return allPhones;
  }

  public String getAllEmails() {
    return allEmails;
  }

  public AddNewData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public int getId() {
    return id;
  }

  public AddNewData withId(int id) {
    this.id = id;
    return this;
  }

  public AddNewData withFirstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public AddNewData withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public AddNewData withNickname(String nickname) {
    this.nickname = nickname;
    return this;
  }

  public AddNewData withTitle(String title) {
    this.title = title;
    return this;
  }

  public AddNewData withCompany(String company) {
    this.company = company;
    return this;
  }

  public AddNewData withAddress(String address) {
    this.address = address;
    return this;
  }

  public AddNewData withHome(String home) {
    this.home = home;
    return this;
  }

  public AddNewData withMobile(String mobile) {
    this.mobile = mobile;
    return this;
  }

  public AddNewData withWork(String work) {
    this.work = work;
    return this;
  }

  public AddNewData withEmail(String email) {
    this.email = email;
    return this;
  }

  public AddNewData withEmail2 (String email2) {
    this.email2 = email2;
    return this;
  }

  public AddNewData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }

  public AddNewData withHomepage(String homepage) {
    this.homepage = homepage;
    return this;
  }

  public AddNewData withGroup(String group) {
    this.group = group;
    return this;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getNickname() {
    return nickname;
  }

  public String getTitle() {
    return title;
  }

  public String getCompany() {
    return company;
  }

  public String getAddress() {
    return address;
  }

  public String getHome() {
    return home;
  }

  public String getMobile() {
    return mobile;
  }

  public String getWork() {
    return work;
  }

  public String getEmail() {
    return email;
  }

  public String getEmail2() {
    return email2;
  }

  public String getHomepage() {
    return homepage;
  }

  public String getGroup() {
    return group;
  }

  @Override
  public String toString() {
    return "AddNewData{" +
            "id='" + id + '\'' +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    AddNewData that = (AddNewData) o;

    if (id != that.id) return false;
    if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
    return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
    result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
    return result;
  }

}
