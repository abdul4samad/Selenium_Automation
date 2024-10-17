package run;

import com.google.gson.Gson;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import utilities.JSONToMap;
import utilities.Utility;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class ResetApplication {

    public static void main(String[] args) throws InterruptedException {
        FirefoxOptions options = new FirefoxOptions();
        options.addPreference("browser.download.folderList", 2);
        options.addPreference("browser.download.dir", System.getProperty("user.dir") + "/downloads");
        Utility.driver = new FirefoxDriver(options);
        Utility.driver.get("https://swavlambancard.gov.in/admin/login");
        Utility.driver.manage().window().maximize();
        Utility.getLocator("email", "id").sendKeys("cmo.varanasi2014@gmail.com");
        Utility.getLocator("pwd", "id").sendKeys("Varanasi@800");
        ArrayList<HashMap<String, String>> data = new JSONToMap().getData("Reset");
        ((JavascriptExecutor) Utility.driver).executeScript("document.body.style.zoom='50%'");
        try {
            for (HashMap<String, String> map : data) {
                String enrolmentNumber = map.get("Enrolment Number");
                try {
                    try {
                        String resetSearchURL = "https://swavlambancard.gov.in/admin/Restapplications?elist%5Bhospital_treating_state_code%5D=&elist%5Bcurrent_subdistrict_code%5D=&elist%5Bcurrent_village_code%5D=&id=&task=&sort_field=Pwdapplications.id&sort_type=DESC&removeid=&limit=10&page=1&list%5BPwdapplications.application_number%5D="+enrolmentNumber+"&list%5BPwdapplications.full_name%5D=&list%5BPwdapplications.aadhaar_no%5D=&list%5BPwdapplications.gender%5D=";
                        Utility.driver.get(resetSearchURL);
                        WebElement resetOnSearch = Utility.getLocator("//button[@title='Reset']", "xpath");
                        resetOnSearch.click();
                        Thread.sleep(5000);
                        Utility.clickByJavaScript(Utility.getLocator("save_form_reset", "id"));
                        Thread.sleep(5000);
                    } catch (Exception e) {
                        if (!map.containsKey("Step")) {
                            map.put("Step", "1");
                        }
                        e.printStackTrace();
                        System.out.println("Error with ==> " + enrolmentNumber);
                        writeInNotePad(map);
                    }
                } catch (Exception e) {
                    System.out.println("End");
                }
            }
        }catch (Exception e) {
            System.out.println("End");
        }
    }

    static String fileName = "ResetError.json";
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
}
