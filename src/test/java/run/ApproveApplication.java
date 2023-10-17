package run;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import utilities.Utility;

import java.io.*;

public class ApproveApplication {

    public static void main(String[] args) throws InterruptedException, IOException {

        // Create Firefox profile
        FirefoxProfile profile = new FirefoxProfile();

        // Set preference to prevent print dialog
        profile.setPreference("print.always_print_silent", true);

        // Set preference to open PDFs with a specific application
        profile.setPreference("browser.helperApps.alwaysAsk.force", false);
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf");
        profile.setPreference("browser.helperApps.neverAsk.openFile", "application/pdf");
        profile.setPreference("browser.download.manager.showWhenStarting", false);
        profile.setPreference("pdfjs.disabled", true);
        profile.setPreference("pdfjs.firstRun", false);

        // Set Firefox options
        FirefoxOptions options = new FirefoxOptions();
        options.setProfile(profile);

        Utility.driver = new FirefoxDriver(options);
        Utility.driver.get("https://www.swavlambancard.gov.in/admin/account/login");
        Utility.driver.manage().window().maximize();
        Utility.getLocator("email", "id").sendKeys("cmo.varanasi2014@gmail.com");
        Utility.getLocator("password", "id").sendKeys("Varanasi@832");
        Thread.sleep(15000);
        String data = "";
        FileReader fr = new FileReader(new File("AppID.txt"));
        BufferedReader br = new BufferedReader(fr);
        data = br.readLine();
        String[] array = data.split("-->");
        //Thread.sleep(3000);
        while (!data.isEmpty()) {
            try {
                Utility.driver.get("https://www.swavlambancard.gov.in/admin/pwd/viewapplicationlist/"+array[1]);
                Thread.sleep(2000);
                data = br.readLine();
                array = data.split("-->");
            } catch (Exception e) {
                e.printStackTrace();
                writeInNotePad("issue with data:" + data);
                data = br.readLine();
                array = data.split("-->");
            }
        }
    }
    static String fileName = "Status.txt";
    public static void writeInNotePad (String text){

        try {
            FileWriter fw = new FileWriter(fileName, true); // true to append
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(text);
            bw.newLine();
            bw.close();
            System.out.println("Text has been written to " + fileName);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}