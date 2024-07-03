package stepDefinations;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;

public class UserRegistrationSteps {

    @Given("User is on regis Page")
    public void user_is_on_regis_Page() {
        System.out.println("User is on Registration Page");
    }

    @When("user enters following User details")
    public void user_enters_following_user_details(io.cucumber.datatable.DataTable table) {
         List<List<String>> data=table.asLists(String.class);
         for(List<String> i:data){
             System.out.println(i);
         }
    }

    @When("user enters following User details with columns")
    public void user_enters_following_user_details_with_columns(DataTable dataTable) {
         List<Map<String,String>>data=dataTable.asMaps(String.class,String.class);
       // System.out.println(data.get(0).get("Name")); //first row data with specific Key
        for(Map<String,String> mp:data){
            System.out.println(mp.get("Name"));
            System.out.println(mp.get("Profession"));
            System.out.println(mp.get("Email"));
            System.out.println(mp.get("Phone"));
            System.out.println(mp.get("Location"));
        }
    }

    @Then("User registration should be successful")
    public void user_registration_should_be_successful() {
        System.out.println("Registration Successful");
    }

}
