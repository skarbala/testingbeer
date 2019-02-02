import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SelenideShowOffTest {
    private static final String BASE_URL = "http://localhost/";
    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
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
        new WebDriverWait(driver, 10).until(ExpectedConditions.textToBePresentInElementLocated(
                By.cssSelector("article p"),
                "Nahravala som sa na D1 a vykotilo ma do jarku"
        ));
        Assert.assertEquals(
                "Nahravala som sa na D1 a vykotilo ma do jarku",
                driver.findElement(By.cssSelector("article p")).getText()
        );
    }

    @After
    public void tearDown() {
        driver.close();
        driver.quit();
    }
}
