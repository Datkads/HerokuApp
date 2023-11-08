import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TyposTest {
    WebDriver chrome;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        chrome = new ChromeDriver();
        chrome.manage().window().maximize();
        chrome.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        chrome.get("https://the-internet.herokuapp.com/typos");
    }

    @Test
    public void typosCheck() {
        String typos = chrome.findElement(By.xpath("//div/p[2]")).getText();
        Assert.assertEquals(typos, "Sometimes you'll see a typo, other times you won't.", "Вы нашли Typos!");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        chrome.quit();
    }
}