package CarPoolCopy;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

class Main {

    private static Scanner sc=new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
        Admin admin;
        User user;
        SignUpLogin log = new SignUpLogin();

        String username, password, email, source, destination;
        int fare, type, op, id;
        boolean working = true;

        System.out.println("ENTER 1 TO LOGIN.");
        System.out.println("ENTER 2 TO SIGN UP.");
        System.out.println("ENTER 3 TO CHANGE PASSWORD.");
        System.out.print("ENTER OPTION: ");
        type = sc.nextInt();

        log.getConnection();

        if(type == 1) {

            username = inputUsername();
            password = inputPassword();

            if(log.login(username, password)) {

                System.out.println("LOGIN SUCCESSFUL...");

                if(username.equals("Admin")) {
                    admin = new Admin();
                    while(working) {
                        ui(true);
                        op = sc.nextInt();

                        switch(op) {
                            case 1: {
                                source = inputSource();
                                destination = inputDestination();
                                fare = inputFare();
                                admin.createRide(username, source, destination, fare);
                                break;
                            }
                            case 2: {
                                admin.displayAllRides();
                                break;
                            }
                            case 3: {
                                source = inputSource();
                                destination = inputDestination();
                                admin.showAvailable(source, destination);
                                break;
                            }
                            case 4: {
                                id = inputId();
                                if(admin.updateAvailable(id)) {
                                    source = inputSource();
                                    destination = inputDestination();
                                    fare = inputFare();
                                    admin.updateRide(id, source, destination, fare);
                                }
                                else {
                                    System.out.println("Ride not available to update.");
                                }
                            }
                            case 5: {
                                id = inputId();
                                admin.deleteRide(id);
                                break;
                            }
                            case 6: {
                                id = inputId();
                                admin.bookRide(id, username);
                                break;
                            }
                            case 7: {
                                id = inputId();
                                admin.cancelRide(id);
                                break;
                            }
                            case 8: {
                                admin.showBookedRides(username);
                                break;
                            }
                            case 9: {
                                ArrayList<String> list = admin.getAllCities();
                                System.out.println(list);
                                break;
                            }
                            case 10: {
                                destination = inputDestination();
                                ArrayList<String> list = admin.getAllSource(destination);
                                System.out.println(list);
                                break;
                            }
                            case 11: {
                                source = inputSource();
                                ArrayList<String> list = admin.getAllDestination(source);
                                System.out.println(list);
                                break;
                            }
                            case 12: {
                                admin.getTotalFare();
                                break;
                            }
                            case 13: {
                                admin.displayUsers();
                                break;
                            }
                            case 14: {
                                id = inputId();
                                admin.removeUser(id);
                                break;
                            }
                            case 0: {
                                System.out.println("LOGOUT SUCCESSFUL...");
                                working = false;
                                break;
                            }
                            default: {
                                System.out.println("INVALID INPUT...");
                                break;
                            }
                        }
                    }
                }

                else {
                    user = new User();
                    while(working) {
                        ui(false);
                        op = sc.nextInt();

                        switch(op) {
                            case 1: {
                                source = inputSource();
                                destination = inputDestination();
                                fare = inputFare();
                                user.createRide(username, source, destination, fare);
                                break;
                            }
                            case 2: {
                                user.displayRides(username);
                                break;
                            }
                            case 3: {
                                source = inputSource();
                                destination = inputDestination();
                                user.showAvailable(source, destination);
                                break;
                            }
                            case 4: {
                                id = inputId();
                                if(user.updateAvailable(username, id)) {
                                    source = inputSource();
                                    destination = inputDestination();
                                    fare = inputFare();
                                    user.updateRide(username, id, source, destination, fare);
                                }
                                else {
                                    System.out.println("Ride not available to update.");
                                }
                                break;
                            }
                            case 5: {
                                id = inputId();
                                user.deleteRide(id, username);
                                break;
                            }
                            case 6: {
                                id = inputId();
                                user.bookRide(id, username);
                                break;
                            }
                            case 7: {
                                id = inputId();
                                user.cancelRide(id, username);
                                break;
                            }
                            case 8: {
                                user.showBookedRides(username);
                                break;
                            }
                            case 9: {
                                ArrayList<String> list = user.getAllCities();
                                System.out.println(list);
                                break;
                            }
                            case 10: {
                                destination = inputDestination();
                                ArrayList<String> list = user.getAllSource(destination);
                                System.out.println(list);
                                break;
                            }
                            case 11: {
                                source = inputSource();
                                ArrayList<String> list = user.getAllDestination(source);
                                System.out.println(list);
                                break;
                            }
                            case 0: {
                                System.out.println("LOGOUT SUCCESSFUL...");
                                working = false;
                                break;
                            }
                            default: {
                                System.out.println("INVALID INPUT...");
                                break;
                            }
                        }
                    }
                }

            }

            else {
                System.out.println("INCORRECT USERNAME OR PASSWORD...");
            }

        }
        else if(type == 2) {

            System.out.println();
            username = inputUsername();
            email = inputEmail();

            if(log.userExists(username, email)) {
                System.out.println("USERNAME OR EMAIL ALREADY EXISTS.");
            }
            else {
                password = inputPassword();
                log.register(username, password, email);
            }

        }
        else if(type == 3) {

            username = inputUsername();
            email = inputEmail();

            if(log.userExists(username, email)) {
                System.out.println("USER VERIFIED.");
                password = inputPassword();
                log.changePassword(username, password, email);
            }
            else {
                System.out.println("VERIFICATION FAILED...");
            }

        }
        else {
            System.out.println("INVALID INPUT...");
        }

