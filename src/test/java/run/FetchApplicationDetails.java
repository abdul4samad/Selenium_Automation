package run;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import utilities.JSONToMap;
import utilities.Utility;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class FetchApplicationDetails {

    public static String getNewURL(String enrolmentNumber){
        return "https://swavlambancard.gov.in/admin/pwdapplications?elist%5Bhospital_treating_state_code%5D=9&elist%5Bhospital_treating_district_code%5D=187&elist%5Bcurrent_subdistrict_code%5D=&elist%5Bcurrent_village_code%5D=&elist%5Bapplication_status%5D=&id=&task=&sort_field=Pwdapplications.id&sort_type=DESC&removeid=&limit=10&page=1&list%5BPwdapplications.application_number%5D="+enrolmentNumber+"&list%5BPwdapplications.full_name%5D=&list%5BPwdapplications.aadhaar_no%5D=&list%5BPwdapplications.gender%5D=&list%5BPwdapplicationstatus.status_name%5D=&list%5BPwdapplicationtype.type%5D=";
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
    public static void main(String[] args) {
        Utility.driver = new FirefoxDriver();
        Utility.driver.get("https://swavlambancard.gov.in/admin/login");
        Utility.driver.manage().window().maximize();
        Utility.getLocator("email", "id").sendKeys("cmo.varanasi2014@gmail.com");
        Utility.getLocator("pwd", "id").sendKeys("Varanasi@800");
        ArrayList<String> values = new ArrayList<>();

        try {
            List<String> lines = Files.readAllLines(Paths.get("imageNames.txt"));
            values.addAll(lines);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            for (String value : values) {
                try {
                    Utility.driver.get(getNewURL(value));
                    Thread.sleep(5000);
                    WebElement view = Utility.getLocator("//button[@title='View']", "xpath");
                    view.click();
                    Thread.sleep(3000);
                    closeCurrentAndSwitchToOpenedWindow();
                    ArrayList<String> details = new ArrayList<>();

                    List<String> xpaths = Arrays.asList(
                            "//*[@id=\"div-udid_number\"]/div/label/span[2]",
                            "//*[@id=\"div-aadhaar_no\"]/div/label/span",
                            "//*[@id=\"div-full_name\"]/div/label[contains(text(),'Applicant Full Name')]/span[2]",
                            "//*[@id=\"div-father_name\"]/div/label/span",
                            "//*[@id=\"div-mother_name\"]/div/label/span",
                            "//*[@id=\"div-gender\"]/div/label/span[2]",
                            "//*[@id=\"div-dob\"]/div/label/span[2]",
                            "//*[@id=\"div-mobile\"]/div/label/span[2]",
                            "//*[@id=\"div-category\"]/div/label/span[2]",
                            "//*[@id=\"div-current_address\"]/div/span",
                            "//*[@id=\"div-current_pincode\"]/div/span",
                            "//*[@id=\"div-disability_type_id\"]/div/label/span",
                            "//h3[contains(text(),'Medical Board Recommendation Details')]/../../..//td[3]",
                            "//*[@id=\"div-hospital_treating_disability\"]/div/span"
                    );

                    for (String xpath : xpaths) {
                        List<WebElement> elements = Utility.getLocatorList(xpath, "xpath");
                        if (!elements.isEmpty()) {
                            details.add(elements.get(0).getText()+">>");
                        } else {
                            details.add(">>");
                        }
                    }

                    try {
                        FileWriter fileWriter = new FileWriter("details.txt", true);
                        PrintWriter printWriter = new PrintWriter(fileWriter);
                        printWriter.print(value+">>");
                        for (String detail : details) {
                            printWriter.print(detail);
                        }
                        printWriter.println();
                        printWriter.close();
                    } catch (Exception e) {
                        System.out.println("Error writing to file");
                        e.printStackTrace();
                    }

                } catch (Exception e) {
                    System.out.println("Error with ==> "+value);
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
