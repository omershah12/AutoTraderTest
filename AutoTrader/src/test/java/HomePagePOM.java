import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePagePOM {

    @FindBy(css="#postcode")
    WebElement postcode;

    @FindBy(css="#js-search-button")
    WebElement search;

    @FindBy(css="body > div.is-non-critical.o-clearfix.ov-pos-rel > footer > div > nav:nth-child(4) > form > button")
    WebElement mobile;

    @FindBy(css="#app")
    WebElement appPage;

    public void searchClick(){
        search.click();
    }

    public void mobileClick(){
        mobile.click();
    }

    public void setPostcode(String p){
        postcode.sendKeys(p);
    }

}
