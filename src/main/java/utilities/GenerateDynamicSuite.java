package utilities;

import org.testng.TestNG;
import org.testng.annotations.Test;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.util.ArrayList;
import java.util.List;


/**
 * 
 * @author abdul.samad
 *
 */
public class GenerateDynamicSuite extends ExcelSheetHandler
{
	@Test
	public static void GenrateTestNG()
	{
	
	XmlSuite suite = new XmlSuite();
    suite.setName("SCALE-IHS Regression");
    		

    XmlTest test = new XmlTest(suite);
    test.setName("Regression Test Flows");
    test.setVerbose(4);
    List<XmlClass> classes = new ArrayList<XmlClass>();
    List<XmlSuite> suites = new ArrayList<XmlSuite>();

    InitializeExcel(ConstantPaths.EXCEL_PATH, "");

    for(int i=1; i<sheet.getPhysicalNumberOfRows(); i++){

        if(sheet.getRow(i).getCell(1).getStringCellValue().equals("Y"))
        {
        	String testClassName = sheet.getRow(i).getCell(0).getStringCellValue();
        	String ScriptPath = ""+testClassName;
        	System.out.println("*************** " +ScriptPath);
        	classes.add(new XmlClass(ScriptPath));

        }

    }

    test.setXmlClasses(classes) ;
    suites.add(suite);
	TestNG tng = new TestNG();
    tng.setXmlSuites(suites);
    tng.run();
	}

}