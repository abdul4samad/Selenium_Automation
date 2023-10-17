package pageFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import utilities.Utility;

public class DashBoard {

    public static void clickOnSearchOptions(){
        String sName = "//*[contains(text(),'Filter By')]";
        Utility.getLocator(sName,"xpath").click();
    }

    public static void selectRefIDtoSearch(){
        String xpath = "//*[@role='option' and @value='reference']";
        Utility.getLocator(xpath, "xpath").click();
    }

    public static void enterRefIDInInputField(String refID){
        String xpath = "//*[@formcontrolname='searchKey']";
        Utility.getLocator(xpath,"xpath").clear();
        Utility.getLocator(xpath,"xpath").sendKeys(refID);
    }

    public static void clickOnSearch(){
        String xpath = "//*[text()='Search']";
        Utility.getLocator(xpath,"xpath").click();
    }

    public static void addBeneficiary(){
        String xpath = "//ion-icon[@name='person-add-outline']/..";
        try{
            Utility.getLocator(xpath,"xpath").click();
        }catch (Exception e){
            System.out.println("already added");
        }

    }

    public static void selectForVaccination(String name){
        String xpath = "//*[text()='Citizen ']/..";
        Utility.getLocator(xpath,"xpath").click();
    }

    public static void selectDemographicAuth(){
        String xpath = "//*[contains(text(),'Demogra')]";
        Utility.getLocator(xpath,"xpath").click();
    }

    public static void enterAdhar(String adhar){
        String xpath="//*[contains(text(),'Aadhaar Number')]/../ion-input";
        Utility.getLocator(xpath,"xpath").click();
        Actions actions = new Actions(Utility.driver);
        actions.sendKeys(adhar).build().perform();
    }

    public static void clickOnYearOfBirth(){
        String xpath = "//*[contains(text(),'Year of Birth')]";
        //Utility.getLocator(xpath,"xpath").click();
        Actions actions = new Actions(Utility.driver);
        actions.click(Utility.getLocator(xpath,"xpath")).build().perform();
    }

    public static void selectBirthYear(String year){
        String xpath = "//*[contains(text(),'"+year+"')]";
        Utility.getLocator(xpath,"xpath").click();
    }

    public static void clickOnContinue() {
        String xpath="//*[text()=' Continue ']";
        Utility.scrollTillView(Utility.driver.findElement(By.xpath(xpath)));
        Utility.driver.findElement(By.xpath(xpath)).click();
    }

    public static void clickOnApprove() {
        String xpath="//*[contains(text(),'Approve')]";
        Utility.getLocator(xpath,"xpath").click();
    }

    public static void clickOnVerified(){
        String xpath = "//*[contains(text(),'Verified')]";
        Utility.getLocator(xpath,"xpath").click();
    }

    public static void clickOnScheduled(){
        String xpath = "//*[contains(text(),'Scheduled')]";
        Utility.getLocator(xpath,"xpath").click();
    }

    public static void otherVerification(){
        String xpath = "//*[contains(text(),'Other Type')]";
        Utility.getLocator(xpath,"xpath").click();
    }

    public static void clickOnVerify() {
        String xpath="//ion-button[contains(text(),'Verify')]";
        Utility.getLocator(xpath,"xpath").click();
    }
}
