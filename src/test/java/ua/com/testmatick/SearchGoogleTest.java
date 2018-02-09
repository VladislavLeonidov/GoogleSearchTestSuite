package ua.com.testmatick;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SearchGoogleTest {

    private WebDriver chromeDriver;
    private String startUrl;

    @BeforeTest
    private void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:/browsersDrivers/chromedriver.exe");
        chromeDriver = new ChromeDriver();
        chromeDriver.manage().window().maximize();
        chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        startUrl = "https://www.google.com";
    }

    @Test
    public void searchTest() {
        chromeDriver.get(startUrl);
        WebElement searchField = chromeDriver.findElement(By.id("lst-ib"));
        searchField.sendKeys("selenium driver");
        WebElement searchButton = chromeDriver.findElement(By.xpath("//div//li[@class='gsfs']//input[@class='lsb']"));
        searchButton.click();
        List<WebElement> results = chromeDriver.findElements(By.xpath("//div[@id='ires']//div[@class='srg']/div"));
        Assert.assertEquals(results.size(), 10);
    }

    @AfterTest
    private void tearDown() {
        if (chromeDriver != null) {
            chromeDriver.quit();
        }
    }
}
