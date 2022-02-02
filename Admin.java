package CarPool;

import java.util.ArrayList;
import java.util.Collections;

public class Admin {

    ArrayList<Ride> rideList = new ArrayList<>();

    void createRide(String source, String destination, int fare) {
        Ride ride = new Ride(source,destination,fare);
        rideList.add(ride);
    }

    void displayRide() {
        for(Ride r : rideList) {
            System.out.println(r);
        }
    }

    void deleteRide(String source, String destination, int fare) {
        int i=0;
        for(Ride r : rideList) {
            if(r.getSource().equals(source) && r.getDestination().equals(destination) && r.getFare()==fare) {
                rideList.remove(i);
                System.out.println("Ride deleted");
                break;
            }
            i++;
        }
    }

    ArrayList<String> getAllCities() {
        ArrayList<String> cityList = new ArrayList<>();
        for(Ride r : rideList) {
            if (!cityList.contains(r.getSource())) cityList.add(r.getSource());
            if (!cityList.contains(r.getDestination())) cityList.add(r.getDestination());
        }
        Collections.sort(cityList);
        return cityList;
    }

    ArrayList<String> getAllSource(String destination) {
        ArrayList<String> sourceList = new ArrayList<>();
        for(Ride r : rideList) {
            if(r.getDestination().equals(destination) && !sourceList.contains(r.getSource())) {
                sourceList.add(r.getSource());
            }
        }
        Collections.sort(sourceList);
        return sourceList;
    }

    ArrayList<String> getAllDestination(String source) {
        ArrayList<String> destinationList = new ArrayList<>();
        for(Ride r : rideList) {
            if(r.getSource().equals(source) && !destinationList.contains(r.getDestination())) {
                destinationList.add(r.getDestination());
            }
        }
        Collections.sort(destinationList);
        return destinationList;
    }
    
    int getTotalFare() {
        int totalFare = 0;
        for(Ride r : rideList) {
            int f = r.getFare();
            totalFare += f;
        }
        return totalFare;
    }

    void updateSource(String source, String destination, int fare, String newSource) {
        for(Ride r : rideList) {
            if(r.getSource().equals(source) && r.getDestination().equals(destination) && r.getFare()==fare) {
                r.setSource(newSource);
                System.out.println("Ride source updated");
                break;
            }
        }
    }

    void updateDestination(String source, String destination, int fare, String newDestination) {
        for(Ride r : rideList) {
            if(r.getSource().equals(source) && r.getDestination().equals(destination) && r.getFare()==fare) {
                r.setDestination(newDestination);
                System.out.println("Ride destination updated.");
                break;
            }
        }
    }

    void updateFare(String source, String destination, int fare, int newFare) {
        for(Ride r : rideList) {
            if(r.getSource().equals(source) && r.getDestination().equals(destination) && r.getFare()==fare) {
                r.setFare(newFare);
                System.out.println("Ride fare updated.");
                break;
            }
        }
    }

}
