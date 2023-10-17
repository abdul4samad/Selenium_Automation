package run;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utilities.GoogleSheetHandler;
import utilities.Utility;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class AamirKaKam3 {

    private static String sheetId="1JNr-pmCo-ChztRLYG_FLAR7GiVLB3FOb--vTz5Na6SU";
    private static String sheetName="Sheet2";
    int rowNum=1;
    private static String errorMsg = "";
    @DataProvider(name="data")
    public static Object[][] readtData() throws IOException, GeneralSecurityException, InterruptedException {
        Object[][] data = GoogleSheetHandler.readData(sheetId, sheetName);
        Utility.driver = new ChromeDriver();
        Utility.driver.get("https://rchrpt.nhm.gov.in/RCHRPT/");
        Utility.driver.manage().window().maximize();
        Utility.getLocator("close-btn","id").click();
        Utility.getLocator("//a[text()='DATA ENTRY']","xpath").click();
        Select select =  new Select(Utility.getLocator("ddlStateName","id"));
        select.selectByVisibleText("Uttar Pradesh (9)");
        Utility.getLocator("txtUserName","id").sendKeys("uphcvns");
        Utility.getLocator("txtPassword","id").sendKeys("abcd@1234");
        Thread.sleep(10000);
        Utility.getLocator("SMSMenu","id").click();
        select = new Select(Utility.getLocator("SingleMainContent_UserInfoBoxControl2_ddlFacilityType","id"));
        select.selectByVisibleText("Urban Health Centre (UHC)");
        Thread.sleep(2000);
        select = new Select(Utility.getLocator("SingleMainContent_UserInfoBoxControl2_ddlPhc","id"));
        select.selectByVisibleText("PANDEYPUR (4173) ");
        Thread.sleep(2000);
        select = new Select(Utility.getLocator("SingleMainContent_UserInfoBoxControl2_ddlSubCentre","id"));
        select.selectByVisibleText("Pandeypur (27273) ");
        Thread.sleep(2000);
        select = new Select(Utility.getLocator("SingleMainContent_UserInfoBoxControl2_ddlVillage","id"));
        select.selectByVisibleText("HARIJAN BASTI TAKTAKPUR (10047864)* (10047864) ");
        Utility.getLocator("SingleMainContent_UserInfoBoxControl2_btnSave","id").click();
        return data;
    }

    @Test(dataProvider = "data")
    public static void run(String sno, String RCHNum, String status) throws Exception {
        Utility.getLocator("WorkplanNewMenu","id").click();
        Utility.getLocator("txtRCH_MCTS_ID", "id").sendKeys(RCHNum);
        Utility.getLocator("txtRCH_MCTS_ID", "id").sendKeys(Keys.ENTER);
        Thread.sleep(1000);
        Utility.getLocator("//*[text()='ANC Details']", "xpath").click();
        List<WebElement> list = Utility.driver.findElements(By.xpath("//table[@id='SingleMainContent_DoubleMainContent_lvDetails_customers']/tbody/tr"));
        int totalRows = list.size() - 1;
        int countClick = totalRows - 1;
        int weight = 0;
        String lmpDate="";
        try{
            for (int i = 0; i <= countClick; i++) {
                errorMsg = "";
                if(Utility.getLocator("SingleMainContent_DoubleMainContent_lvDetails_lblANCTypeListView_"+i,"id").getText().equalsIgnoreCase("ANC - "+(i+1))){
                    continue;
                }
            WebElement element = Utility.getLocator("SingleMainContent_DoubleMainContent_lvDetails_lnkSelect_" + i, "id");
            element.sendKeys("");
            Utility.clickByJavaScript(element);
            Thread.sleep(5000);
            Select select = new Select(Utility.getLocator("SingleMainContent_DoubleMainContent_ddlAnm", "id"));
            select.selectByVisibleText("Reena Gupta(205058)");
            select = new Select(Utility.getLocator("SingleMainContent_DoubleMainContent_ddlAsha", "id"));
            Random random = new Random();
            int low = 0;
            int high = 6;
            int result = random.nextInt(high - low) + low;
            select.selectByIndex(result);
            select = new Select(Utility.getLocator("SingleMainContent_DoubleMainContent_ddlFPforANCDone", "id"));
            select.selectByVisibleText("Other Private Hospital");
            Thread.sleep(3000);
            lmpDate = Utility.getLocator("SingleMainContent_DoubleMainContent_lblLMP","id").getText();
            //Utility.getLocator("SingleMainContent_DoubleMainContent_txtFacilityPlaceName", "id").clear();
            Utility.valueByJavaScript(Utility.getLocator("SingleMainContent_DoubleMainContent_txtFacilityPlaceName", "id"),"Jkno");
            String ancDate = "";
            switch (i) {
                case 0:
                    ancDate = getDateAfterDays(lmpDate,70);
                    int textSize=Utility.getLocatorList("SingleMainContent_DoubleMainContent_lblTTMsg","id").size();
                    if(textSize==0) {
                        select = new Select(Utility.getLocator("SingleMainContent_DoubleMainContent_ddlTT", "id"));
                        select.selectByVisibleText("TT1");
                        Thread.sleep(1000);
                        //Utility.getLocator("SingleMainContent_DoubleMainContent_txtTt1Date", "id").clear();
                        Utility.valueByJavaScript(Utility.getLocator("SingleMainContent_DoubleMainContent_txtTt1Date", "id"),ancDate);
                        Utility.getLocator("SingleMainContent_DoubleMainContent_txtTt1Date", "id").sendKeys(Keys.TAB);
                        List<WebElement> list2 = Utility.getLocatorList("//input[@id='SingleMainContent_DoubleMainContent_txtTt1Date']/../span","xpath");
                        for(WebElement element2: list2){
                            if(element2.isDisplayed()){
                                errorMsg = element2.getText();
                            }
                        }
                    }
                    Utility.valueByJavaScript(Utility.getLocator("SingleMainContent_DoubleMainContent_txtFolicAcidTabs","id"), 60+"");
                    break;
                case 1:
                    ancDate = getDateAfterDays(lmpDate,7*20);
                    textSize=Utility.getLocatorList("SingleMainContent_DoubleMainContent_lblTTMsg","id").size();
                    if(textSize==0) {
                        select = new Select(Utility.getLocator("SingleMainContent_DoubleMainContent_ddlTT", "id"));
                        select.selectByVisibleText("TT2");
                        Thread.sleep(1000);
                        Utility.getLocator("SingleMainContent_DoubleMainContent_txtTt1Date", "id").clear();
                        Utility.getLocator("SingleMainContent_DoubleMainContent_txtTt1Date", "id").sendKeys(ancDate);
                        Utility.getLocator("SingleMainContent_DoubleMainContent_txtTt1Date", "id").sendKeys(Keys.TAB);
                        List<WebElement> list2 = Utility.getLocatorList("//input[@id='SingleMainContent_DoubleMainContent_txtTt1Date']/../span","xpath");
                        for(WebElement element2: list2){
                            if(element2.isDisplayed()){
                                errorMsg = element2.getText();
                            }
                        }
                    }
                    Utility.valueByJavaScript(Utility.getLocator("SingleMainContent_DoubleMainContent_txtIfaTabs","id"), 180+"");
                    break;
                case 2:
                    ancDate = getDateAfterDays(lmpDate,7*30);
                    break;
                case 3:
                    ancDate = getDateAfterDays(lmpDate,7*37);
                    break;
            }
            Utility.getLocator("SingleMainContent_DoubleMainContent_txtAncDate", "id").clear();
            Thread.sleep(500);
            Utility.getLocator("SingleMainContent_DoubleMainContent_txtAncDate", "id").sendKeys(ancDate);
            Utility.getLocator("SingleMainContent_DoubleMainContent_txtAncDate", "id").sendKeys(Keys.TAB);
            List<WebElement> list2 = Utility.getLocatorList("//input[@id='SingleMainContent_DoubleMainContent_txtAncDate']/../span","xpath");
            for(WebElement element2: list2){
                if(element2.isDisplayed()){
                    errorMsg = element2.getText();
                }
            }
            weight = Integer.parseInt(Utility.getLocator("SingleMainContent_DoubleMainContent_txtMotherWeight", "id").getAttribute("value"));
            element = Utility.getLocator("SingleMainContent_DoubleMainContent_btnSave", "id");
            Utility.clickByJavaScript(element);
            Thread.sleep(3000);
            //element.sendKeys(Keys.SPACE);
                errorMsg = Utility.driver.switchTo().alert().getText();
            Utility.driver.switchTo().alert().accept();
        }

        for (int i = totalRows; i < 4; i++) {
            errorMsg = "";
            Select select = new Select(Utility.getLocator("SingleMainContent_DoubleMainContent_ddlAnm", "id"));
            select.selectByVisibleText("Reena Gupta(205058)");
            select = new Select(Utility.getLocator("SingleMainContent_DoubleMainContent_ddlAsha", "id"));
            Random random = new Random();
            int low = 0;
            int high = 6;
            int result = random.nextInt(high - low) + low;
            select.selectByIndex(result);
            Thread.sleep(1000);
            select = new Select(Utility.getLocator("SingleMainContent_DoubleMainContent_ddlFPforANCDone", "id"));
            select.selectByVisibleText("Other Private Hospital");
            Thread.sleep(2000);
            Utility.getLocator("SingleMainContent_DoubleMainContent_txtFacilityPlaceName", "id").clear();
            Utility.getLocator("SingleMainContent_DoubleMainContent_txtFacilityPlaceName", "id").sendKeys("Jkno");
            lmpDate = Utility.getLocator("SingleMainContent_DoubleMainContent_lblLMP","id").getText();
            String ancDate = "";
            switch (i) {
                case 0:
                    ancDate = getDateAfterDays(lmpDate,70);
                    int textSize=Utility.getLocatorList("SingleMainContent_DoubleMainContent_lblTTMsg","id").size();
                    if(textSize==0) {
                        select = new Select(Utility.getLocator("SingleMainContent_DoubleMainContent_ddlTT", "id"));
                        select.selectByVisibleText("TT1");
                        Thread.sleep(1000);
                        Utility.getLocator("SingleMainContent_DoubleMainContent_txtTt1Date", "id").clear();
                        Utility.getLocator("SingleMainContent_DoubleMainContent_txtTt1Date", "id").sendKeys(ancDate);
                        Utility.getLocator("SingleMainContent_DoubleMainContent_txtTt1Date", "id").sendKeys(Keys.TAB);
                        List<WebElement> list2 = Utility.getLocatorList("//input[@id='SingleMainContent_DoubleMainContent_txtTt1Date']/../span","xpath");
                        for(WebElement element2: list2){
                            if(element2.isDisplayed()){
                                errorMsg = element2.getText();
                            }
                        }
                    }
                    Utility.valueByJavaScript(Utility.getLocator("SingleMainContent_DoubleMainContent_txtFolicAcidTabs","id"), 60+"");
                    break;
                case 1:
                    ancDate = getDateAfterDays(lmpDate,7*20);
                    textSize=Utility.getLocatorList("SingleMainContent_DoubleMainContent_lblTTMsg","id").size();
                    if(textSize==0) {
                        select = new Select(Utility.getLocator("SingleMainContent_DoubleMainContent_ddlTT", "id"));
                        select.selectByVisibleText("TT2");
                        Thread.sleep(1000);
                        Utility.getLocator("SingleMainContent_DoubleMainContent_txtTt1Date", "id").clear();
                        Utility.getLocator("SingleMainContent_DoubleMainContent_txtTt1Date", "id").sendKeys(ancDate);
                        Utility.getLocator("SingleMainContent_DoubleMainContent_txtTt1Date", "id").sendKeys(Keys.TAB);
                        List<WebElement> list2 = Utility.getLocatorList("//input[@id='SingleMainContent_DoubleMainContent_txtTt1Date']/../span","xpath");
                        for(WebElement element2: list2){
                            if(element2.isDisplayed()){
                                errorMsg = element2.getText();
                            }
                        }
                    }
                    Utility.valueByJavaScript(Utility.getLocator("SingleMainContent_DoubleMainContent_txtIfaTabs","id"), 180+"");
                    break;
                case 2:
                    ancDate = getDateAfterDays(lmpDate,7*30);
                    break;
                case 3:
                    ancDate = getDateAfterDays(lmpDate,7*37);
                    break;
            }
            //Utility.getLocator("SingleMainContent_DoubleMainContent_txtAncDate", "id").clear();
            Utility.valueByJavaScript(Utility.getLocator("SingleMainContent_DoubleMainContent_txtAncDate", "id"),ancDate);
            Utility.getLocator("SingleMainContent_DoubleMainContent_txtAncDate", "id").sendKeys(Keys.TAB);
            List<WebElement> list2 = Utility.getLocatorList("//input[@id='SingleMainContent_DoubleMainContent_txtTt1Date']/../span","xpath");
            for(WebElement element2: list2){
                if(element2.isDisplayed()){
                    errorMsg = element2.getText();
                }
            }
            if(weight<10){
                weight = random.nextInt(70 - 60)+60;
            }
            //Utility.getLocator("SingleMainContent_DoubleMainContent_txtMotherWeight", "id").clear();
            Utility.valueByJavaScript(Utility.getLocator("SingleMainContent_DoubleMainContent_txtMotherWeight", "id"),""+(weight++));
            WebElement element = Utility.getLocator("SingleMainContent_DoubleMainContent_btnSave", "id");
            Utility.clickByJavaScript(element);
            Thread.sleep(3000);
            errorMsg = Utility.driver.switchTo().alert().getText();
            Utility.driver.switchTo().alert().accept();
        }
    }catch (Exception e){
            e.printStackTrace();
            throw new Exception(e);
        }

    }

    @AfterMethod
    public void afterMethod(ITestResult iTestResult) throws IOException, GeneralSecurityException {
        if (iTestResult.isSuccess()){
            GoogleSheetHandler.setCellValue(sheetId,sheetName,rowNum,2,"Pass"+(errorMsg.length()>0?": "+errorMsg:""));
            errorMsg = "";
        }else {
            GoogleSheetHandler.setCellValue(sheetId,sheetName,rowNum,2,"Fail"+(errorMsg.length()>0?": "+errorMsg:""));
            errorMsg = "";
        }
        rowNum++;
    }

    public static String getDateAfterDays(String LMPDate, int extraDays){
        SimpleDateFormat sdfSource = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;
        try {
            date = sdfSource.parse(LMPDate);
        } catch (Exception ex) {
            System.out.println("Exception "+ex);
        }

        // Create calendar object and add the days to the date calculated
        Calendar c = Calendar.getInstance();
        assert date != null;
        c.setTime(date);
        c.add(Calendar.DATE, extraDays);

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        System.out.println(sdf.format(c.getTime()));
        return sdf.format(c.getTime());
    }

}
