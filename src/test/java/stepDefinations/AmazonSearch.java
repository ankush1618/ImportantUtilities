package stepDefinations;

import amazonImplementations.Product;
import amazonImplementations.Search;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class AmazonSearch {

    //TDD approach
    Product product;
    Search search;

    @Given("I have a Search field on a Amazomn Page")
    public void i_have_a_search_field_on_a_amazomn_page() {
        System.out.println("Step1: I am on Search Page");
    }

    @When("I search for a Product with name {string} and price {int}")
    public void i_search_for_a_product_with_name_and_price(String name, Integer price) {
        System.out.println("Step2: Search the Product with name "+name+" and price "+ price);
        product=new Product(name,price);

    }

    @Then("Product with name {string} is displayed")
    public void product_with_name_is_displayed(String name) {
        System.out.println("Step3: Product with name "+name+ "is displayed");
        search=new Search();
        String names=search.searchProduct(product);
        Assert.assertEquals(names,name);
    }

}
