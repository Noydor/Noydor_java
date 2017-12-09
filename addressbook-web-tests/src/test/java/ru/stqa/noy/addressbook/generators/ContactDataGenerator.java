package ru.stqa.noy.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
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
    save(contacts, new File(file));
  }

  private void save(List<AddNewData> contacts, File file) throws IOException {
    System.out.println(new File(".").getAbsolutePath());
    Writer writer = new FileWriter(file);
    for (AddNewData contact : contacts) {
      writer.write(String.format("%s;%s;%s\n", contact.getFirstname(), contact.getLastname(), contact.getAddress()));
    }
    writer.close();
  }

  private List<AddNewData> generateContacts(int count) {
    List<AddNewData> contacts = new ArrayList<AddNewData>();
    for (int i = 0; i < count; i++) {
      contacts.add(new AddNewData().withFirstname(String.format("firstname %s", i)).withLastname(String.format("lastname %s", i)).withAddress(String.format("address %s", i)));
    }
    return contacts;
  }
}
