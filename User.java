package CarPool;

import java.util.ArrayList;

public class User {

    DataBase db = new DataBase();

    void createRide(String source, String destination, int fare) {
        Ride ride = new Ride(source,destination,fare);
        db.addRide(ride);
    }

    void deleteRide(String source, String destination, int fare) {
        int i=0;
        for(Ride r : db.getRideList()) {
            if(r.getSource().equals(source) && r.getDestination().equals(destination) && r.getFare()==fare) {
                db.removeRide(i);
                System.out.println("Ride deleted");
                break;
            }
            i++;
        }
    }

    void updateSource(String source, String destination, int fare, String newSource) {
        for(Ride r : db.getRideList()) {
            if(r.getSource().equals(source) && r.getDestination().equals(destination) && r.getFare()==fare) {
                r.setSource(newSource);
                System.out.println("Ride source updated");
                break;
            }
        }
    }

    void updateDestination(String source, String destination, int fare, String newDestination) {
        for(Ride r : db.getRideList()) {
            if(r.getSource().equals(source) && r.getDestination().equals(destination) && r.getFare()==fare) {
                r.setDestination(newDestination);
                System.out.println("Ride destination updated.");
                break;
            }
        }
    }

    void updateFare(String source, String destination, int fare, int newFare) {
        for(Ride r : db.getRideList()) {
            if(r.getSource().equals(source) && r.getDestination().equals(destination) && r.getFare()==fare) {
                r.setFare(newFare);
                System.out.println("Ride fare updated.");
                break;
            }
        }
    }

    void bookRide(String source, String destination) {

    }

    void showAvailable(String source, String destination) {
        for(Ride r : db.getRideList()) {
            if(r.getSource().equals(source) && r.getDestination().equals(destination)) {
                System.out.println(r);
            }
        }
    }

}

