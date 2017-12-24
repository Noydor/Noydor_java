package ru.stqa.noy.mantis.appmanager;

import org.openqa.selenium.By;

public class ChangePasswordHelper extends HelperBase {

  public ChangePasswordHelper(ApplicationManager app) {
    super(app);
  }

  public void adminEnters(String username, String password) {
    wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
    type(By.name("username"), username);
    click(By.cssSelector("input[value='Login']"));
    type(By.name("password"), password);
    click(By.cssSelector("input[value='Login']"));
  }

  public void changePassword(String username) {
    click(By.linkText("Manage"));
    click(By.linkText("Manage Users"));
    click(By.linkText(username));
    click(By.xpath("//form[@id='manage-user-reset-form']/fieldset/span/input"));
  }

  public void finish(String changeConfirmationLink, String user_password) {
    wd.get(changeConfirmationLink);
    type(By.name("password"), user_password);
    type(By.name("password_confirm"), user_password);
    click(By.className("bigger-110"));
  }
}


