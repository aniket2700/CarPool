package CarPool;

import java.util.ArrayList;

class DataBase {

    private static ArrayList<Ride> rl = new ArrayList<>();

    void addRide(Ride r) {
        rl.add(r);
    }

    void removeRide(int i) {
        rl.remove(i);
    }

    ArrayList<Ride> getRideList() {
        return rl;
    }

}