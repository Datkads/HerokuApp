import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CheckboxesTest {
    WebDriver chrome;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        chrome = new ChromeDriver();
        chrome.manage().window().maximize();
        chrome.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void checkCheckboxes() {
        chrome.get("https://the-internet.herokuapp.com/checkboxes");
        List<WebElement> checkboxes = chrome.findElements(By.cssSelector("[type=checkbox]"));

        boolean checkboxStatus = checkboxes.get(0).isSelected();
        boolean checkboxSelected = false;
        Assert.assertEquals(checkboxStatus, checkboxSelected);

        checkboxes.get(0).click();
        checkboxStatus = checkboxes.get(0).isSelected();
        Assert.assertTrue(checkboxStatus);

        checkboxStatus = checkboxes.get(1).isSelected();
        Assert.assertTrue(checkboxStatus);

        checkboxes.get(1).click();
        checkboxStatus = checkboxes.get(1).isSelected();
        Assert.assertEquals(checkboxStatus, checkboxSelected);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        chrome.quit();
    }
}
