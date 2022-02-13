package CarPoolCopy;

import java.sql.SQLException;

public class SignUpLogin {

    DataBase db = new DataBase();

    boolean login(String user, String pass) throws SQLException {
        return db.login(user, pass);
    }

    boolean userExists(String user, String email) throws SQLException {
        return db.userExists(user, email);
    }

    void register(String user, String pass, String email) throws SQLException {
        db.registerUser(user,pass,email);
    }

    boolean verifyUser(String user, String email) throws SQLException {
        return db.verifyUser(user, email);
    }

    void changePassword(String user, String pass, String email) throws SQLException {
        db.changePassword(user, pass, email);
    }

    void getConnection() throws SQLException {
        db.getConnection();
    }

    void closeConnection() throws SQLException {
        db.closeConnection();
    }

}
