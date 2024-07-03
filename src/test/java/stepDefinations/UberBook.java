package stepDefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UberBook {

    //Cucumber Expressions({string} in step) instead of Regular Expressions (\\d+)
    @Given("User want to select car type {string} from Uber App")
    public void user_want_to_select_car_type_from_uber_app(String carType) {
        System.out.println("Step1: User selects the Car Type as :"+carType);
    }

    @When("User selects car {string} and pick up point {string} and drop location {string}")
    public void user_selects_car_and_pick_up_point_and_drop_location(String carType, String pickUp, String drop) {
        System.out.println("Step2: Selects :"+carType +" from "+pickUp+" to "+drop);
    }

    @Then("Driver starts the Journey")
    public void driver_starts_the_journey() {
        System.out.println("Step3: Driver starts Journey");
    }

    @Then("Driver ends the journey")
    public void driver_ends_the_journey() {
        System.out.println("Step4: Ends the Journey");
    }
   //Using Regular Expressions (\\d+) instead of Cucumber Expression {int}
    @Then("^User pays (\\d+) rupees$")
    public void user_pays_rupees(Integer price) {
        System.out.println("Step5:User pays :"+price);
    }

}
