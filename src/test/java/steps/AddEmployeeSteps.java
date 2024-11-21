package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.AddEmployeePage;
import utils.CommonMethods;
import utils.ExcelReader;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class AddEmployeeSteps extends CommonMethods {

    AddEmployeePage addEmployeePage = new AddEmployeePage();

    @When("user enters firstname and lastname")
    public void user_enters_firstname_and_lastname() {
        //WebElement firstNameLocator = driver.findElement(By.id("firstName"));
        //WebElement lastNameLocator = driver.findElement(By.id("lastName"));

        //firstNameLocator.sendKeys("mark");
        //lastNameLocator.sendKeys("jacob");
        sendText("mark", addEmployeePage.firstNameLocator);
        sendText("jacob", addEmployeePage.lastNameLocator);

    }

    @When("user clicks on save button")
    public void user_clicks_on_save_button() {
        //WebElement saveBtn = driver.findElement(By.id("btnSave"));
        //saveBtn.click();
        click(addEmployeePage.saveBtn);
    }

    @Then("employee added successfully")
    public void employee_added_successfully() {
        System.out.println("Steps will be implemented later");

    }

    @When("user enters firstname and middlename and lastname")
    public void user_enters_firstname_and_middlename_and_lastname() {
        //WebElement firstNameLocator = driver.findElement(By.id("firstName"));
        //WebElement lastNameLocator = driver.findElement(By.id("lastName"));
        //WebElement middleNameLocator = driver.findElement(By.id("middleName"));

        //firstNameLocator.sendKeys("mark");
        //lastNameLocator.sendKeys("jacob");
        //middleNameLocator.sendKeys("ms");
        sendText("mark", addEmployeePage.firstNameLocator);
        sendText("ms", addEmployeePage.middleNameLocator);
        sendText("jacob", addEmployeePage.lastNameLocator);
    }

    @When("user adds {string}, {string} and {string}")
    public void user_adds_and(String fn, String mn, String ln) {

        //WebElement firstNameLocator = driver.findElement(By.id("firstName"));
        //WebElement lastNameLocator = driver.findElement(By.id("lastName"));
        //WebElement middleNameLocator = driver.findElement(By.id("middleName"));

        //firstNameLocator.sendKeys(fn);
        //middleNameLocator.sendKeys(mn);
        //lastNameLocator.sendKeys(ln);
        sendText(fn, addEmployeePage.firstNameLocator);
        sendText(mn, addEmployeePage.middleNameLocator);
        sendText(ln, addEmployeePage.lastNameLocator);
    }

    @When("user adds multiple employees using data table and save them")
    public void user_adds_multiple_employees_using_data_table_and_save_them
            (io.cucumber.datatable.DataTable dataTable) {

        List<Map<String, String>> employeeNames = dataTable.asMaps();

        for (Map<String, String> employee : employeeNames) {

            //WebElement firstNameLocator = driver.findElement(By.id("firstName"));
            //WebElement lastNameLocator = driver.findElement(By.id("lastName"));
            //WebElement middleNameLocator = driver.findElement(By.id("middleName"));

            addEmployeePage.firstNameLocator.sendKeys(employee.get("firstName"));
            addEmployeePage.middleNameLocator.sendKeys(employee.get("middleName"));
            addEmployeePage.lastNameLocator.sendKeys(employee.get("lastName"));


            //WebElement saveBtn = driver.findElement(By.id("btnSave"));
            addEmployeePage.saveBtn.click();

            WebElement addEmpOption = driver.findElement(By.id("menu_pim_addEmployee"));
            addEmpOption.click();
        }
    }

    @When("user adds multiple employees from excel file")
    public void user_adds_multiple_employees_from_excel_file() throws IOException {
        List<Map<String, String>> newEmployees = ExcelReader.read();

        for (Map<String, String> employee : newEmployees) {

            // WebElement firstNameLocator = driver.findElement(By.id("firstName"));
            //WebElement lastNameLocator = driver.findElement(By.id("lastName"));
            // WebElement middleNameLocator = driver.findElement(By.id("middleName"));

            addEmployeePage.firstNameLocator.sendKeys(employee.get("firstName"));
            addEmployeePage.middleNameLocator.sendKeys(employee.get("middleName"));
            addEmployeePage.lastNameLocator.sendKeys(employee.get("lastName"));

            // WebElement saveBtn = driver.findElement(By.id("btnSave"));
            addEmployeePage.saveBtn.click();

            WebElement addEmpOption = driver.findElement(By.id("menu_pim_addEmployee"));
            addEmpOption.click();


        }
    }
}
