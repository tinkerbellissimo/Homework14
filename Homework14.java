import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Created by tinkerbellissimo on 1/21/17.
 */
public class Homework14 {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        System.setProperty("webdriver.chrome.driver", "/Users/tinkerbellissimo/Downloads/chromedriver");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void newWindow() throws InterruptedException {
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        clickOnRandomCountry();

        List<WebElement> links = driver.findElements(By.className("fa-external-link"));
        for (WebElement link : links) {
            String originalWindow = driver.getWindowHandle();
            link.click();
            Thread.sleep(5);
            Set<String> newWindows = driver.getWindowHandles();
            newWindows.remove(originalWindow);
            for (String newWindow : newWindows) {
                driver.switchTo().window(newWindow);
                driver.close();
                driver.switchTo().window(originalWindow);
            }
        }
    }

    public void clickOnRandomCountry() {
        List<WebElement> country = driver.findElements(By.cssSelector(".fa.fa-pencil"));
        Random r = new Random();
        int randomCountry = r.nextInt(country.size());
        country.get(randomCountry).click();
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}

