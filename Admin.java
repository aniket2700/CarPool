package CarPoolCopy;

import java.sql.SQLException;

class Admin extends User{

    void getTotalFare() throws SQLException{
        db.totalFare();
    }

    void displayAllRides() throws SQLException {
        db.showAllRides();
    }

    void deleteRide(int id) throws SQLException {
        db.deleteRow(id);
    }

    boolean updateAvailable(int id) throws SQLException {
        return db.updateAvailable(id);
    }

    void cancelRide(int id) throws SQLException {
        db.cancelRide(id);
    }

    void updateRide(int id, String source, String destination, int fare) throws SQLException {
        db.updateRide(id, source, destination, fare);
    }

    void displayUsers() throws SQLException {
        db.displayUsers();
    }

    void removeUser(int id) throws SQLException {
        db.removeUser(id);
    }

}
