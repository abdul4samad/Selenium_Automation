package run;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import utilities.Utility;

import java.io.*;
import java.util.List;

public class NativeViewer {

    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/src/main/resources/drivers/chromedriver");
        //WebDriverManager.chromedriver().setup();
        Utility.driver = new ChromeDriver();
        Utility.driver.get("https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf");
        WebElement shadowHost = Utility.getLocator("id","viewer");

        // Use JavaScript to create a reference to the shadow root
        JavascriptExecutor jsExecutor = (JavascriptExecutor) Utility.driver;
        WebElement shadowRoot = (WebElement) jsExecutor.executeScript("return arguments[0].shadowRoot", shadowHost);

        // Now you can find elements within the shadow root as usual
        WebElement innerElement = shadowRoot.findElement(By.id("toolbar"));
        System.out.println(innerElement.getAttribute("style"));
    }

}
