package resources;

import test.AddPlaceGoogleMaps;
import test.Locations;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {
    public  AddPlaceGoogleMaps addPlace(){
        AddPlaceGoogleMaps a=new AddPlaceGoogleMaps();//create object of parent class
        Locations l=new Locations();
        l.setLat(23.00);
        l.setLng(20.00);//call setter methods and add values for all fields
        a.setLocation(l);
        a.setName("testing place");
        a.setAccuracy(50);
        a.setAddress("Random test place");
        a.setPhone_number("1323001211");
        List<String> types=new ArrayList<String>();
        types.add("permanent");
        types.add("temporary");
        a.setTypes(types);
        a.setWebsite("random.com");
        a.setLanguage("English");
        return a;
    }
    public  AddPlaceGoogleMaps addPlaceDynamically(String name,String place){
        AddPlaceGoogleMaps a=new AddPlaceGoogleMaps();//create object of parent class
        Locations l=new Locations();
        l.setLat(23.00);
        l.setLng(20.00);//call setter methods and add values for all fields
        a.setLocation(l);
        a.setName(name);
        a.setAccuracy(50);
        a.setAddress(place);
        a.setPhone_number("1323001211");
        List<String> types=new ArrayList<String>();
        types.add("permanent");
        types.add("temporary");
        a.setTypes(types);
        a.setWebsite("random.com");
        a.setLanguage("English");
        return a;
    }
}
