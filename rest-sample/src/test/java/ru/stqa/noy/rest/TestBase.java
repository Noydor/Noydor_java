package ru.stqa.noy.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.testng.SkipException;

import java.io.IOException;
import java.util.Set;

public class TestBase {

  public Executor getExecutor() {
    return Executor.newInstance().auth("28accbe43ea112d9feb328d2c00b3eed", "");
  }

  boolean isIssueOpen(int issueId) throws IOException {
    String status = getStatus(issueId);
    if (status.equals("Resolved")) {
      return false;
    } else {
      return true;
    }
  }

  private String getStatus(int issueId) throws IOException {
    String json = getExecutor().execute(Request.Get(String.format("http://demo.bugify.com/api/issues/%s.json", issueId))).returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    Set<Issue> issue = new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
    return issue.iterator().next().getStatus();
  }

  public void skipIfNotFixed(int issueId) throws IOException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }
}