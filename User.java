package CarPool;

import java.util.ArrayList;

public class User {

    Database db = new Database();
    Ride ride;
    private static int id = 0;
    private static boolean idAvailable = false;
    private static ArrayList<Integer> availIds= new ArrayList<>();

    void createRide(String source, String destination, int fare) {
        if(idAvailable) {
            ride = new Ride(source, destination, fare, availIds.get(0));
            db.getRideList().add(availIds.get(0)-1, ride);
            availIds.remove(0);
            idAvailable = false;
        }
        else {
            ride = new Ride(source,destination,fare,id);
            db.addRide(ride);
            id = id + 1;
        }
    }

    void displayRides(){
        for(Ride r : db.getRideList()) {
            System.out.println(r);
        }
    }

    void deleteRide(int id) {
        for(Ride r : db.getRideList()) {
            if(r.getId() == id) {
                db.removeRide(id-1);
                availIds.add(id);
                idAvailable = true;
                System.out.println("Ride deleted");
                break;
            }
        }
    }

    void updateSource(int id, String newSource) {
        for(Ride r : db.getRideList()) {
            if(r.getId() == id) {
                r.setSource(newSource);
                System.out.println("Ride source updated");
                break;
            }
        }
    }

    void updateDestination(int id, String newDestination) {
        for(Ride r : db.getRideList()) {
            if(r.getId() == id) {
                r.setDestination(newDestination);
                System.out.println("Ride destination updated.");
                break;
            }
        }
    }

    void updateFare(int id, int newFare) {
        for(Ride r : db.getRideList()) {
            if(r.getId() == id) {
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

