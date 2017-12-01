import org.apache.poi.ss.formula.functions.WeekNum;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignInPOM {

    @FindBy(css="#signInSubmit")
    WebElement signInButton;

    @FindBy(css="#js-social__signin > div > form > span:nth-child(8) > div")
    WebElement errorMessage;

    @FindBy(css="#js-facebook__signin--button")
    WebElement facebook;

    @FindBy(css="#js-social__signup-tab")
    WebElement loginButton;

    @FindBy(css="#email")
    WebElement emailSignUp;

    @FindBy(css="#password")
    WebElement passwordSignUp;

    @FindBy(css="#likeToReceiveNewsletter")
    WebElement checkBox1;

    @FindBy(css="#likeToReceiveThirdPartyServices")
    WebElement checkBox2;

    @FindBy(css="#social--signup--submit")
    WebElement sigupButton;

    public void signInButonClick(){
        signInButton.click();
    }

    public void facebookClick(){
        facebook.click();
    }

    public void loginButtonClick(){
        loginButton.click();
    }

    public void setEmailSignUp(String e){
        emailSignUp.sendKeys(e);
    }

    public void setPasswordSignUp(String p){
        passwordSignUp.sendKeys(p);
    }

    public void setCheckBox1(){
        if(!checkBox1.isSelected()){
            checkBox1.click();
        }
    }

    public void setCheckBox2(){
        if(!checkBox2.isSelected()){
            checkBox2.click();
        }
    }

    public void signUpButtonClick(){
        sigupButton.click();
    }
}
