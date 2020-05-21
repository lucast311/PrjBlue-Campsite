package campground_data;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BusinessManager {

    private Owner currUser;
    private BookingHelper bookingHelper;
    private PlotHelper plotHelper;
    private OwnerHelper ownerHelper = new OwnerHelper();
    private GuestHelper guestHelper;
    private static ArrayList<Owner> ownerList = OwnerHelper.getOwnerList();

    private static Scanner obIn = new Scanner(System.in);

    private Booking searchbooking;

    public static void main(String[] args) {

        Owner harry = new Owner("harry", "louis", "Pa$$w0rd", "555-555-5555", "test@gmail.com", 3, true);
        ownerList.add(harry);
        LogIn();
        boolean quit = false;
        do{
            System.out.print("Actions:[L]ist Something, [A]dd Something, [Q]uit: ");
            switch (obIn.nextLine().toUpperCase()) {
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
    public static void LogIn()
    {
        boolean user = false;
        boolean pass = false;
        while (!user)
        {
            System.out.println("Enter your UserID");
            String userID = obIn.nextLine();
            if(validateId(userID))
            {
                user = true;
            }
            else
            {
                System.out.println("UserID not found");
            }
        }
        while (!pass)
        {
            System.out.println("Enter your password");
            String userPass = obIn.nextLine();
            if(validatePassword(userPass))
            {
                pass = true;
                System.out.println("Log In successful. Select from the following menu items");
                System.out.println();
            }
            else
            {
                System.out.println("Password incorrect, try again");
            }
        }
    }
    public void managebooking() {
    }
    public static boolean validateId(String userID)
    {
        for (Owner owner : ownerList)
        {
            if(owner.getUserId().compareTo(userID) == 0)
            {
                return true;
            }
        }
        return false;
    }

    public static boolean validatePassword(String password)
    {
        for (Owner owner : ownerList)
        {
            if(owner.getPassword().compareTo(password) == 0)
            {
                return true;
            }
        }
        return false;
    }

    public Booking cancelbooking(String phoneNum) {
        searchbooking = BookingHelper.search(phoneNum);
        //needs validation for error
        return BookingHelper.search(phoneNum);
    }

//    public static Object search(Object obVal)
//    {}

    public void cancelConfirm(String answer ) {
        if (answer.equals("y") || answer.equals("yes")) {
            //go to the next
            // if passed start date
            Date date1 = new Date();
            Date date2 = searchbooking.getStartDate();
            //Date does not work??????
            if (date2.getDate() - date1.getDate() > 0 ){
                if (date2.getMonth() - date1.getMonth() >= 0){
                    //move to refund confirm
                }else {
                    //cancel successful
                    bookingHelper.removeBooking(searchbooking);
                }

            }else{
                //cancel successful
                bookingHelper.removeBooking(searchbooking);
            }
        } else if (answer.equals("n") || answer.equals("no") || answer.equals("nope")) {
            //go back to current booking
            //move into mainconsole instead

        }
            //error message


    }

    public double refundconfirm(String answer) {
        //SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date1 = new Date();
        Date date2 = searchbooking.getEndDate();
        int price;
        price = (int) PlotHelper.searchPlot(searchbooking.getPlotID()).getPrice();

        int ratething = (int) (date1.getDate() - date2.getDate());
        ratething = ratething * price;

        if (answer.equals("y") || answer.equals("yes")) {
            return (searchbooking.getTotal() - ratething);
        } else if (answer.equals("n") || answer.equals("no") || answer.equals("nope")){
            return searchbooking.getTotal();
        }
            //error message
        return 0;
    }
}


