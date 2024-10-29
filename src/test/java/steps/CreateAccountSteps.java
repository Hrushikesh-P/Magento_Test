package steps;

import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Random;

public class CreateAccountSteps {
    WebDriver driver ;

    @Given("the user is on the Magento homepage")
    public void the_user_is_on_the_Magento_homepage() {
        driver = new ChromeDriver();
        driver.get("https://magento.softwaretestingboard.com/");
        driver.manage().window().maximize();
    }

    @When("the user clicks on {string} hyperlink")
    public void the_user_clicks_on_hyperlink(String linkText) {
        WebElement createAccountLink = driver.findElement(By.linkText(linkText));
        createAccountLink.click();
    }

    @When("the user enters details with {string} , {string} , {string} , {string}")
    public void the_user_enters_valid_details(String firstname, String lastname, String email, String password) {
        driver.findElement(By.id("firstname")).sendKeys(firstname);
        driver.findElement(By.id("lastname")).sendKeys(lastname);
        driver.findElement(By.id("email_address")).sendKeys(email);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("password-confirmation")).sendKeys(password);
    }

    @When("the user submits the form")
    public void the_user_submits_the_form() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        driver.findElement(By.cssSelector("button[title='Create an Account']")).click();
    }

    @Then("the account should be created successfully")
    public void the_account_should_be_created_successfully() {
        String currentURL = driver.getCurrentUrl();
        assert(currentURL.equals("https://magento.softwaretestingboard.com/customer/account/"));
    }


    @Then("user should see the message {string}")
    public void userShouldSeeTheMessage(String successMessage) {
        WebElement welcomeMessage = driver.findElement(By.className("messages"));
        assert(welcomeMessage.getText().contains(successMessage));
        driver.quit();
    }

    @Then("an error message {string} is displayed for each input fields.")
    public void anErrorMessageIndicatingRequiredFieldsIsDisplayedForEachInputFields(String errorMsg) {
        System.out.println("error " + driver.findElement(By.id("firstname-error")).getText());
        assert(driver.findElement(By.id("firstname-error")).getText().contains(errorMsg));
        assert(driver.findElement(By.id("lastname-error")).getText().contains(errorMsg));
        assert(driver.findElement(By.id("email_address-error")).getText().contains(errorMsg));
        assert(driver.findElement(By.id("password-error")).getText().contains(errorMsg));
        assert(driver.findElement(By.id("password-confirmation-error")).getText().contains(errorMsg));
    }

    @Then("an error message {string} is displayed.")
    public void anErrorMessageIsDisplayed(String errMsg) {
        assert(driver.findElement(By.id("email_address-error")).getText().contains(errMsg));
    }


    @Then("validation error for password strength is displayed")
    public void validationErrorForPasswordStrengthIsDisplayed() {
        assert(driver.findElement(By.id("password-error")).getText().contains("Minimum length of this field must be equal or greater than 8 symbols."));
    }


}
