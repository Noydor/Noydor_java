package ru.stqa.noy.addressbook.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "addressbook")

@XStreamAlias("contact")
public class AddNewData {
  @Override
  public String toString() {
    return "AddNewData{" +
            "id=" + id +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            ", nickname='" + nickname + '\'' +
            ", title='" + title + '\'' +
            ", company='" + company + '\'' +
            ", address='" + address + '\'' +
            ", home='" + home + '\'' +
            ", mobile='" + mobile + '\'' +
            ", work='" + work + '\'' +
            ", email='" + email + '\'' +
          //  ", email2='" + email2 + '\'' +
            ", homepage='" + homepage + '\'' +
          //  ", group='" + group + '\'' +
          //  ", photo='" + photo + '\'' +
            '}';
  }

  @XStreamOmitField
  @Id
  @Column(name = "id")
  private int id = Integer.MAX_VALUE;

  @Column(name = "firstname")
  private String firstname;

  @Column(name = "lastname")
  private String lastname;

  @Column(name = "nickname")
  private String nickname;

  @Column(name = "title")
  private String title;

  @Column(name = "company")
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
  private String allPhones;

  @Transient
  @Column(name = "photo")
  @Type(type = "text")
  private String photo;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "address_in_groups",
          joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
  private static Set<GroupData> groups = new HashSet<GroupData>();

  public static Groups getGroups() {
    return new Groups(groups);
  }



  /*public File getPhoto() {
    return new File(photo);
  }*/

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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    AddNewData that = (AddNewData) o;

    if (id != that.id) return false;
    if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
    if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
    if (nickname != null ? !nickname.equals(that.nickname) : that.nickname != null) return false;
    if (title != null ? !title.equals(that.title) : that.title != null) return false;
    if (company != null ? !company.equals(that.company) : that.company != null) return false;
    if (address != null ? !address.equals(that.address) : that.address != null) return false;
    if (home != null ? !home.equals(that.home) : that.home != null) return false;
    if (mobile != null ? !mobile.equals(that.mobile) : that.mobile != null) return false;
    if (work != null ? !work.equals(that.work) : that.work != null) return false;
    if (email != null ? !email.equals(that.email) : that.email != null) return false;
    if (email2 != null ? !email2.equals(that.email2) : that.email2 != null) return false;
    if (homepage != null ? !homepage.equals(that.homepage) : that.homepage != null) return false;
    //if (group != null ? !group.equals(that.group) : that.group != null) return false;
    return photo != null ? photo.equals(that.photo) : that.photo == null;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
    result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
    result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
    result = 31 * result + (title != null ? title.hashCode() : 0);
    result = 31 * result + (company != null ? company.hashCode() : 0);
    result = 31 * result + (address != null ? address.hashCode() : 0);
    result = 31 * result + (home != null ? home.hashCode() : 0);
    result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
    result = 31 * result + (work != null ? work.hashCode() : 0);
    result = 31 * result + (email != null ? email.hashCode() : 0);
    result = 31 * result + (email2 != null ? email2.hashCode() : 0);
    result = 31 * result + (homepage != null ? homepage.hashCode() : 0);
    //result = 31 * result + (group != null ? group.hashCode() : 0);
    result = 31 * result + (photo != null ? photo.hashCode() : 0);
    return result;
  }

  public AddNewData inGroup(GroupData group) {
    groups.add(group);
    return this;
  }
}
