//package run;
//
//import org.openqa.selenium.Keys;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.interactions.Actions;
//import org.testng.ITestResult;
//import org.testng.annotations.*;
//import utilities.ExcelSheetHandler;
//import utilities.Utility;
//
//public class Register {
//
//    WebDriver driver;
//    String vaccinePointURL="";
//    int hitCount=0;
//
//    @DataProvider(name = "dataProvider")
//    public Object[][] dataProvider(){
//        ExcelSheetHandler.InitializeExcel("C:\\Users\\Vicky Virus\\Downloads\\Corbevax 1St Dos.xlsx","1ST DOSE CORBEVAX  1-5000");
//        Object[][] obj= ExcelSheetHandler.readData("1ST DOSE CORBEVAX  1-5000");
//        return obj;
//    }
//
//    @BeforeClass
//    public void beforeClass(){
//        System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"/src/main/resources/drivers/chromedriver.exe");
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
//        driver.get("https://app.cowin.gov.in/login");
//        Utility.driver=driver;
//        Utility.getLocator("//*[text()='Mobile Number']/following-sibling::ion-input/input","xpath").sendKeys("8922932094");
//        Utility.getLocator("//*[text()='Password']/following-sibling::ion-input/input","xpath").sendKeys("CoVIN@1234");
//        Utility.getLocator("//*[contains(text(),'Login')]","xpath").click();
//        Utility.getLocator("//*[@name='close-circle']","xpath").click();
//        Utility.getLocator("//*[text()='Session Details']","xpath").getText();
//        Utility.getLocator("//*[contains(text(),'On Going')]/../../preceding-sibling::td[contains(text(),'CORBEVAX')]","xpath").click();
//        vaccinePointURL=Utility.driver.getCurrentUrl();
//    }
//
//    @Test(dataProvider = "dataProvider")
//    public void registerChild(String name, String mobile, String year, String id, String y) throws InterruptedException {
//        Utility.getLocator("//*[contains(text(),'OnSite')]","xpath").click();
//        Utility.getLocator("//*[@appinputchar='username']","xpath").click();
//        Utility.getLocator("//*[@appinputchar='username']","xpath").sendKeys(name);
//        Actions actions =  new Actions(driver);
//        actions.sendKeys(Keys.TAB);
//        actions.sendKeys(Keys.SPACE);
//        actions.build().perform();
//        Utility.getLocator("//*[@formcontrolname='birth_year']","xpath").click();
//        Utility.getLocator("//*[@formcontrolname='birth_year']","xpath").sendKeys(year);
//        Utility.getLocator("//*[@formcontrolname='mobile_number']","xpath").click();
//        Utility.getLocator("//*[@formcontrolname='mobile_number']","xpath").sendKeys(mobile);
//        actions.sendKeys(Keys.PAGE_DOWN);
//        actions.build().perform();
//        Utility.getLocator("//*[@formcontrolname='confirm_mobile_number']","xpath").click();
//        Utility.getLocator("//*[@formcontrolname='confirm_mobile_number']","xpath").sendKeys(mobile);
//        Utility.clickByJavaScript(Utility.getLocator("//*[contains(text(),'Photo ID')]/..","xpath"));
//        Utility.getLocator("//span[text()=' Student Photo Id Card ']","xpath").click();
//        Utility.clickByJavaScript(Utility.getLocator("//*[@formcontrolname='photo_id_number']","xpath"));
//        Utility.getLocator("//*[@formcontrolname='photo_id_number']","xpath").sendKeys(id);
//        Utility.clickByJavaScript(Utility.getLocator("//*[contains(text(),'Type of Re')]","xpath"));
//        Utility.getLocator("//span[contains(text(),'Citizen')][1]","xpath").click();
//        actions.sendKeys(Keys.PAGE_DOWN);
//        actions.build().perform();
//        Utility.clickByJavaScript(Utility.getLocator("//*[contains(text(),'Type of Cat')]","xpath"));
//        //Utility.getLocator("//span[contains(text(),'Citizen')][1]","xpath").click();
//        actions.sendKeys(Keys.ENTER).sendKeys(Keys.TAB);
//        actions.build().perform();
//        Utility.getLocator("//*[contains(text(),'Continue')]","xpath").click();
//        Thread.sleep(15000);
//        Utility.getLocator("//*[contains(text(),'Continue')]","xpath").click();
//    }
//
//    @AfterMethod
//    public void after(ITestResult iTestResult){
//        if (!iTestResult.isSuccess()){
//            driver.get(vaccinePointURL);
//        }
//    }
//}
