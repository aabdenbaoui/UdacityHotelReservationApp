package com.aabdenbaoui;

import com.aabdenbaoui.api.HotelResource;
import com.aabdenbaoui.model.IRoom;
import com.aabdenbaoui.model.Reservation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class MainMenu {

    public static  void main(String[] args){
              handleChoices();
    }

    public static void handleChoices(){
        Scanner scanner = new Scanner(System.in);
        String choice ;
        boolean flag = true;
        while(flag) {
            System.out.println("Please enter a choice from the number below");
            System.out.println("1. Find and reserve a room");
            System.out.println("2. See my reservations");
            System.out.println("3. create an account");
            System.out.println("4. Admin");
            System.out.println("5. Exit");
            System.out.print("Enter the choice: ");
            choice = scanner.nextLine();

            if(choice.equals("5")){
                break;
            }
            displayChoices(choice);
        }
        System.out.println("Thank you for using my hotel application");
    }
    private static void displayChoices(String choice){
        Scanner sc = new Scanner(System.in);
        switch (choice) {
            case "1":
                reserveARRoom();
                break;
            case "2":
                String email = getValidateCustomerEmail(sc);
                printCustomerReservation(email);
                break;
            case "3":
                createAccount();
                break;
            case "4":
                AdminMenu.adminMenuDisplay();
                break;
            default:
                System.out.println("wrong choice");
        }
    }
    private static void printCustomerReservation(String email){
        if(HotelResource.getCustomerReservations(email) == null){
            System.out.println("No reservation was found for the provided email");
        }else{
            for(Reservation reservation : HotelResource.getCustomerReservations(email)){
                System.out.println(reservation);
            }
        }
    }
    private static void createAccount(){
        Scanner scanner = new Scanner(System.in);
        String email = getValidateCustomerEmail(scanner);
        System.out.println("Please enter your first name:");
        String firstName = scanner.nextLine();
        System.out.println("Please enter your last name:");
        String lastName = scanner.nextLine();
        HotelResource.createCustomer(email,firstName, lastName);
        System.out.println("The account has been succesful");
    }
    public static String getValidateCustomerEmail(Scanner sc){
        System.out.println("Enter your email: eg. name@domain.com:");
        String email = sc.nextLine();
        String regexPattern =  "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        while(!email.matches(regexPattern)){
            System.out.println("Error email");
            System.out.println("Enter your email: eg. name@domain.com");
            email = sc.nextLine();
        }
        return email;
    }
    private static void reserveARRoom(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a checkin date mm/dd/yy example 02/10/2020: ");
        String checkInInput = sc.nextLine();
        LocalDate checkInDate = LocalDate.parse(checkInInput, formatter);
        System.out.println("Enter a checkout date mm/dd/yy example 02/10/2020: ");
        String checkOutInput = sc.nextLine();
        LocalDate checkOutDate = LocalDate.parse(checkOutInput, formatter);
        while (HotelResource.findARoom(checkInDate,checkOutDate).size() == 0){
            checkInDate = checkInDate.plusDays(7);
            checkOutDate = checkOutDate.plusDays(7);
            if(HotelResource.findARoom(checkInDate,checkOutDate).size() == 0){
                System.out.println("No room is availabe in the range you provided and 7  days after your date you entered");
                System.out.println("Will exit the reservation and click on 1 to find and reserve a room");
                return;
            }
            System.out.println("No room is availabe in the range you provided");
            System.out.println("we may be interested in different set of dates: " +checkInDate + " - " + checkOutDate);
            System.out.println("Please click n to exit anything else will continue with reservation:");
            String exitContinue = sc.nextLine();
            if(exitContinue.equalsIgnoreCase("n")) {
                return;
            }else{
                break;
            }
//            return;
        }
        displayRooms(checkInDate, checkOutDate);
//        System.out.println("Would you like to reserve this room y/n:");
        System.out.println("Please enter a room from the above list:");
        String roomNumber = sc.nextLine();
        while(HotelResource.getRoom(roomNumber)  == null || HotelResource.getAvailableRooms().contains(roomNumber)){
            System.out.println("The room you entered is not in the database");
            System.out.println("Please enter a room from the above list or enter n to exit:");
             roomNumber = sc.nextLine();
            if(roomNumber.equalsIgnoreCase("n")) {
                return;
            }
        }
        System.out.println("Please info below to confirm the reservation");
        String customerEmail = getValidateCustomerEmail(sc);
        while(HotelResource.getCustomer(customerEmail) == null){
            System.out.println("Not such a customer in our database ");
            System.out.println("Do you  want to enter new email enter y or quit using n:");
            String continueOrQuit = sc.nextLine();
            while(true){
                if(continueOrQuit.equalsIgnoreCase("y")){
                    customerEmail = getValidateCustomerEmail(sc);
                    break;
                }else if(continueOrQuit.equalsIgnoreCase("n")){
                    return;
                }else{
                    System.out.println("wrong input: ");
                    System.out.println("Y to enter new email || N to quit: ");
                    continueOrQuit = sc.nextLine();
                }
            }

        }
        if(HotelResource.findARoom(checkInDate, checkOutDate).contains(roomNumber)){
            System.out.println("The room you enetered is not in the database");
        }else{
            HotelResource.bookRoom(customerEmail, HotelResource.getRoom(roomNumber), checkInDate,checkOutDate);
        }

    }
    public static void displayRooms(LocalDate checkInDate, LocalDate checkOutDate){
        for(IRoom room : HotelResource.findARoom(checkInDate,checkOutDate)) {
            System.out.println(room);
        }
    }

}
