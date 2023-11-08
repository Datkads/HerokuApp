import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class NotificationsTest {
    WebDriver chrome;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        chrome = new ChromeDriver();
        chrome.manage().window().maximize();
        chrome.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        chrome.get("https://the-internet.herokuapp.com/notification_message_rendered");
    }

    @Test
    public void notificationsCheck() {
        chrome.findElement(By.xpath("//div/p/a")).click();
        String notification = chrome.findElement(By.cssSelector("[id=\"flash\"]")).getText();
        Assert.assertEquals(notification, "Action successful\n" + "×", "Action unsuccesful, please try again");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        chrome.quit();
    }
}
