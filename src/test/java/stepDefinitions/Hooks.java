package stepDefinitions;

import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {

    @Before("@DeletePlace")//run before this method
    public void beforeScenario() throws IOException
    {		//execute this code only when place id is null
        //write a code that will give you place id

        stepDefDynamicData m =new stepDefDynamicData();//calling step def methods by creating stepdef class object
        if(stepDefDynamicData.place_id==null)
        {

            m.add_place_valid_payload_with("Achintya", "pojo");
            m.user_calls_with_post_request("AddPlaceApi", "POST");
            m.verify_place_id_created_maps_to_using("Achintya", "getPlaceApi");
        }



    }
}
