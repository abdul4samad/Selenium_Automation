package run;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import utilities.JSONToMap;
import utilities.Utility;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;

public class PullAllIds {

    public static String convertToBase64(String input) {
        return Base64.getEncoder().encodeToString(input.getBytes());
    }

    public static String convertToFourDigitString(int number) {
        return String.format("%04d", number);
    }

    public static void main(String[] args) throws InterruptedException {

        System.out.println(System.getProperty("user.dir")+"/images/");

        Utility.driver = new FirefoxDriver();
        Utility.driver.get("https://swavlambancard.gov.in/admin/login");
        Utility.driver.manage().window().maximize();
        Utility.getLocator("email", "id").sendKeys("cmo.varanasi2014@gmail.com");
        Utility.getLocator("pwd", "id").sendKeys("Varanasi@800");
        ArrayList<String> data = new ArrayList<>();
        for (int i = 600; i < 8000; i++) {
            System.out.println(i);
            try {
                Utility.driver.get("https://swavlambancard.gov.in/admin/Users/view?id="+convertToBase64(convertToFourDigitString(i)));
                data.add(Utility.getLocator("email", "id").getAttribute("value"));
                data.add(Utility.getLocator("//*[@id=\"div-login_status\"]/div/div", "xpath").getAttribute("class"));
                data.add(Utility.getLocator("//*[@id=\"div-status\"]/div/div", "xpath").getAttribute("class"));
            } catch (Exception e) {
                System.out.println("End");
            }
            writeInNotePad(data.toString());
            data.clear();
        }
    }

    static String fileName = "Output.txt";
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
