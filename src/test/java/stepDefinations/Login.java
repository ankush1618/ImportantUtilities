package stepDefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Login {

    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {

    }

    @When("the user enters {string} in username field")
    public void the_user_enters_in_username_field(String string) {

    }

    @When("the user enters {string} in password field")
    public void the_user_enters_in_password_field(String string) {

    }

    @When("clicks the submit button")
    public void clicks_the_submit_button() {

    }

    @Then("the user should be logged in successfully")
    public void the_user_should_be_logged_in_successfully() {

    }

    @Then("user gets Login Failed Error")
    public void user_gets_login_failed_error() {

    }
}
