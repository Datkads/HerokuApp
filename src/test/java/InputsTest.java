import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

public class InputsTest {
    WebDriver chrome;
    WebElement inputField;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        chrome = new ChromeDriver();
        chrome.manage().window().maximize();
        chrome.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        chrome.get("https://the-internet.herokuapp.com/inputs");
        inputField = chrome.findElement(By.tagName("input"));
    }

    @Test
    public void checkDigitInputs() {
        for (int i = 0; i < 10; i++) {
            inputField.sendKeys(Keys.ARROW_UP);
        }
        String input = inputField.getAttribute("value");
        Assert.assertEquals(input, "10");

        inputField.clear();

        for (int i = 0; i > -10; i--) {
            inputField.sendKeys(Keys.ARROW_DOWN);
        }
        input = inputField.getAttribute("value");
        Assert.assertEquals(input, "-10");
    }

    @Test
    public void checkSymbolsInputs() {
        inputField.sendKeys("qwerty");
        String input = inputField.getAttribute("value");
        Assert.assertEquals(input, "", "Symbols input are allowed");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        chrome.quit();
    }
}
