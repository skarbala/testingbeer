import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

public class SelenideShowOffTest {
  private static final String BASE_URL = "http://localhost:8888/";
  private WebDriver driver;

  @Before
  public void setUp() {
    System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
    driver = new ChromeDriver();
  }

  @Test
  public void testValue() {
    driver.get(BASE_URL + "waitforit.php");
    driver.findElement(By.cssSelector("#startWaitForText")).click();
    new WebDriverWait(driver, 10)
        .until(ExpectedConditions.textToBePresentInElementValue(
            By.cssSelector("#waitForTextInput")
            , "dary !!!")
        );
  }

  @Test
  public void testClass() {
    driver.get(BASE_URL + "waitforit.php");
    driver.findElement(By.cssSelector("#startWaitForProperty")).click();
    new WebDriverWait(driver, 10)
        .until(ExpectedConditions.attributeContains(
            By.cssSelector("#waitForProperty"),
            "class",
            "error"
            )
        );
  }


  @Test
  public void testSin() {
    driver.get(BASE_URL + "sincity.php");
    driver.findElement(By.name("title")).sendKeys("Insta za volantom");
    driver.findElement(By.name("author")).sendKeys("Bozana");
    driver.findElement(By.name("message")).sendKeys("Nahravala som sa na D1 a vykotilo ma do jarku");
    driver.findElement(By.cssSelector("button[type=submit]")).click();
    new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(
        By.cssSelector(".list-of-sins li:nth-of-type(1)")
    ));
    driver.findElement(By.cssSelector(".list-of-sins li:nth-of-type(1)")).click();
    new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(
        By.cssSelector("article p")
    ));
    Assert.assertEquals(
        "Nahravala som sa na D1 a vykotilo ma do jarku",
        driver.findElement(By.cssSelector("article p")).getText()
    );
  }

  @Test
  public void testTable() {
    driver.get(BASE_URL + "tabulka.php");
    System.out.println(driver.findElement(By.xpath("//tbody/tr[last()]")).getText());
  }

  @After
  public void tearDown() {
    driver.close();
    driver.quit();
  }

}
