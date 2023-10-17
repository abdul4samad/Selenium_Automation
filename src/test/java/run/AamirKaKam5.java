package run;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import utilities.Utility;

import java.io.*;
import java.util.List;
import java.util.Set;

public class AamirKaKam5 {

    public static void main(String[] args) throws InterruptedException {

        Utility.driver = new FirefoxDriver();
        Utility.driver.get("https://www.swavlambancard.gov.in/admin/account/login");
        Utility.driver.manage().window().maximize();
        Utility.getLocator("email", "id").sendKeys("cmo.varanasi2014@gmail.com");
        Utility.getLocator("password", "id").sendKeys("Varanasi@832");
        Thread.sleep(15000);
        String data = "";
        try {

            FileReader fr = new FileReader(new File("Voters.txt"));
            BufferedReader br = new BufferedReader(fr);
            data = br.readLine();
            String array[] = data.split("-->");
            while (!data.isEmpty()) {
                try {
                    try {
                        Utility.driver.get("https://www.swavlambancard.gov.in/admin/pwd/assessmentslist");
                        Thread.sleep(5000);
                        WebElement search = Utility.getLocator("//div[@id='pwdAssessmentsDataTable_filter']//input", "xpath");
                        search.clear();
                        search.sendKeys(array[1]);
                        Thread.sleep(2000);
                        search.sendKeys(Keys.BACK_SPACE);
                        Thread.sleep(2000);
                        int waitcount = 0;
                        List<WebElement> UpdateAssessment = Utility.getLocatorList("//*[text()='" + array[1] + "']/..//a[@title='Assessment']", "xpath");
                        while (UpdateAssessment.size() == 0 && waitcount < 15) {
                            UpdateAssessment = Utility.getLocatorList("//*[text()='" + array[1] + "']/..//a[@title='Assessment']", "xpath");
                            Thread.sleep(200);
                            waitcount++;
                        }
                        UpdateAssessment.get(0).click();
                        waitcount = 0;
                        List<WebElement> addAssessment = Utility.getLocatorList("buttonAdd", "id");
                        while (addAssessment.size() == 0 && waitcount < 15) {
                            addAssessment = Utility.getLocatorList("buttonAdd", "id");
                            Thread.sleep(200);
                            waitcount++;
                        }
                        addAssessment.get(0).click();
                        Thread.sleep(2000);
                        Select select = new Select(Utility.getLocator("disability_type_0", "id"));
                        select.selectByVisibleText(array[2]);
                        Utility.getLocator("affected_part_0", "id").sendKeys(array[3]);
                        Utility.getLocator("diagnosis_0", "id").sendKeys(array[4]);
                        Utility.getLocator("assessment_document_0", "id").sendKeys("C:\\Users\\Vicky Virus\\Downloads\\AamirFiles\\" + array[0] + ".jpeg");
                        Utility.getLocator("remark_0", "id").sendKeys(array[5]);
                        Utility.getLocator("add_assessment_0", "id").click();

                        Utility.driver.get("https://www.swavlambancard.gov.in/admin/pwd/medicalboardlist");
                        Utility.getLocator("//*[text()='" + array[1] + "']/..//a[@title='Assign Medical Board']", "xpath").click();
                        select = new Select(Utility.getLocator("board_id", "id"));
                        select.selectByVisibleText(array[6]);
                        try {
                            Utility.getLocator("//*[text()='" + array[7] + "']/input", "xpath").click();
                        }catch (Exception e){
                            Utility.getLocator("//*[text()='" + array[7] + "']/../input", "xpath").click();
                        }

                        Utility.getLocator("//input[@value='Assign']", "xpath").click();
                        Thread.sleep(2000);
                        Utility.driver.get("https://www.swavlambancard.gov.in/admin/pwd/medicalboardrecommandation");
                        Utility.getLocator("//td[text()='" + array[1] + "']/../td[6]/a[@title='Update Recommendation']", "xpath").click();
                        Utility.getLocator("disability_in_percent", "id").sendKeys(array[8] + Keys.TAB);
                        select = new Select(Utility.getLocator("disability_condition_master_id", "id"));
                        select.selectByVisibleText(array[9]);
                        select = new Select(Utility.getLocator("disability_condition_category", "id"));
                        select.selectByVisibleText(array[10]);
                        Thread.sleep(2000);
                        if (array[10].equalsIgnoreCase("Temporary")) {
                            select = new Select(Utility.getLocator("//*[contains(@id,'reassesment_require')]", "xpath"));
                            select.selectByVisibleText(array[11]);
                            Thread.sleep(1000);
                            if (array[11].equalsIgnoreCase("Yes")) {
                                select = new Select(Utility.getLocator("//*[contains(@id,'reassesment_review_year')][2]", "xpath"));
                                select.selectByVisibleText(array[12]);
                                select = new Select(Utility.getLocator("//*[contains(@id,'reassesment_review_month')][2]", "xpath"));
                                select.selectByVisibleText(array[13]);
                            }
                        }
                        Utility.getLocator("recommendation_document", "id").sendKeys("C:\\Users\\Vicky Virus\\Downloads\\AamirFiles\\" + array[0] + ".jpeg");
                        Utility.getLocator("remark", "id").sendKeys(array[14]);
                        Utility.getLocator("recommandation_save", "id").click();
                        Thread.sleep(2000);
                        data = br.readLine();
                        array = data.split("-->");
                    } catch (Exception e) {
                        writeInNotePad("Error with ==> "+array[1]);
                        data = br.readLine();
                        array = data.split("-->");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            System.out.println("End");
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