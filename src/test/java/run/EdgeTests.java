package run;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import utilities.Utility;

import java.io.*;
import java.util.List;

public class EdgeTests {

    public static void main(String[] args) throws InterruptedException {

        Utility.driver = new
                EdgeDriver();
        Utility.driver.get("https://microsoftedge.microsoft.com/addons/detail/adobe-acrobat-pdf-edit-/elhekieabhbkpmcefcoobjddigjcaadp?refid=bingshortanswersdownload");
        Utility.driver.manage().window().maximize();
        Utility.getLocator("getOrRemoveButton-elhekieabhbkpmcefcoobjddigjcaadp","id").click();
        Utility.driver.switchTo().alert().accept();
        }
    }