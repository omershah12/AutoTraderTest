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

public class HomePage {
    private  String url = "https://www.autotrader.co.uk/";
    private WebDriver webDriver;
    private static ExtentReports report;

    @BeforeClass
    public static void init(){
        report = new ExtentReports();
        String fileName = "HomePageReport" + ".html";
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
    //Should not search when postcode is empty
    public void searchWhenEmpty() throws InterruptedException, IOException {
        ExtentTest test = report.createTest("Search when empty test");
        test.log(Status.INFO, "Test is starting");

        start();

        HomePagePOM hpom = PageFactory.initElements(webDriver, HomePagePOM.class);

        hpom.searchClick();
//        Thread.sleep(2000);
        String expected = "Enter a Postcode";

        String required = hpom.postcode.getAttribute("placeholder");

        try{
            Assert.assertEquals(expected, required);
        }
       catch(AssertionError error){
           ScreenShot shotEmpty = new ScreenShot();
           String shot1Empty = shotEmpty.take(webDriver, "SearchWhenEmpty");
           test.addScreenCaptureFromPath(shot1Empty);
       }
    }

    @Test
    //Go to Mobile site
    //Not working
    public void mobile() throws InterruptedException, IOException {
        ExtentTest test = report.createTest("Mobile test");
        test.log(Status.INFO, "Mobile is starting");

        start();

        HomePagePOM hpom = PageFactory.initElements(webDriver, HomePagePOM.class);
        //Thread.sleep(2000);
        hpom.mobileClick();
        String expected = "app";

        String id = hpom.appPage.getAttribute("id");
        try{
            Assert.assertEquals(expected, id);
        }
        catch (AssertionError error){
            ScreenShot shotMobile = new ScreenShot();
            String shot1Mobile = shotMobile.take(webDriver, "Mobile");
            test.addScreenCaptureFromPath(shot1Mobile);
        }
    }

    @Test
    //Remembers previous Postcode entered
    public void postcode()throws InterruptedException, IOException{
        ExtentTest test = report.createTest("Postcode test");
        test.log(Status.INFO, "Postcode is starting");

        start();
        HomePagePOM hpom = PageFactory.initElements(webDriver, HomePagePOM.class);

        String post = "M50 2TJ";
        hpom.setPostcode(post);

        hpom.searchClick();

        webDriver.navigate().to(url);

        String autoPost = hpom.postcode.getAttribute("value");
        try{
            Assert.assertEquals(post.replace(" ", ""), autoPost);
        }
        catch(AssertionError error){
            ScreenShot shotPostCode = new ScreenShot();
            String shot1Postcode = shotPostCode.take(webDriver, "Postcode");
            test.addScreenCaptureFromPath(shot1Postcode);
        }
    }

    @Test
    //Search for car
    public void search() throws IOException{
        ExtentTest test= report.createTest("Search Test");
        test.log(Status.INFO, "Search Test is starting");

        start();
        HomePagePOM hpom = PageFactory.initElements(webDriver, HomePagePOM.class);

        String post = "M50 2TJ";
        hpom.setPostcode(post);

        hpom.searchClick();

        String currentURL=webDriver.getCurrentUrl();

        String expectedURL ="https://www.autotrader.co.uk/car-search?advertising-location=at_cars&search-target=usedcars&is-quick-search=true&radius=&onesearchad=used&onesearchad=nearlynew&onesearchad=new&make=&price-from=&price-to=&postcode=m502tj";
        try{
            Assert.assertEquals(expectedURL, currentURL);
        }
        catch(AssertionError error){
            ScreenShot shotSearch = new ScreenShot();
            String shot1Search = shotSearch.take(webDriver, "Search");
            test.addScreenCaptureFromPath(shot1Search);
        }
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
