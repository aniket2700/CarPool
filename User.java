package CarPoolCopy;
//DT20229908259
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

class User {

    DataBase db = new DataBase();

    void createRide(String user, String source, String destination, int fare) throws SQLException {
        db.insertRow(user, source, destination, fare);
    }

    void deleteRide(int id, String user) throws SQLException {
        db.deleteRow(id, user);
    }

    void displayRides(String user) throws SQLException {
        db.showAllRides(user);
    }

    boolean updateAvailable(String user, int id) throws SQLException {
        return db.updateAvailable(user, id);
    }

    void updateRide(String user,int id, String source, String destination, int fare) throws SQLException {
        db.updateRide(user, id, source, destination, fare);
    }

    void bookRide(int id, String user) throws SQLException {
        db.bookRide(id, user);
    }

    void cancelRide(int id, String user) throws SQLException {
        db.cancelRide(id,user);
    }

    void showBookedRides(String user) throws SQLException {
        db.showBookedRides(user);
    }

    void showAvailable(String source, String destination) throws SQLException {
        db.showAllRides(source, destination);
    }

    ArrayList<String> getAllCities() throws SQLException {
        ArrayList<String> cityList = db.showAllCities();
        Collections.sort(cityList);
        return cityList;
    }

    ArrayList<String> getAllSource(String destination) throws SQLException {
        ArrayList<String> sourceList = db.showAllSources(destination);
        return sourceList;
    }

    ArrayList<String> getAllDestination(String source) throws SQLException{
        ArrayList<String> destinationList = db.showAllDestinations(source);
        return destinationList;
    }

}
