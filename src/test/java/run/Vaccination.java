package run;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import utilities.Utility;

import java.util.List;

public class Vaccination {

    @Test()
    public void run() throws InterruptedException {
        Utility utility = new Utility();
        System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"/src/main/resources/drivers/chromedriver.exe");
        utility.driver = new ChromeDriver();
        utility.driver.manage().window().maximize();
        utility.driver.get("https://app.cowin.gov.in/login");
        utility.getLocator("//*[text()='Mobile Number']/following-sibling::ion-input/input","xpath").sendKeys("8922932094");
        utility.getLocator("//*[text()='Password']/following-sibling::ion-input/input","xpath").sendKeys("CoVIN@1234");
        utility.getLocator("//*[contains(text(),'Login')]","xpath").click();
        utility.getLocator("//*[@name='close-circle']","xpath").click();
        utility.getLocator("//*[text()='Session Details']","xpath").getText();
        utility.getLocator("//*[contains(text(),'On Going')]/../../preceding-sibling::td[contains(text(),'CORBEVAX')]","xpath").click();
        utility.getLocator("//*[contains(text(),'Verified')]/../..","xpath").click();
        int x=0;
        while (x<3000){
            try{
                String xpath = "//*[text()='Citizen ']/..";
                List<WebElement> elem = utility.getLocatorList(xpath,"xpath");
//                Actions actions = new Actions(utility.driver);
//                actions.sendKeys(Keys.PAGE_DOWN).build().perform();
                elem.get(1).click();
                //utility.getLocator(xpath,"xpath").click();
                xpath = "//*[contains(text(),'Confirm')]";
                utility.getLocator(xpath,"xpath").click();
                x++;
            }catch (Exception e){
                x++;
            }
        }
    }

    @Test()
    public void run2() throws InterruptedException {
        Utility utility = new Utility();
        System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"/src/main/resources/drivers/chromedriver.exe");
        utility.driver = new ChromeDriver();
        utility.driver.manage().window().maximize();
        utility.driver.get("https://app.cowin.gov.in/login");
        utility.getLocator("//*[text()='Mobile Number']/following-sibling::ion-input/input","xpath").sendKeys("8922932094");
        utility.getLocator("//*[text()='Password']/following-sibling::ion-input/input","xpath").sendKeys("CoVIN@1234");
        utility.getLocator("//*[contains(text(),'Login')]","xpath").click();
        utility.getLocator("//*[@name='close-circle']","xpath").click();
        utility.getLocator("//*[text()='Session Details']","xpath").getText();
        utility.getLocator("//*[contains(text(),'On Going')]/../../preceding-sibling::td[contains(text(),'CORBEVAX')]","xpath").click();
        utility.getLocator("//*[contains(text(),'Verified')]/../..","xpath").click();
        int x=0;
        while (x<3000){
            try{
                String xpath = "//*[text()='Citizen ']/..";
                List<WebElement> elem = utility.getLocatorList(xpath,"xpath");
                Actions actions = new Actions(utility.driver);
                actions.sendKeys(Keys.PAGE_DOWN).build().perform();
                elem.get(5).click();
                //utility.getLocator(xpath,"xpath").click();
                xpath = "//*[contains(text(),'Confirm')]";
                utility.getLocator(xpath,"xpath").click();
                xpath="//ion-button[contains(text(),'Verify')]";
                utility.getLocator(xpath,"xpath").click();
                x++;
            }catch (Exception e){
                x++;
            }
        }
    }

    @Test()
    public void run3() throws InterruptedException {
        Utility utility = new Utility();
        System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"/src/main/resources/drivers/chromedriver.exe");
        utility.driver = new ChromeDriver();
        utility.driver.manage().window().maximize();
        utility.driver.get("https://app.cowin.gov.in/login");
        utility.getLocator("//*[text()='Mobile Number']/following-sibling::ion-input/input","xpath").sendKeys("8922932094");
        utility.getLocator("//*[text()='Password']/following-sibling::ion-input/input","xpath").sendKeys("CoVIN@1234");
        utility.getLocator("//*[contains(text(),'Login')]","xpath").click();
        utility.getLocator("//*[@name='close-circle']","xpath").click();
        utility.getLocator("//*[text()='Session Details']","xpath").getText();
        utility.getLocator("//*[contains(text(),'On Going')]/../../preceding-sibling::td[contains(text(),'CORBEVAX')]","xpath").click();
        int x=0;
        while (x<3000){
            try{
                String xpath = "//*[text()='Citizen ']/..";
                List<WebElement> elem = utility.getLocatorList(xpath,"xpath");
                Actions actions = new Actions(utility.driver);
                actions.sendKeys(Keys.PAGE_DOWN).build().perform();
                elem.get(4).click();
                //utility.getLocator(xpath,"xpath").click();
                xpath = "//*[contains(text(),'Other Type')]";
                utility.getLocator(xpath,"xpath").click();
                xpath="//ion-button[contains(text(),'Verify')]";
                utility.getLocator(xpath,"xpath").click();
                x++;
            }catch (Exception e){
                x++;
            }
        }
    }

    @Test()
    public void run4() throws InterruptedException {
        Utility utility = new Utility();
        System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"/src/main/resources/drivers/chromedriver.exe");
        utility.driver = new ChromeDriver();
        utility.driver.manage().window().maximize();
        utility.driver.get("https://app.cowin.gov.in/login");
        utility.getLocator("//*[text()='Mobile Number']/following-sibling::ion-input/input","xpath").sendKeys("8922932094");
        utility.getLocator("//*[text()='Password']/following-sibling::ion-input/input","xpath").sendKeys("CoVIN@1234");
        utility.getLocator("//*[contains(text(),'Login')]","xpath").click();
        utility.getLocator("//*[@name='close-circle']","xpath").click();
        utility.getLocator("//*[text()='Session Details']","xpath").getText();
        utility.getLocator("//*[contains(text(),'On Going')]/../../preceding-sibling::td[contains(text(),'CORBEVAX')]","xpath").click();
        int x=0;
        while (x<3000){
            try{
                String xpath = "//*[text()='Citizen ']/..";
                List<WebElement> elem = utility.getLocatorList(xpath,"xpath");
                Actions actions = new Actions(utility.driver);
                actions.sendKeys(Keys.PAGE_DOWN).build().perform();
                elem.get(5).click();
                //utility.getLocator(xpath,"xpath").click();
                xpath = "//*[contains(text(),'Other Type')]";
                utility.getLocator(xpath,"xpath").click();
                xpath="//ion-button[contains(text(),'Verify')]";
                utility.getLocator(xpath,"xpath").click();
                x++;
            }catch (Exception e){
                x++;
            }
        }
    }

    @Test()
    public void run5() throws InterruptedException {
        Utility utility = new Utility();
        System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"/src/main/resources/drivers/chromedriver.exe");
        utility.driver = new ChromeDriver();
        utility.driver.manage().window().maximize();
        utility.driver.get("https://app.cowin.gov.in/login");
        utility.getLocator("//*[text()='Mobile Number']/following-sibling::ion-input/input","xpath").sendKeys("8922932094");
        utility.getLocator("//*[text()='Password']/following-sibling::ion-input/input","xpath").sendKeys("CoVIN@1234");
        utility.getLocator("//*[contains(text(),'Login')]","xpath").click();
        utility.getLocator("//*[@name='close-circle']","xpath").click();
        utility.getLocator("//*[text()='Session Details']","xpath").getText();
        utility.getLocator("//*[contains(text(),'On Going')]/../../preceding-sibling::td[contains(text(),'CORBEVAX')]","xpath").click();
        int x=0;
        while (x<3000){
            try{
                String xpath = "//*[text()='Citizen ']/..";
                List<WebElement> elem = utility.getLocatorList(xpath,"xpath");
                Actions actions = new Actions(utility.driver);
                actions.sendKeys(Keys.PAGE_DOWN).build().perform();
                elem.get(6).click();
                //utility.getLocator(xpath,"xpath").click();
                xpath = "//*[contains(text(),'Other Type')]";
                utility.getLocator(xpath,"xpath").click();
                xpath="//ion-button[contains(text(),'Verify')]";
                utility.getLocator(xpath,"xpath").click();
                x++;
            }catch (Exception e){
                x++;
            }
        }
    }

    @Test()
    public void run6() throws InterruptedException {
        Utility utility = new Utility();
        System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"/src/main/resources/drivers/chromedriver.exe");
        utility.driver = new ChromeDriver();
        utility.driver.manage().window().maximize();
        utility.driver.get("https://app.cowin.gov.in/login");
        utility.getLocator("//*[text()='Mobile Number']/following-sibling::ion-input/input","xpath").sendKeys("8922932094");
        utility.getLocator("//*[text()='Password']/following-sibling::ion-input/input","xpath").sendKeys("CoVIN@1234");
        utility.getLocator("//*[contains(text(),'Login')]","xpath").click();
        utility.getLocator("//*[@name='close-circle']","xpath").click();
        utility.getLocator("//*[text()='Session Details']","xpath").getText();
        utility.getLocator("//*[contains(text(),'On Going')]/../../preceding-sibling::td[contains(text(),'CORBEVAX')]","xpath").click();
        int x=0;
        while (x<3000){
            try{
                String xpath = "//*[text()='Citizen ']/..";
                List<WebElement> elem = utility.getLocatorList(xpath,"xpath");
                Actions actions = new Actions(utility.driver);
                actions.sendKeys(Keys.PAGE_DOWN).build().perform();
                elem.get(7).click();
                //utility.getLocator(xpath,"xpath").click();
                xpath = "//*[contains(text(),'Other Type')]";
                utility.getLocator(xpath,"xpath").click();
                xpath="//ion-button[contains(text(),'Verify')]";
                utility.getLocator(xpath,"xpath").click();
                x++;
            }catch (Exception e){
                x++;
            }
        }
    }
}
