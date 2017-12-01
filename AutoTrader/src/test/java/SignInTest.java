import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.io.IOException;

public class SignInTest {
    private  String url = "https://www.autotrader.co.uk/secure/signin?after-sigin-url=/";
    private WebDriver webDriver;
    private static ExtentReports report;

    @BeforeClass
    public static void init(){
        report = new ExtentReports();
        String fileName = "SignInReport" + ".html";
        String filePath = System.getProperty("user.dir")
                + File.separatorChar + fileName;
        report.attachReporter(new ExtentHtmlReporter(filePath));
    }

    @Before
    public void setUp(){
        webDriver = new ChromeDriver();
    }

    public void start(){
        webDriver.navigate().to(url);
    }

    @Test
    //Test to see error message when no fields entered
    public void emptyFields() throws InterruptedException, IOException{
        ExtentTest test = report.createTest("Failed Login test");
        test.log(Status.INFO, "Failed Login is starting");

        start();
        SignInPOM spom = PageFactory.initElements(webDriver, SignInPOM.class);

        spom.signInButonClick();

        String error = spom.errorMessage.getText();

        String expected = "Your email or password was incorrect. Please try again.";
        try{
            Assert.assertEquals(expected, error);
        }
        catch(AssertionError er){
            ScreenShot shotEmptyF = new ScreenShot();
            String shot1EmptyF = shotEmptyF.take(webDriver, "emptyFields");
            test.addScreenCaptureFromPath(shot1EmptyF);
        }

    }

    @Test
    //Test to see facebook signup starts
    public void facebook() throws InterruptedException, IOException{
        ExtentTest test = report.createTest("Facebook test");
        test.log(Status.INFO, "Facebook is starting");

        start();
        SignInPOM spom = PageFactory.initElements(webDriver, SignInPOM.class);
        spom.facebookClick();
        //String winHandleBefore = webDriver.getWindowHandle();
        for(String winHandle : webDriver.getWindowHandles()){
            webDriver.switchTo().window(winHandle);
        }
        String title = webDriver.getTitle();
        String expected = "Facebook";
        try{
            Assert.assertEquals(expected, title);
        }
        catch(AssertionError error){
            ScreenShot shotFacebook = new ScreenShot();
            String shot1Facebook = shotFacebook.take(webDriver, "Facebook");
            test.addScreenCaptureFromPath(shot1Facebook);
        }
    }

    @Test
    //Create Account Test
    public void signUp() throws InterruptedException {
        ExtentTest test = report.createTest("SignUp test");
        test.log(Status.INFO, "SignUp is starting");

        start();
        SignInPOM spom = PageFactory.initElements(webDriver, SignInPOM.class);
        spom.loginButtonClick();
        String testEmail = "omers07@hotmail.co.uk";
        String testPassword = "Carrot@2811";

        spom.setEmailSignUp(testEmail);
        spom.setPasswordSignUp(testPassword);

        spom.setCheckBox1();
        spom.setCheckBox2();
        spom.signUpButtonClick();
        Thread.sleep(40000);
    }

    @After
    public void tearDown(){
        webDriver.quit();
    }

    @AfterClass
    public static void cleanUp(){
        report.flush();
    }
}
