package run;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import utilities.Utility;

import java.io.*;

public class GenerateCertificate {

    public static void main(String[] args) throws InterruptedException {

        Utility.driver = new FirefoxDriver();
        Utility.driver.get("https://www.swavlambancard.gov.in/admin/account/login");
        Utility.driver.manage().window().maximize();
        Utility.getLocator("email", "id").sendKeys("cmo.varanasi2014@gmail.com");
        Utility.getLocator("password", "id").sendKeys("Varanasi@832");
        Thread.sleep(15000);
        Utility.driver.get("https://www.swavlambancard.gov.in/admin/pwd/allapplicationlist");
        Thread.sleep(5000);
        String data="";
        try {
            FileReader fr = new FileReader(new File("DataFile.txt"));
            BufferedReader br = new BufferedReader(fr);
            data = br.readLine();
            String array[] = data.split("-->");
            while (!data.isEmpty()) {
                try {
                    Utility.driver.get("https://www.swavlambancard.gov.in/admin/pwd/generateudidlist");
                    WebElement search = Utility.getLocator("//*[@id=\"generateudidDataTable_filter\"]/label/input","xpath");
                    search.clear();
                    search.sendKeys(array[1]+ Keys.BACK_SPACE);
                    Thread.sleep(1000);
                    search.sendKeys(Keys.CONTROL+"z");
                    Thread.sleep(2000);
                    Utility.getLocator("//*[text()='"+array[1]+"']/../td[6]/a[@title='Generate UDID']","xpath").click();
                    Select select = new Select(Utility.getLocator(".verification_type","css"));
                    select.selectByVisibleText("Normal");
                    Thread.sleep(1000);
                    Utility.getLocator(".submit_btn","css").click();
                    Thread.sleep(1000);
                    if(!Utility.getLocator(".successMessage","css").getText().equalsIgnoreCase("Disability certificate and UDID card generated successfully.")){
                        writeInNotePad(array[1]+" Failed");
                    }

                    data = br.readLine();
                    array = data.split("-->");
                } catch (Exception e) {
                    e.printStackTrace();
                    writeInNotePad("issue with data:" + data);
                    data = br.readLine();
                    array = data.split("-->");
                }
            }

    }catch (Exception e){
            e.printStackTrace();
        }
    }
        static String fileName = "GenerateResult.txt";
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