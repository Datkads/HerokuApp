import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class HoversTest {
    WebDriver chrome;
    Actions action;
    WebElement profile;
    String profileName;
    List<WebElement> errors;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        chrome = new ChromeDriver();
        chrome.manage().window().maximize();
        chrome.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        chrome.get("https://the-internet.herokuapp.com/hovers");
    }

    @Test
    public void hoversCheckProfile1() {
        action = new Actions(chrome);
        profile = chrome.findElement(By.xpath("//div/div[1]/img"));
        action.moveToElement(profile).build().perform();
        profileName = chrome.findElement(By.xpath("//div/div[1]/div/h5")).getText();
        Assert.assertEquals(profileName, "name: user1");

        chrome.findElement(By.xpath("//div/div[1]/div/a")).click();
        errors = chrome.findElements(By.name("Not Found"));
        Assert.assertEquals(errors.size(), 0);
    }

    @Test
    public void hoversCheckProfile2() {
        action = new Actions(chrome);
        profile = chrome.findElement(By.xpath("//div/div[2]/img"));
        action.moveToElement(profile).build().perform();
        profileName = chrome.findElement(By.xpath("//div/div[2]/div/h5")).getText();
        Assert.assertEquals(profileName, "name: user2");

        chrome.findElement(By.xpath("//div/div[2]/div/a")).click();
        errors = chrome.findElements(By.name("Not Found"));
        Assert.assertEquals(errors.size(), 0);
    }

    @Test
    public void hoversCheckProfile3() {
        action = new Actions(chrome);
        profile = chrome.findElement(By.xpath("//div/div[3]/img"));
        action.moveToElement(profile).build().perform();
        profileName = chrome.findElement(By.xpath("//div/div[3]/div/h5")).getText();
        Assert.assertEquals(profileName, "name: user3");

        chrome.findElement(By.xpath("//div/div[3]/div/a")).click();
        errors = chrome.findElements(By.name("Not Found"));
        Assert.assertEquals(errors.size(), 0);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        chrome.quit();
    }
}
