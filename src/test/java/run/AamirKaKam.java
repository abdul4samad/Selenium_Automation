package run;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utilities.GoogleSheetHandler;
import utilities.Utility;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class AamirKaKam {

    private static String sheetId="1T3vZCP4xYoA0hryI23b_yucRGPeHQ3mbdYAp9DrKQTw";
    private static String sheetName="Sheet1";

    @DataProvider(name="data")
    public static Object[][] readtData() throws IOException, GeneralSecurityException, InterruptedException {
        Object[][] data = GoogleSheetHandler.readData(sheetId, sheetName);
        Utility.driver = new ChromeDriver();
        Utility.driver.get("https://upcovid19tracks.in/");
        Utility.getLocator("username","id").sendKeys("RRTVAR024");
        Utility.getLocator("password","id").sendKeys("rvar024#852");
        Thread.sleep(5000);
        Utility.driver.manage().window().maximize();
        Utility.driver.get("https://upcovid19tracks.in/cmo/add-case-register");
        return data;
    }

    @Test(dataProvider = "data")
    public static void run(String sn, String mobile, String name, String age, String gender) throws InterruptedException {
        /*Utility.driver.get("https://upcovid19tracks.in/");
        Utility.getLocator("username","id").sendKeys("RRTVAR024");
        Utility.getLocator("password","id").sendKeys("rvar024#852");
        Utility.getLocator("//div[@id='sidebar-menu']/div/ul/li[13]/a/span[text()='Cases']","xpath").click();
        Utility.getLocator("//*[@id=\"sidebar-menu\"]/div/ul/li[13]/ul/li[1]/a","xpath").click();*/
        Utility.getLocator("mobile","id").clear();
        Utility.getLocator("mobile","id").sendKeys(mobile);
        Utility.getLocator("//*[@id=\"addboardForm\"]/div[3]/div/div/div[2]/div","xpath").click();
        try {
            Utility.getLocator("//button[@class='swal-button swal-button--confirm']","xpath").click();
        }catch (Exception e){
            System.out.println("case not found");
            try {
                Utility.getLocator("//button[@class='close']","xpath").click();
            }catch (Exception z){
                System.out.println("cases found");
            }
        }

        Utility.getLocator("name","id").clear();
        Utility.getLocator("name","id").sendKeys(name);
        Utility.getLocator("age","id").clear();
        Utility.getLocator("age","id").sendKeys(age);
        Select select =  new Select(Utility.getLocator("gender","id"));
        select.selectByVisibleText(gender);
        select =  new Select(Utility.getLocator("block","id"));
        select.selectByVisibleText("VAR Block - Urban");
        select =  new Select(Utility.getLocator("occupation","id"));
        select.selectByVisibleText("Others");
        select =  new Select(Utility.getLocator("address_type","id"));
        select.selectByVisibleText("Urban");
        Utility.getLocator("address_1","id").clear();
        Utility.getLocator("address_1","id").sendKeys("Varanasi");
        Utility.getLocator("address_2","id").clear();
        Utility.getLocator("address_2","id").sendKeys("Varanasi");
        select =  new Select(Utility.getLocator("vaccination_taken","id"));
        select.selectByVisibleText("No");
        Utility.getLocator("permanent_address","id").clear();
        Utility.getLocator("permanent_address","id").sendKeys("Varanasi");
        select =  new Select(Utility.getLocator("permanent_address_state","id"));
        select.selectByVisibleText("UTTAR PRADESH");
        Thread.sleep(3000);
        select =  new Select(Utility.getLocator("permanent_address_district","id"));
        select.selectByVisibleText("VARANASI");
        select =  new Select(Utility.getLocator("case_type","id"));
        select.selectByVisibleText("Random selection");
        select =  new Select(Utility.getLocator("source","id"));
        select.selectByVisibleText("Others");
        Utility.getLocator("addBoard","id").click();
        Utility.getLocator("//button[@class='swal-button swal-button--confirm']","xpath").click();
    }

}
