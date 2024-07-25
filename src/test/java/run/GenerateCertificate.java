package run;

import com.google.gson.Gson;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.Select;
import utilities.JSONToMap;
import utilities.Utility;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class GenerateCertificate {

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
                String enrolmentNumber = map.get("Enrolment Number");
                Select select;
                try {
                    try {
                        Utility.driver.get("https://swavlambancard.gov.in/admin/downloadCertificates");
                        WebElement search = Utility.getLocator("listPwdapplications_application_number", "id");
                        search.clear();
                        search.sendKeys(enrolmentNumber);
                        search.sendKeys(Keys.ENTER);
                        Thread.sleep(2000);
                        Utility.getLocator("//td[contains(text(),\"" + enrolmentNumber + "\")]/..//button[@title=\"Certificate Preview & Generate\"]", "xpath").click();
                        Utility.getLocator("//button[@value='Generate Certificate']", "xpath").click();
                        renameDownloadedPDF(enrolmentNumber+"_cert");
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

    static String fileName = "GenerateError.json";
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

    public static void renameDownloadedPDF(String fileName) {
        File file = new File(System.getProperty("user.dir")+"/downloads/ReferralSheet.pdf");
        if (file.exists()) {
            file.renameTo(new File(System.getProperty("user.dir")+"/downloads/"+fileName+".pdf"));
        }
    }
}
