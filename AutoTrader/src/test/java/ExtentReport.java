import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

public class ExtentReport {

    private static ExtentReports report;
    private WebDriver webDriver;


    @BeforeClass
    public static void init() {
        report = new ExtentReports();
        String fileName = "MyReport" + ".html";
        String filePath = System.getProperty("user.dir")
                + File.separatorChar + fileName;
        report.attachReporter(new ExtentHtmlReporter(filePath));
    }

    @Before
    public void setUp() {
        webDriver = new ChromeDriver();
    }

    @After
    public void tearDown() {
        webDriver.quit();
    }

    @AfterClass
    public static void cleanUp() {
        report.flush();
    }

    @Test
    public void MyFirstTestReportTest() {
        ExtentTest test = report.createTest("MyFirstTest");
        test.log(Status.INFO, "My First Test is Starting ");
        for (int i = 0; i < 100; i++) {
            test.log(Status.DEBUG, "On iteration: " + i);
        }
    }

}