        log.closeConnection();
    }

    public static void ui(boolean isAdmin) {
        System.out.println();
        System.out.print("ENTER 1 TO CREATE A RIDE.            ||");
        System.out.println("    ENTER 2 TO DISPLAY ALL RIDES.");
        System.out.print("ENTER 3 TO DISPLAY AVAILABLE RIDES.  ||");
        System.out.println("    ENTER 4 TO UPDATE RIDE.");
        System.out.print("ENTER 5 TO DELETE RIDE.              ||");
        System.out.println("    ENTER 6 TO BOOK RIDE.");
        System.out.print("ENTER 7 TO CANCEL RIDE.              ||");
        System.out.println("    ENTER 8 TO DISPLAY BOOKED RIDES.");
        System.out.print("ENTER 9 TO SHOW ALL CITIES.          ||");
        System.out.println("    ENTER 10 TO SHOW ALL SOURCES.");
        System.out.print("ENTER 11 TO SHOW ALL DESTINATIONS.   ||");
        if(isAdmin) {
            System.out.println("    ENTER 12 TO DISPLAY TOTAL FARE.");
            System.out.print("ENTER 13 TO DISPLAY ALL USERS.       ||");
            System.out.println("    ENTER 14 TO REMOVE USER.");
        }
        System.out.println("ENTER 0 TO LOGOUT.");
        System.out.print("ENTER OPTION: ");
    }

    public static String inputUsername() {
        System.out.print("ENTER USERNAME: ");
        return capitalize(sc.next());
    }

    public static String inputEmail() {
        System.out.print("ENTER EMAIL: ");
        return sc.next();
    }

    public static String inputPassword() {
        System.out.print("ENTER PASSWORD: ");
        return sc.next();
    }

    public static String inputSource() {
        System.out.print("ENTER SOURCE: ");
        return capitalize(sc.next());
    }

    public static String inputDestination() {
        System.out.print("ENTER DESTINATION: ");
        return capitalize(sc.next());
    }

    public static int inputFare() {
        System.out.print("ENTER FARE: ");
        return sc.nextInt();
    }

    public static int inputId() {
        System.out.print("ENTER ID: ");
        return sc.nextInt();
    }

    public static String capitalize(String s) {
        s = s.substring(0,1).toUpperCase() + s.substring(1,s.length());
        return s;
    }

    public static String enLayer1(String s) {
        String ns="";
        for(int i=0;i<s.length();i++) {
            char ch1 = s.charAt(i);
            int x = (int)ch1 + 5;
            char ch2 = (char)x;
            ns = ns + ch2;
        }
        return enLayer2(ns);
    }

    public static String enLayer2(String s) {
        return s+"layer2";
    }

}