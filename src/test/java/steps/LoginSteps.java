package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*
        ;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;
import utils.CommonMethods;
import utils.ConfigReader;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Map;

public class LoginSteps extends CommonMethods {

//LoginPage loginPage=new LoginPage();

    @Given("user is able to access HRMS application")
    public void user_is_is_able_to_access_HRMS_application() throws IOException {
        openBrowserAndLaunchApplication();
        driver.get("http://hrm.syntaxtechs.net/humanresources/symfony/web/index.php/auth/login");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    @When("user enters valid username and password")
    public void user_enters_valid_username_and_password() throws InterruptedException {

        //WebElement usernameField = driver.findElement(By.id("txtUsername"));
        //usernameField.sendKeys("admin");
        sendText(ConfigReader.read("userName"), loginPage.usernameField);

        //WebElement passwordField = driver.findElement(By.id("txtPassword"));
        //passwordField.sendKeys("Hum@nhrm123");
        sendText(ConfigReader.read("password"), loginPage.passwordField);
    }

    @When("user clicks on login button")
    public void user_clicks_on_login_button() {
        //WebElement loginButton = driver.findElement(By.id("btnLogin"));
        //loginButton.click();
        click(loginPage.loginButton);
    }

    @Then("user is able to see dashboard page")
    public void user_is_able_to_see_dashboard_page() {
        System.out.println("Test passed");
    }
    @When("user clicks on PIM option")
    public void user_clicks_on_pim_option() {
        WebElement pimOption = driver.findElement(By.id("menu_pim_viewPimModule"));
        //pimOption.click();
        click(pimOption);
    }
    @When("user clicks on Add employee option")
    public void user_clicks_on_add_employee_option() {
        WebElement addEmpOption = driver.findElement(By.id("menu_pim_addEmployee"));
        //addEmpOption.click();
        click(addEmpOption);
    }
    @When("user enters invalid username and password")
    public void user_enters_invalid_username_and_password() {
        //WebElement usernameField = driver.findElement(By.id("txtUsername"));
        //usernameField.sendKeys("admin");
        sendText("admin",loginPage.usernameField);

        //WebElement passwordField = driver.findElement(By.id("txtPassword"));
        //passwordField.sendKeys("Hum@nhrm123");
        sendText("Hum@nhrm123",loginPage.passwordField);
    }
    @Then("user can see an error message")
    public void user_can_see_an_error_message() {
        System.out.println("Steps will be implemented later");

    }
    @When("user enters {string} and {string} and {string} in the application")
    public void user_enters_and_and_in_the_application(
            String firstName, String middleName, String lastName) {

        WebElement firstNameLocator = driver.findElement(By.id("firstName"));
        WebElement lastNameLocator = driver.findElement(By.id("lastName"));
        WebElement middleNameLocator = driver.findElement(By.id("middleName"));

        firstNameLocator.sendKeys(firstName);
        middleNameLocator.sendKeys(middleName);
        lastNameLocator.sendKeys(lastName);

    }


        }










