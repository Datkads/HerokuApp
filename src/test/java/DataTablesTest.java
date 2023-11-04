import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

public class DataTablesTest {
    WebDriver chrome;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        chrome = new ChromeDriver();
        chrome.manage().window().maximize();
        chrome.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        chrome.get("https://the-internet.herokuapp.com/tables");
    }

    @Test
    public void checkDataTables() {
        String email = chrome.findElement(By.xpath("//tbody/tr[1]/td[3]")).getText();
        Assert.assertEquals(email, "jsmith@gmail.com");

        String price = chrome.findElement(By.xpath("//tbody/tr[2]/td[4]")).getText();
        Assert.assertEquals(price, "$51.00");

        String link = chrome.findElement(By.xpath("//tbody/tr[3]/td[5]")).getText();
        Assert.assertEquals(link, "http://www.jdoe.com");

        String name = chrome.findElement(By.xpath("//tbody/tr[3]/td[2]")).getText();
        Assert.assertEquals(name, "Jason");

        String button = chrome.findElement(By.xpath("//tbody/tr[1]/td[6]/a[1]")).getText();
        Assert.assertEquals(button, "edit");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        chrome.quit();
    }
}
