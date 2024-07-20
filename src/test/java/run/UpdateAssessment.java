package run;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import utilities.JSONToMap;
import utilities.Utility;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UpdateAssessment {

    public static void main(String[] args) throws InterruptedException {

        System.out.println(System.getProperty("user.dir")+"/images/");

        Utility.driver = new FirefoxDriver();
        Utility.driver.get("https://swavlambancard.gov.in/admin/login");
        Utility.driver.manage().window().maximize();
        Utility.getLocator("email", "id").sendKeys("cmo.varanasi2014@gmail.com");
        Utility.getLocator("pwd", "id").sendKeys("Varanasi@800");
        ArrayList<HashMap<String, String>> data = new JSONToMap().getData();
        try {
            for (HashMap<String, String> map : data) {
                String date = map.get("Date"),
                        serialCode = map.get("Serial Code"),
                        enrolmentNumber = map.get("Enrolment Number").replace("/", ""),
                        disabilityType = map.get("Disability Type"),
                        assessmentStatus = map.get("Assessment Status"),
                        affectedPart = map.get("Affected Part"),
                        diagnosis = map.get("Dignosis"),
                        remark = map.get("Remark"),
                        district = map.get("District"),
                        assignMedicalBord = map.get("Assign Medical Bord."),
                        doctorName = map.get("Doctor Name"),
                        disabilityPercent = map.get("Disability Percent"),
                        disabilityCondition = map.get("Disability Condition").toLowerCase(),
                        permanentTemporary = map.get("Permanent / Temporary"),
                        reassessmentRequire = map.get("Reassessment Require").equals("null") ? "No" : map.get("Reassessment Require"),
                        reassessmentReviewYear = map.get("Reassessment Review Year"),
                        reassementDate = map.get("ReassementDate");
                String filePath = System.getProperty("user.dir") + File.separator + "images" + File.separator + serialCode + ".jpg";
                if (!Files.exists(Paths.get(filePath))) {
                    continue;
                }
                try {
                    try {
                        Utility.driver.get("https://swavlambancard.gov.in/admin/assessments");
                        Thread.sleep(5000);
                        WebElement search = Utility.getLocator("listPwdapplications_application_number", "id");
                        search.clear();
                        search.sendKeys(enrolmentNumber);
                        search.sendKeys(Keys.ENTER);
                        Thread.sleep(3000);
                        int waitcount = 0;
                        List<WebElement> UpdateAssessment = Utility.getLocatorList("//td[contains(text(),\""+enrolmentNumber+"\")]/..//button[@title=\"Edit\"]", "xpath");
                        while (UpdateAssessment.isEmpty() && waitcount < 100) {
                            UpdateAssessment = Utility.getLocatorList("//td[contains(text(),\""+enrolmentNumber+"\")]/..//button[@title=\"Edit\"]", "xpath");
                            Thread.sleep(200);
                            waitcount++;
                        }
                        UpdateAssessment.get(0).click();
                        waitcount = 0;
                        List<WebElement> addAssessment = Utility.getLocatorList("//button[text()='Add Assessment']", "xpath");
                        while (addAssessment.isEmpty() && waitcount < 100) {
                            addAssessment = Utility.getLocatorList("//button[text()='Add Assessment']", "xpath");
                            Thread.sleep(200);
                            waitcount++;
                        }
                        Thread.sleep(3000);
                        addAssessment.get(0).click();
                        Thread.sleep(5000);
                        Utility.getLocator("affected_part_1_chosen", "id").click();
                        Utility.getLocator("#affected_part_1_chosen input", "css").sendKeys("Other", Keys.ENTER);
                        /*Select select = new Select(Utility.getLocator("affected_part_1", "id"));
                        select.selectByVisibleText("Other");*/
                        Utility.getLocator("affected_part_other_1", "id").sendKeys(affectedPart);
                        Utility.getLocator("diagnosis_1", "id").sendKeys(diagnosis);
                        Utility.getLocator("assessment_document", "id").sendKeys(filePath);
                        Utility.getLocator("assessment_remark_1", "id").sendKeys(remark);
                        Select select = new Select(Utility.getLocator("assessment_status", "id"));
                        select.selectByVisibleText(assessmentStatus);
                        Utility.getLocator("//*[@id='frmedit']//button[@title='Save']", "xpath").click();
                        Thread.sleep(2000);
                    } catch (Exception e) {
                        System.out.println("Error with ==> "+enrolmentNumber);
                        e.printStackTrace();
                    }
                    try {
                        Utility.driver.get("https://swavlambancard.gov.in/admin/Assignmedicalboard");

                        WebElement search = Utility.getLocator("listPwdapplications_application_number", "id");
                        search.clear();
                        search.sendKeys(enrolmentNumber);
                        search.sendKeys(Keys.ENTER);
                        Thread.sleep(5000);
                        Utility.getLocator("//td[contains(text(),'" + enrolmentNumber + "')]/..//a[@title=\"Assign Medical Board\"]", "xpath").click();
                        Thread.sleep(2000);
                        Select select = new Select(Utility.getLocator("model_district_code", "id"));
                        select.selectByVisibleText(district);
                        Thread.sleep(2000);
                        select = new Select(Utility.getLocator("board_id", "id"));
                        select.selectByVisibleText(assignMedicalBord);
                        Utility.getLocator("//*[contains(text(),'" + doctorName + "')]/../input", "xpath").click();
                        Utility.getLocator("//input[@value='Assign']", "xpath").click();
                        Thread.sleep(3000);
                        Actions actions = new Actions(Utility.driver);
                        actions.sendKeys(Keys.TAB).build().perform();
                        actions.sendKeys(Keys.SPACE).build().perform();
                        Thread.sleep(2000);
                    }catch (Exception e) {
                            System.out.println("Error with ==> "+enrolmentNumber);
                            e.printStackTrace();
                        }
                        try {
                        Utility.driver.get("https://swavlambancard.gov.in/admin/Medicalboardrecommendation");

                        WebElement search = Utility.getLocator("listPwdapplications_application_number", "id");
                        search.clear();
                        search.sendKeys(enrolmentNumber);
                        search.sendKeys(Keys.ENTER);
                        Thread.sleep(3000);

                        Utility.getLocator("//td[contains(text(),'"+enrolmentNumber+"')]/..//button[@title=\"Medical Board Recommendation\"]", "xpath").click();
                        Utility.getLocator("add_disability_type_id_1", "id").sendKeys(disabilityPercent + Keys.TAB);
                        Thread.sleep(1000);
                        Select select = new Select(Utility.getLocator("//*[@name='disability_condition']", "xpath"));
                        select.selectByVisibleText(disabilityCondition);
                        select = new Select(Utility.getLocator("disability_type_pt1", "id"));
                        select.selectByVisibleText(permanentTemporary);
                        Thread.sleep(2000);
                        if (permanentTemporary.equalsIgnoreCase("Temporary")) {
                            select = new Select(Utility.getLocator("reassessment_require1", "id"));
                            select.selectByVisibleText(reassessmentRequire);
                            Thread.sleep(1000);
                            if (reassessmentRequire.equalsIgnoreCase("Yes")) {
                                WebElement element = Utility.getLocator("valid_till1", "id");
                                JavascriptExecutor js = (JavascriptExecutor) Utility.driver;
                                js.executeScript("arguments[0].removeAttribute('readonly')", element);
                                element.clear();
                                element.sendKeys(reassementDate);
                            }
                        }
                        Utility.getLocator("recommendation_document", "id").sendKeys(filePath);
                        Utility.getLocator("remark_1", "id").sendKeys(remark);
                        List<WebElement> elements = Utility.getLocatorList("affected_part1", "id");
                        if (!elements.isEmpty()) {
                            elements.get(0).sendKeys(affectedPart);
                        }
                        elements = Utility.getLocatorList("diagnosis1", "id");
                            if (!elements.isEmpty()) {
                                elements.get(0).sendKeys(diagnosis);
                            }
                        Utility.getLocator("//button[@title='Add Recommendation']", "xpath").click();
                        Thread.sleep(2000);
                    }catch (Exception e){
                        writeInNotePad("Error with ==> "+enrolmentNumber);
                        e.printStackTrace();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            System.out.println("End");
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
