package campground_data;

import java.util.Scanner;

public class BusinessManager {

    private Owner currUser;
    private BookingHelper bookingHelper;
//    private PlotHelper plotHelper;
    private OwnerHelper ownerHelper;
//    private GuestHelper guestHelper;

    private static Scanner read = new Scanner(System.in);

    public static void main(String[] args) {

        boolean quit = false;
        do{
            System.out.print("Actions:[L]ist Something, [A]dd Something, [Q]uit: ");
            switch (read.nextLine().toUpperCase()) {
                case "L":
//                    listSomething();
                    break;
                case "A":
//                    inputSomething();
                    break;
                default:
                    quit = true;
                    break;
            }
            System.out.println("");
            System.out.println("");
        } while (!quit);
        System.out.println("Quit Application");
    }

    public void LogIn()
    {}

//    public boolean validateId(String userID)
//    {}
//
//    public boolean validatePassword(String password)
//    {}
//
//    public Object search(Object obVal)
//    {}

}
