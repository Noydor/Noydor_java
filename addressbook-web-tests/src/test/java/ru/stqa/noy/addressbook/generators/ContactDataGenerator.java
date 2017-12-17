package ru.stqa.noy.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.thoughtworks.xstream.XStream;
import ru.stqa.noy.addressbook.model.AddNewData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

  @Parameter(names = "-c", description = "Contact count")
  public int count;

  @Parameter(names = "-f", description = "Target file")
  public String file;

  @Parameter(names = "-d", description = "Data format")
  public String format;

  public static void main(String[] args) throws IOException {
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex) {
      jCommander.usage();
      return;
    }
    generator.run();
  }

  private void run() throws IOException {
    List<AddNewData> contacts = generateContacts(count);
    if (format.equals("csv")) {
      saveAsCsv(contacts, new File(file));
    } else if (format.equals("xml")) {
      saveAsXml(contacts, new File(file));
    } else {
      System.out.println("Unrecognized format " + format);
    }
  }

  private void saveAsXml(List<AddNewData> contacts, File file) throws IOException {
    XStream xstream = new XStream();
    xstream.processAnnotations(AddNewData.class);
    String xml = xstream.toXML(contacts);
    try (Writer writer = new FileWriter(file)) {
      writer.write(xml);
    }
  }

  private void saveAsCsv(List<AddNewData> contacts, File file) throws IOException {
    System.out.println(new File(".").getAbsolutePath());
    try (Writer writer = new FileWriter(file)) {
      for (AddNewData contact : contacts) {
        writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n", contact.getFirstname(), contact.getLastname(), contact.getNickname(), contact.getTitle(), contact.getCompany(), contact.getAddress()
                , contact.getHome(), contact.getMobile(), contact.getWork(), contact.getEmail(), contact.getHomepage()));
      }
    }
  }

  private List<AddNewData> generateContacts(int count) {
    List<AddNewData> contacts = new ArrayList<AddNewData>();
    for (int i = 0; i < count; i++) {
      contacts.add(new AddNewData().withFirstname(String.format("Samuel %s", i)).withLastname(String.format("Smith %s", i)).withNickname(String.format("Smithy %s", i))
              .withTitle(String.format("Mr %s", i)).withCompany(String.format("Seastar %s", i)).withAddress(String.format("Berlin, Niederkirchnerstrasse, 18 %s", i)).withHome(String.format("4955123456 %s", i))
              .withMobile(String.format("4976543210 %s", i)).withWork(String.format("123456 %s", i)).withEmail(String.format("samuelsmith@gmail.com %s", i))
              .withHomepage(String.format("samuelsmith.com %s", i)));
    }
    return contacts;
  }
}
