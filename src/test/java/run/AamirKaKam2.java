package run;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utilities.GoogleSheetHandler;
import utilities.Utility;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Set;

public class AamirKaKam2 {

    private static String sheetId="1ySEcHaybMtCVMXgMrULAbMS7aCq34Osm3sfpKBUCde4";
    private static String sheetName="Form responses 1";
    static String parent="";
    static Set<String> windows;

    @DataProvider(name="data")
    public static Object[][] readtData() throws IOException, GeneralSecurityException, InterruptedException {
        Object[][] data = GoogleSheetHandler.readData(sheetId, sheetName);
        Utility.driver = new ChromeDriver();
        Utility.driver.get("https://ab-hwc.nhp.gov.in/home/login");
        Utility.getLocator("user_name","id").sendKeys("hwc_up_varanasi");
        Utility.getLocator("pass","id").sendKeys("z6vo1pPgEm");
        Thread.sleep(10000);
        Utility.driver.manage().window().maximize();
        Utility.driver.get("https://ab-hwc.nhp.gov.in/beta/Facility_Profile");
        return data;
    }

    @Test(dataProvider = "data")
    public static void run(String sn, String blackName, String choName, String mobile, String email, String hwcName, String eamil, String degree, String ninNumber, String DOJ) throws InterruptedException {
        Utility.getLocator("//*[@type='search']","xpath").clear();
        Utility.getLocator("//*[@type='search']","xpath").sendKeys(hwcName);
        Thread.sleep(3000);
        Utility.getLocator("//*[@id='Facility_list_propose']/tbody/tr/td[2]/a","xpath").click();
        Thread.sleep(2000);
        windows = Utility.driver.getWindowHandles();
        parent = Utility.driver.getWindowHandle();
        for (String x: windows
             ) {
            if(!x.equalsIgnoreCase(parent)){
                Utility.driver.switchTo().window(x);
            }
        }

        WebElement elem = Utility.getLocator("//*[@id='staff_table']/tbody/tr/td[7]/a[1]","xpath");
        //Utility.scrollTillView(elem);
        elem.click();
        Select select =  new Select(Utility.getLocator("//select[@class='form-control type']","xpath"));
        select.selectByVisibleText(degree);
        Utility.getLocator("//div[@class='input-group date dop']/input","xpath").clear();
        Utility.getLocator("//div[@class='input-group date dop']/input","xpath").sendKeys(DOJ);
        Utility.getLocator("//input[@name='mobileno']","xpath").clear();
        Utility.getLocator("//input[@name='mobileno']","xpath").sendKeys(mobile);
        Utility.getLocator("//input[@name='emailid']","xpath").clear();
        Utility.getLocator("//input[@name='emailid']","xpath").sendKeys(email);
        Utility.getLocator("//button[@class='btn btn-primary addstaff']","xpath").click();
        Thread.sleep(2000);
    }

    @AfterMethod
    public void afterMethod(){
        windows = Utility.driver.getWindowHandles();
        for (String x: windows
        ) {
            if(!x.equalsIgnoreCase(parent)){
                Utility.driver.close();
            }
        }
        Utility.driver.switchTo().window(parent);
    }

}
