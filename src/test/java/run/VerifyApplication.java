package run;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.Select;
import utilities.JSONToMap;
import utilities.Utility;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import com.google.gson.Gson;

public class VerifyApplication {

    public static void main(String[] args) throws InterruptedException {
        FirefoxOptions options = new FirefoxOptions();
        options.addPreference("browser.download.folderList", 2);
        options.addPreference("browser.download.dir", System.getProperty("user.dir")+"/downloads");
        Utility.driver = new FirefoxDriver(options);
        Utility.driver.get("https://swavlambancard.gov.in/admin/login");
        Utility.driver.manage().window().maximize();
        Utility.getLocator("email", "id").sendKeys("cmo.varanasi2014@gmail.com");
        Utility.getLocator("pwd", "id").sendKeys("Varanasi@800");
        ArrayList<HashMap<String, String>> data = new JSONToMap().getData("Verify");
        try {
            for (HashMap<String, String> map : data) {
                String enrolmentNumber = map.get("Enrolment Number"),
                        speciality = map.get("Speciality"),
                        specialist = map.get("Specialist");
                Select select;
                try {
                    try {
                        Utility.driver.get("https://swavlambancard.gov.in/admin/pwdapplications");
                        WebElement search = Utility.getLocator("listPwdapplications_application_number", "id");
                        search.clear();
                        search.sendKeys(enrolmentNumber);
                        search.sendKeys(Keys.ENTER);
                        Thread.sleep(2000);
                        Utility.getLocator("//td[contains(text(),\"" + enrolmentNumber + "\")]/..//button[@title=\"Verify\"]", "xpath").click();
                        select = new Select(Utility.getLocator("id_application_status", "id"));
                        select.selectByVisibleText("Verify");
                        Utility.getLocator("save_form_verify", "id").click();
                        List<WebElement> checkboxes = Utility.getLocatorList("#confirmation_view input", "css");
                        for (WebElement checkbox : checkboxes) {
                            checkbox.click();
                        }
                        Utility.getLocator("confirm_button", "id").click();

                    } catch (Exception e) {
                        if(!map.containsKey("Step")){
                            map.put("Step", "1");
                        }
                        e.printStackTrace();
                        System.out.println("Error with ==> " + enrolmentNumber);
                    }
                    try {
                        Utility.driver.get("https://swavlambancard.gov.in/admin/assignspecialist");
                        WebElement search = Utility.getLocator("listPwdapplications_application_number", "id");
                        search.clear();
                        search.sendKeys(enrolmentNumber);
                        search.sendKeys(Keys.ENTER);
                        Thread.sleep(2000);
                        Utility.getLocator("//td[contains(text(),\"" + enrolmentNumber + "\")]/..//button[@title=\"Edit\"]", "xpath").click();
                        select = new Select(Utility.getLocator("//select[@data-id='2']", "xpath"));
                        select.selectByVisibleText(speciality);

                        select = new Select(Utility.getLocator("doctor_id_2", "id"));
                        select.selectByVisibleText(specialist);
                        Utility.getLocator("//*[@id='frmedit']//button[@title='Save']", "xpath").click();
                        Thread.sleep(2000);
                    } catch (Exception e) {
                        if(!map.containsKey("Step")){
                            map.put("Step", "2");
                        }
                        System.out.println("Error with ==> "+enrolmentNumber);
                        e.printStackTrace();
                    }
                    try {
                        Utility.driver.get("https://swavlambancard.gov.in/admin/pwdapplications?elist%5Bhospital_treating_state_code%5D=&elist%5Bcurrent_subdistrict_code%5D=&elist%5Bcurrent_village_code%5D=&elist%5Bapplication_status%5D=&id=&task=&sort_field=Pwdapplications.id&sort_type=DESC&removeid=&limit=10&page=1&list%5BPwdapplications.application_number%5D="+enrolmentNumber+"&list%5BPwdapplications.full_name%5D=&list%5BPwdapplications.aadhaar_no%5D=&list%5BPwdapplications.gender%5D=&list%5BPwdapplicationstatus.status_name%5D=&list%5BPwdapplicationtype.type%5D=");
                        Thread.sleep(2000);
                        Utility.getLocator("//td[contains(text(),\"" + enrolmentNumber + "\")]/..//button[@title=\"View\"]", "xpath").click();
                        closeCurrentAndSwitchToOpenedWindow();
                        Utility.getLocator("//a[text()='Download Referral Sheet']", "xpath").click();
                        Thread.sleep(2000);
                        closeCurrentAndSwitchToOpenedWindow();
                        renameDownloadedPDF(enrolmentNumber);
                    } catch (Exception e) {
                        if(!map.containsKey("Step")){
                            map.put("Step", "3");
                        }
                        e.printStackTrace();
                        System.out.println("Error with ==> " + enrolmentNumber);
                        writeInNotePad(map);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            System.out.println("End");
        }
    }

    static String fileName = "VerifyError.json";
    public static void writeInNotePad (HashMap<String, String> map){
        Gson gson = new Gson();
        try {
            FileWriter fw = new FileWriter(fileName, true); // true to append
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(gson.toJson(map));
            bw.write(",");
            bw.newLine();
            bw.close();
            System.out.println("Text has been written to " + fileName);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static void closeCurrentAndSwitchToOpenedWindow() {
        String currentWindowHandle = Utility.driver.getWindowHandle();
        Utility.driver.close();
        Set<String> windowHandles = Utility.driver.getWindowHandles();
        for (String windowHandle : windowHandles) {
            if (!currentWindowHandle.equals(windowHandle)) {
                Utility.driver.switchTo().window(windowHandle);
                break;
            }
        }
    }

    public static void renameDownloadedPDF(String fileName) {
        File file = new File(System.getProperty("user.dir")+"/downloads/ReferralSheet.pdf");
        if (file.exists()) {
            file.renameTo(new File(System.getProperty("user.dir")+"/downloads/"+fileName+".pdf"));
        }
    }
}
