package run;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.Select;
import utilities.JSONToMap;
import utilities.Utility;

import java.util.ArrayList;
import java.util.HashMap;

public class RejectApplication {
    public static void main(String[] args) throws InterruptedException {
        FirefoxOptions options = new FirefoxOptions();
        options.addPreference("browser.download.folderList", 2);
        options.addPreference("browser.download.dir", System.getProperty("user.dir")+"/downloads");
        Utility.driver = new FirefoxDriver(options);
        Utility.driver.get("https://swavlambancard.gov.in/admin/login");
        Utility.driver.manage().window().maximize();
        Utility.getLocator("email", "id").sendKeys("cmo.varanasi2014@gmail.com");
        Utility.getLocator("pwd", "id").sendKeys("Varanasi@800");
        ArrayList<HashMap<String, String>> data = new JSONToMap().getData("Reject");
        ((JavascriptExecutor) Utility.driver).executeScript("document.body.style.zoom='50%'");
        try {
            for (HashMap<String, String> map : data) {
                String enrolmentNumber = map.get("Enrolment Number"),
                        otherComment = map.get("Other");
                Select select;
                try {
                    try {
                        Utility.driver.get("https://swavlambancard.gov.in/admin/pwdapplications");
                        WebElement search = Utility.getLocator("listPwdapplications_application_number", "id");
                        search.clear();
                        search.sendKeys(enrolmentNumber);
                        search.sendKeys(Keys.ENTER);
                        Thread.sleep(2000);
                        Utility.clickByJavaScript(Utility.getLocator("//td[contains(text(),\"" + enrolmentNumber + "\")]/..//button[@title=\"Verify\"]", "xpath"));
                        Thread.sleep(1000);
                        WebElement loader = Utility.getLocator("global-loader", "id");
                        ((JavascriptExecutor) Utility.driver).executeScript("arguments[0].remove();", loader);
                        select = new Select(Utility.getLocator("id_application_status", "id"));
                        select.selectByVisibleText("Rejected");
                        Utility.clickByJavaScript(Utility.getLocator("//input[@value='Other']", "xpath"));
                        Utility.getLocator("reject_remark_other", "id").sendKeys(otherComment);
                        Utility.clickByJavaScript(Utility.getLocator("save_form_verify", "id"));
                        Thread.sleep(2000);
                    } catch (Exception e) {
                        if(!map.containsKey("Step")){
                            map.put("Step", "1");
                        }
                        e.printStackTrace();
                        System.out.println("Error with ==> " + enrolmentNumber);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            System.out.println("End");
        }
    }
}