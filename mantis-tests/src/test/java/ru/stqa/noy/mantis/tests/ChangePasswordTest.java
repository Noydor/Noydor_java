package ru.stqa.noy.mantis.tests;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.noy.mantis.model.MailMessage;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTest extends TestBase {

  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }

  @Test
  public void testChangePassword() throws IOException, MessagingException {
    long now = System.currentTimeMillis();
    String username = String.format("user%s", now);
    String user_password = "password";
    String user_email = String.format("user%s@localhost", now);
    app.registration().start(username, user_email);
    List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
    String registrationConfirmationLink = findConfirmationLink(mailMessages, user_email);
    app.registration().finish(registrationConfirmationLink, user_password);
    String admin_username = app.getProperty("web.adminLogin").toString();
    String admin_password = app.getProperty("web.adminPassword").toString();
    app.changePass().adminEnters(admin_username, admin_password);
    app.changePass().changePassword(username);
    List<MailMessage> newMailMessages = app.mail().waitForMail(2, 10000);
    String changeConfirmationLink = findChangeConfirmationLink(newMailMessages, user_email);
    String new_password = "new_password";
    app.changePass().finish(changeConfirmationLink, new_password);
    assertTrue(app.newSession().login(username, new_password));
  }

  private String findConfirmationLink(List<MailMessage> mailMessages, String user_email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(user_email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  private String findChangeConfirmationLink(List<MailMessage> newMailMessages, String user_email) {
    MailMessage mailMessage = newMailMessages.stream().filter((m) -> m.to.equals(user_email)).collect(Collectors.toList()).get(1);
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  @AfterMethod(alwaysRun = true)
  private void stopMailServer() {
     app.mail().stop();
   }
}
