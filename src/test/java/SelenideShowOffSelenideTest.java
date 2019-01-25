import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import com.codeborne.selenide.Configuration;

public class SelenideShowOffSelenideTest {

  @Before
  public void setUp() {
    Configuration.baseUrl = "http://localhost:8888/";
    Configuration.startMaximized = true;
    Configuration.holdBrowserOpen = false;
  }

  @Test
  public void testValue() {
    open("waitforit.php");
    $("#startWaitForText").click();
    $("#waitForTextInput").shouldHave(value("dary !!!"));
  }

  @Test
  public void testClass() {
    open("waitforit.php");
    $("#startWaitForProperty").click();
    $("#waitForProperty").shouldHave(cssClass("error"));
  }

  @Test
  public void testSin() {
    open("sincity.php");
    $(By.name("title")).val("Insta za volantom");
    $(By.name("author")).val("Bozana");
    $(By.name("message")).val("Nahravala som sa na D1 a vykotilo ma do jarku");
    $("button[type=submit]").click();
    $(".list-of-sins li:nth-of-type(1)").shouldBe(visible).click();
    $("article p").shouldHave(text("Nahravala som sa na D1 a vykotilo ma do jarku"));
  }

  @Test
  public void testTable() {
    open("tabulka.php");
    System.out.println($("tbody").lastChild());
  }
}
