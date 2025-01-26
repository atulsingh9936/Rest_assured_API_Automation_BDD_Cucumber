package resources;

import pojo.Add_place;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {

    public Add_place addPlacePayload(String name,String language,String address){
        Add_place p = new Add_place();
        p.setAccuracy(50);
        p.setAddress(address);
        p.setLanguage(language);
        p.setPhone_number("(+91) 983 893 3937");
        p.setWebsite("http://google.com");
        p.setName(name);
        List<String> my_list = new ArrayList<String>();
        my_list.add("shoe park");
        my_list.add("shop");

        p.setTypes(my_list);

        Location l = new Location();
        l.setLat(-38.383494);
        l.setLng(33.427362);
        p.setLocation(l);
        return p;
    }

    public String deletePlacePayload(String placeId){
        return "{\r\n    \"place_id\":\""+placeId+"\"\r\n}";
    }
}
