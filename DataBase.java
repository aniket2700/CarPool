package CarPoolCopy;

import java.sql.*;
import java.util.ArrayList;

class DataBase {

    private static Connection conn = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;
    private static PreparedStatement prep = null;

    private final String allSources = "select distinct source from ride order by source;";
    private final String allDestinations = "select distinct destination from ride order by destination;";

    void getConnection() throws SQLException {
        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/carpool","postgres","Admin");
            stmt = conn.createStatement();
            System.out.println("Connection Generated.");
        }
        catch (SQLException e) {
            System.out.println(e);
        }
    }

    void insertRow(String user, String source, String destination, int fare) throws SQLException {
        prep = conn.prepareStatement("insert into ride(source,destination,fare,booked,creator_id) values(?,?,?,?,?)");
        prep.setString(1, source);
        prep.setString(2, destination);
        prep.setInt(3, fare);
        prep.setString(4, "available");
        prep.setInt(5,getUserId(user));
        int i = prep.executeUpdate();
        System.out.println((i == 1)?"Insertion Successful.":"Insertion Failed.");
    }

    void deleteRow(int id) throws SQLException {
        int i = stmt.executeUpdate("delete from ride where id = "+id+";");
        System.out.println((i == 1)?"Deletion Successful.":"Deletion Failed.");
    }

    void deleteRow(int id, String user) throws SQLException {
        int i = stmt.executeUpdate("delete from ride where id = "+id+" and creator_id = "+getUserId(user)+";");
        System.out.println((i == 1)?"Deletion Successful.":"Deletion Failed.");
    }

    int getLastId() throws SQLException {
        int id = 0;
        rs = stmt.executeQuery("select id from ride order by id desc limit 1;");
        while (rs.next()) {
            id = rs.getInt(1);
        }
        return id;
    }

    void showAllRides() throws SQLException {
        rs = stmt.executeQuery("select * from ride order by id;");
        while (rs.next()) {
            System.out.println("[ "+rs.getString(1)+", "+rs.getString(2)+", "+rs.getString(3)
                    +", "+rs.getString(4)+", "+rs.getString(5)+", "+rs.getInt(6)+" ]");
        }
    }

    void showAllRides(String source, String destination) throws SQLException {
        prep = conn.prepareStatement("select * from ride where source =? and destination =? and booked = 'available';");
        prep.setString(1, source);
        prep.setString(2, destination);
        rs = prep.executeQuery();
        while (rs.next()) {
            System.out.println("[ "+rs.getString(1)+", "+rs.getString(2)+", "+rs.getString(3)+", "+rs.getString(4)+" ]");
        }
    }

    void showAllRides(String user) throws SQLException {
        rs = stmt.executeQuery("select * from ride where creator_id = "+getUserId(user)+";");
        while (rs.next()) {
            System.out.println("[ "+rs.getString(1)+", "+rs.getString(2)+", "+rs.getString(3)+", "+rs.getString(4)+" ]");
        }
    }

    void showBookedRides(String user) throws SQLException {
        rs = stmt.executeQuery("select * from ride where booked = 'booked' and customer_id = "+getUserId(user)+";");
        while (rs.next()) {
            System.out.println("[ "+rs.getString(1)+", "+rs.getString(2)+", "+rs.getString(3)+", "+rs.getString(4)+" ]");
        }
    }

    ArrayList<String> showAllCities() throws SQLException {
        ArrayList<String> cities = new ArrayList<>();
        rs = stmt.executeQuery(allSources);
        while (rs.next()) {
            if(!cities.contains(rs.getString(1))) {
                cities.add(rs.getString(1));
            }
        }
        rs = stmt.executeQuery(allDestinations);
        while (rs.next()) {
            if(!cities.contains(rs.getString(1))) {
                cities.add(rs.getString(1));
            }
        }
        return cities;
    }

    ArrayList<String> showAllSources(String destination) throws SQLException {
        ArrayList<String> sources = new ArrayList<>();
        rs = stmt.executeQuery("select distinct source from ride where destination = '"+destination+"' order by source;");
        while (rs.next()) {
            sources.add(rs.getString(1));
        }
        return sources;
    }

    ArrayList<String> showAllDestinations(String source) throws SQLException {
        ArrayList<String> destinations = new ArrayList<>();
        rs = stmt.executeQuery("select distinct destination from ride where source = '"+source+"' order by destination;");
        while (rs.next()) {
            destinations.add(rs.getString(1));
        }
        return destinations;
    }

    boolean updateAvailable(String user, int id) throws SQLException {
        rs = stmt.executeQuery("select * from ride where id = "+id+" and creator_id = "+getUserId(user)+";");
        if(rs.next())
        {
            return true;
        }
        return false;
    }

    boolean updateAvailable(int id) throws SQLException {
        rs = stmt.executeQuery("select * from ride where id = "+id+";");
        if(rs.next())
        {
            return true;
        }
        return false;
    }

    void updateRide(String user,int id, String source, String destination, int fare) throws SQLException {
        prep = conn.prepareStatement("update ride set source = ?, destination = ?, fare = ? where creator_id = ? and id = ?;");
        prep.setString(1, source);
        prep.setString(2, destination);
        prep.setInt(3, fare);
        prep.setInt(4, getUserId(user));
        prep.setInt(5, id);
        int i = prep.executeUpdate();
        System.out.println((i == 1)?"Updation of Ride ID "+id+" Successful.":"Updation Failed.");
    }

    void updateRide(int id, String source, String destination, int fare) throws SQLException {
        prep = conn.prepareStatement("update ride set source = ?, destination = ?, fare = ? where id = ?;");
        prep.setString(1, source);
        prep.setString(2, destination);
        prep.setInt(3, fare);
        prep.setInt(4, id);
        int i = prep.executeUpdate();
        System.out.println((i == 1)?"Updation of Ride ID "+id+" Successful.":"Updation Failed.");
    }

    void bookRide(int id, String user) throws SQLException {
        int i = stmt.executeUpdate("update ride set booked = 'booked', customer_id = "+getUserId(user)+" where id = "+id+" and booked = 'available';");
        System.out.println((i == 1)?"Booking of Ride ID "+id+" Successful.":"Booking Failed.");
    }

    void cancelRide(int id) throws SQLException {
        int i = stmt.executeUpdate("update ride set booked = 'available', customer_id = null where id = "+id+" and booked = 'booked';");
        System.out.println((i == 1)?"Cancelling of Ride ID "+id+" Successful.":"Cancelling Failed.");
    }

    void cancelRide(int id, String user) throws SQLException {
        int i = stmt.executeUpdate("update ride set booked = 'available', customer_id = null where id = "+id+" and booked = 'booked' and customer_id = "+getUserId(user)+";");
        System.out.println((i == 1)?"Cancelling of Ride ID "+id+" Successful.":"Cancelling Failed.");
    }

    void totalFare() throws SQLException {
        rs = stmt.executeQuery("select sum(fare) from ride where booked = true");
        while (rs.next()) {
            System.out.println("Total Fare = "+rs.getInt(1));
        }
    }

    void displayUsers() throws SQLException {
        rs = stmt.executeQuery("select * from users order by id;");
        while (rs.next()) {
            System.out.println("[ "+rs.getInt(1)+", "+rs.getString(2)+", "+rs.getString(3)+", "+rs.getString(4)+" ]");
        }
    }

    void removeUser(int id) throws SQLException {
        if(id == 1) {
            System.out.println("Cannot Remove Admin.");
            return;
        }
        int i = stmt.executeUpdate("delete from users where id = "+id+";");
        System.out.println((i == 1)?"User Removed.":"User Does not Exist.");
    }

    boolean login(String user, String pass) throws SQLException {
        rs = stmt.executeQuery("select * from users where name = '"+user+"' and password = '"+pass+"';");
        if(rs.next()) {
            return true;
        }
        return false;
    }

    boolean userExists(String user, String email) throws SQLException {
        rs = stmt.executeQuery("select * from users where name = '"+user+"' or email = '"+email+"';");
        if(rs.next()) {
            return true;
        }
        return false;
    }

    void registerUser(String user, String pass, String email) throws SQLException {
        prep = conn.prepareStatement("insert into users(name,password,email) values(?,?,?)");
        prep.setString(1,user);
        prep.setString(2,pass);
        prep.setString(3,email);
        int i = prep.executeUpdate();
        System.out.println((i == 1)?"Registration Successful.":"Registration Failed.");
    }

    boolean verifyUser(String user, String email) throws SQLException {
        rs = stmt.executeQuery("select * from users where name = '"+user+"' and email = '"+email+"';");
        if(rs.next()) {
            return true;
        }
        return false;
    }

    void changePassword(String user,String pass, String email) throws SQLException {
        prep = conn.prepareStatement("update users set password = ? where name = ? and email = ?");
        prep.setString(1,pass);
        prep.setString(2,user);
        prep.setString(3,email);
        int i = prep.executeUpdate();
        System.out.println((i == 1)?"Reset Password Successful.":"Reset Password Failed.");
    }

    int getUserId(String user) throws SQLException {
        int id = 0;
        rs = stmt.executeQuery("select id from users where name = '"+user+"';");
        while (rs.next()) {
            id = rs.getInt(1);
        }
        return id;
    }

    void closeConnection() throws SQLException {
        conn.close();
        System.out.println("Connection Terminated.");
    }

}
