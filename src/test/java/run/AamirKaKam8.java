package run;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import utilities.Utility;

import java.io.*;

public class AamirKaKam8 {

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
            FileReader fr = new FileReader(new File("Voters.txt"));
            BufferedReader br = new BufferedReader(fr);
            data = br.readLine();
            //String array[] = data.split("-->");
            while (!data.isEmpty()) {
                try {
                    WebElement searchBox = Utility.getLocator("#allapplicationapproveDataTable_filter input","css");
                    searchBox.clear();
                    searchBox.sendKeys(data+ Keys.BACK_SPACE);
                    Thread.sleep(2000);
                    searchBox.sendKeys(Keys.CONTROL+"z");
                    Thread.sleep(2000);
                    String status = Utility.getLocator("//*[text()='"+data+"']/../td[5]","xpath").getText();
                    writeInNotePad("LinkDetails.txt", data+","+status);
                    data = br.readLine();
                }catch (Exception e){
                    writeInNotePad("ForReferral.txt","issue with: "+data);
                    data = br.readLine();
                }

    }

    }catch (Exception e){
            e.printStackTrace();
        }
    }
        static String fileName = "LinkDetails.txt";
        public static void writeInNotePad (String fileName2, String text){
            fileName=fileName2;
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