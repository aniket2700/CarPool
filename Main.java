package CarPool;

public class Main {

    public static void main(String[] args) {
        Admin admin = new Admin();
        admin.createRide("Delhi", "Agra",500);
        admin.createRide("Jalandhar", "Delhi",450);
        admin.createRide("Jalandhar", "Agra",550);
        admin.displayRide();
//        System.out.println(admin.getAllCities());
//        System.out.println(admin.getAllSource("Agra"));
//        System.out.println(admin.getAllDestination("Jalandhar"));
//        admin.deleteRide("Delhi","Agra",500);
        admin.updateSource("Delhi","Agra",500,"Bhopal");
        admin.updateDestination("Jalandhar","Delhi",450,"Jaipur");
        admin.updateFare("Jalandhar","Agra",550,600);
        admin.displayRide();
    }

}