package run;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import utilities.Utility;

import java.io.*;
import java.util.List;
import java.util.Set;

public class AamirKaKam7 {

    public static void main(String[] args) throws InterruptedException {

        Utility.driver = new FirefoxDriver();
        Utility.driver.get("https://www.swavlambancard.gov.in/admin/account/login");
        Utility.driver.manage().window().maximize();
        Utility.getLocator("email", "id").sendKeys("cmo.varanasi2014@gmail.com");
        Utility.getLocator("password", "id").sendKeys("Varanasi@832");
        Thread.sleep(15000);
        String data="";
        try {

            FileReader fr = new FileReader(new File("LinkDetails.txt"));
            BufferedReader br = new BufferedReader(fr);
            data = br.readLine();
            String array[] = data.split("-->");
            while (!data.isEmpty()) {
                Utility.driver.get("https://www.swavlambancard.gov.in/admin/pwd/generateudidlist");

                Thread.sleep(1000);
                data = br.readLine();
                array = data.split("-->");
    }

    }catch (Exception e){
            System.out.println("dfdsfsdfsdfsdf");
        }
    }
        static String fileName = "LinkDetails.txt";
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