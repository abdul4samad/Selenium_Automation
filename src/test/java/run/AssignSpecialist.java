package run;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import utilities.Utility;

import java.io.*;

public class AssignSpecialist {

    public static void main(String[] args) throws InterruptedException {

        Utility.driver = new FirefoxDriver();
        Utility.driver.get("https://www.swavlambancard.gov.in/admin/account/login");
        Utility.driver.manage().window().maximize();
        Utility.getLocator("email", "id").sendKeys("cmo.varanasi2014@gmail.com");
        Utility.getLocator("password", "id").sendKeys("Varanasi@800");
        Thread.sleep(15000);
        String data="";
        try {
            FileReader fr = new FileReader(new File("DataFile.txt"));
            BufferedReader br = new BufferedReader(fr);
            data = br.readLine();
            String array[] = data.split("-->");
            while (!data.isEmpty()) {
                try {
                    Utility.driver.get("https://www.swavlambancard.gov.in/admin/pwd/specialist");
                    Thread.sleep(5000);
                    WebElement search = Utility.getLocator("//div[@id='pwdSpecialistDataTable_filter']//input", "xpath");
                    search.clear();
                    search.sendKeys(array[1]);
                    Thread.sleep(2000);
                    search.sendKeys(Keys.BACK_SPACE);
                    Thread.sleep(2000);
                    search.sendKeys(Keys.COMMAND+ "z");
                    Thread.sleep(2000);
                    Select select = new Select(Utility.getLocator("speciality_id_1","id"));
                    select.selectByVisibleText(array[2]);
                    Thread.sleep(2000);
                    select = new Select(Utility.getLocator("doctor_id_1","id"));
                    select.selectByVisibleText(array[3]);
                    Utility.getLocator("//input[@value='Assign']","xpath").click();
                    Thread.sleep(2000);
                    data = br.readLine();
                    array = data.split("-->");
                }catch (Exception e){
                    e.printStackTrace();
                    data = br.readLine();
                    array = data.split("-->");
                }

            }

    }catch (Exception e){
            System.out.println("dfdsfsdfsdfsdf");
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
