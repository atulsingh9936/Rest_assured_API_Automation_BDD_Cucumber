package Stepdefinitions;

import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {

    @Before("@DeletePlace")
    public void beforeScenario() throws IOException {

        Stepdefinitions m = new Stepdefinitions();
        m.addPlacePayloadWith("shetty","French","Asia");
        m.userCallsWithHttpRequest("AddPlaceAPI","POST");
        m.verifyPlaceIdCreatedMapsToUsing("Shetty","POST");

    }
}
