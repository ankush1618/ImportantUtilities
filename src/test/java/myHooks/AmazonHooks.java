package myHooks;


import io.cucumber.java.*;

public class AmazonHooks {

    @Before(order=1)
    public void setUp(Scenario sc){
        System.out.println("Before Hook: Launch amazon Application");
        System.out.println("Scenario name that is executed :"+sc.getName());
    }

    @Before(order=2)
    public void setUrl(){
        System.out.println("Launch Url");
    }

    //Runs in decreasing  order 2 will run before order 1
    @After(order=1)
    public void tearDown_close(Scenario cs){
        System.out.println("After Hook order 1: Close the Browser");
        System.out.println("Scenario name :"+cs.getName());
    }

    @After(order=2)
    public void tearDown_Logout(){
        System.out.println("After Hook order 2: Logout the application");
    }

    @BeforeStep
    public void takeScreenShot(){
        System.out.println("Take the screen shot");
    }

    @AfterStep
    public void refreshPage(){
        System.out.println("Refresh the page");
    }

}
