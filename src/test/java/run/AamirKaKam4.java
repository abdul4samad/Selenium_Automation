package run;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import utilities.Utility;

import java.io.*;
import java.util.List;

public class AamirKaKam4 {

    public static void main(String[] args) throws InterruptedException {

        Utility.driver = new FirefoxDriver();
        Utility.driver.get("https://www.swavlambancard.gov.in/admin/account/login");
        Utility.driver.manage().window().maximize();
        Utility.getLocator("email", "id").sendKeys("cmo.varanasi2014@gmail.com");
        Utility.getLocator("password", "id").sendKeys("Varanasi@2014");
        Thread.sleep(15000);
        String data="";
        try {

            Utility.driver.get("https://www.swavlambancard.gov.in/admin/pwd/sendSms");
            FileReader fr = new FileReader(new File("Links.txt"));
            BufferedReader br = new BufferedReader(fr);
            data = br.readLine();
            String eN = data.split("-->")[0];
            String msg = data.split("-->")[1];
            while (!eN.isEmpty()) {
                //Utility.driver.get("https://www.swavlambancard.gov.in/admin/pwd/view/1zt6zJ1zJ4zP/i1Ne1Gs1an1rc1Xv10n1Cv1Jazw");
                //Thread.sleep(500);
                try {
                    WebElement enField = Utility.getLocator("application_number", "id");
                    enField.clear();
                    enField.sendKeys(eN);
                    enField.sendKeys(Keys.ENTER);
                    List<WebElement> ids = Utility.getLocatorList("ids", "name");
                    int retryCount = 0;
                    while (ids.size() < 1 && retryCount < 6) {
                        ids = Utility.getLocatorList("ids", "name");
                        Thread.sleep(500);
                        retryCount++;
                    }
                    ids.get(0).click();
                /*if(duplicate.size()>0){
                    duplicate.get(0).click();
                    Thread.sleep(500);
                }*/
                    Thread.sleep(500);
                    Select select = new Select(Utility.getLocator("sms_id", "id"));
                    select.selectByVisibleText("Appointment for disability assessment is fixed on (Date) at (Time) in (Hospital Name) for (Applicant enrolment number)");
                    WebElement msgFiled = Utility.getLocator("text_message", "id");
                    msgFiled.clear();
                    msgFiled.sendKeys(msg);
                    Utility.getLocator("send_sms", "id").click();
                /*WebElement form = Utility.getLocator("verify_application_form","id");
                JavascriptExecutor js = (JavascriptExecutor) Utility.driver;
                js.executeScript("arguments[0].setAttribute('action', '/admin/pwd/view/"+eN+"')", form);
                WebElement input = Utility.driver.findElement(By.id("appId"));
                js.executeScript("arguments[0].setAttribute('value', '"+eN+"')", input);
                Utility.getLocator("//input[@value=\"Verify\"]","xpath").click();*/
                /*Thread.sleep(200);
                duplicate = Utility.getLocatorList("//div[@id='confirmation_view']//input","xpath");
                for (WebElement elem: duplicate
                     ) {
                    elem.click();
                }
                Utility.getLocator("confirm_button","id").click();*/
                    //WebElement searchInput = Utility.getLocator("//input[@aria-controls=\"pwdDigitizationDataTable\"]", "xpath");
                /*searchInput.clear();
                searchInput.sendKeys(eN);
                searchInput.sendKeys(Keys.ENTER);
                List<WebElement> linkList = Utility.getLocatorList("//td[text()='"+eN+"']/../td[7]/a[@title='Verify']","xpath");
                while (linkList.size()<1){
                    linkList = Utility.getLocatorList("//td[text()='"+eN+"']/../td[7]/a[@title='Verify']","xpath");
                }
                writeInNotePad(linkList.get(0).getAttribute("href"));*/
                    data = br.readLine();
                    eN = data.split("-->")[0];
                    msg = data.split("-->")[1];
                }catch (Exception e){
                    e.printStackTrace();
                    writeInNotePad("issue with data:"+data);
                    data = br.readLine();
                    eN = data.split("-->")[0];
                    msg = data.split("-->")[1];
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            writeInNotePad("issue with data:"+data);
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