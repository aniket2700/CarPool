package CarPool;

import java.util.ArrayList;
import java.util.Collections;

public class Admin extends User{

    void displayRides() {
        for(Ride r : db.getRideList()) {
            System.out.println(r);
        }
    }

    int getTotalFare() {
        int totalFare = 0;
        for(Ride r : db.getRideList()) {
            int f = r.getFare();
            totalFare += f;
        }
        return totalFare;
    }

    ArrayList<String> getAllCities() {
        ArrayList<String> cityList = new ArrayList<>();
        for(Ride r : db.getRideList()) {
            if (!cityList.contains(r.getSource())) cityList.add(r.getSource());
            if (!cityList.contains(r.getDestination())) cityList.add(r.getDestination());
        }
        Collections.sort(cityList);
        return cityList;
    }

    ArrayList<String> getAllSource(String destination) {
        ArrayList<String> sourceList = new ArrayList<>();
        for(Ride r : db.getRideList()) {
            if(r.getDestination().equals(destination) && !sourceList.contains(r.getSource())) {
                sourceList.add(r.getSource());
            }
        }
        Collections.sort(sourceList);
        return sourceList;
    }

    ArrayList<String> getAllDestination(String source) {
        ArrayList<String> destinationList = new ArrayList<>();
        for(Ride r : db.getRideList()) {
            if(r.getSource().equals(source) && !destinationList.contains(r.getDestination())) {
                destinationList.add(r.getDestination());
            }
        }
        Collections.sort(destinationList);
        return destinationList;
    }

}
