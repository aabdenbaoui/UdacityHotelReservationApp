package com.aabdenbaoui;

import com.aabdenbaoui.api.AdminRessource;
import com.aabdenbaoui.api.HotelResource;
import com.aabdenbaoui.model.Customer;
import com.aabdenbaoui.model.IRoom;
import com.aabdenbaoui.model.Room;
import com.aabdenbaoui.model.RoomType;
import com.aabdenbaoui.service.CustomerService;
import com.aabdenbaoui.service.ReservationService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static java.util.stream.Collectors.toCollection;

public class AdminMenu {

    public  static  void adminMenuDisplay() {
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            System.out.println("welcome Admin");
            System.out.println("Please enter a choice from the number below");
            System.out.println("1. See all customers");
            System.out.println("2. See all rooms");
            System.out.println("3. see all Reservations");
            System.out.println("4. Add a room");
            System.out.println("5. Back to Main Menu");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    printAllCustomers();
                    break;
                case "2":
                    printAllRooms();
                    break;
                case "3":
                    AdminRessource.displayAllReservations();
                    break;
                case "4":
                    addARoom();
//                    flag = false;
                    break;
                case "5":
                    MainMenu.handleChoices();
                    break;
                default:
                    System.out.println("wrong input");
                    adminMenuDisplay();
            }
        }
    }
    private static void printAllRooms(){
        if(AdminRessource.getRooms().size() == 0){
            System.out.println("No room in the database");
        }
        for(IRoom room : AdminRessource.getRooms()){
            System.out.println(room);
        }
    }
    private static void printAllCustomers(){
        if(AdminRessource.getAllCustomers().size() == 0){
            System.out.println("No customer in the database");
        }
        for(Customer customer: AdminRessource.getAllCustomers()){
            System.out.println(customer);
        }
    }
    private static void addARoom() {
        Scanner sc = new Scanner(System.in);
        Scanner sc01 = new Scanner(System.in);
        System.out.println("Please enter a room number between 100 and 200");
        String roomNumberStr = sc.nextLine();
        while(true){
            if(isNumeric(roomNumberStr)) {
                if ((Integer.valueOf(roomNumberStr) >= 100 && Integer.valueOf(roomNumberStr) <= 200)) {
                     break;
                }else{
                    System.out.println("Invalid input\n Type the integer-type number: and has to be between 100 and 200: ");
                    roomNumberStr = sc.nextLine();
                }
            }else{
                System.out.println("Invalid input\n Type the integer-type number: and has to be between 100 and 200: ");
                roomNumberStr = sc.nextLine();
            }

        }
        System.out.println("Please enter the price");

        while (!sc.hasNextDouble())
        {
            System.out.println("Invalid input\n Type the double-type number:");
            sc.next();
        }
        double price = sc.nextDouble();
        System.out.println(price);
        System.out.println("break");
        RoomType roomType = RoomType.SINGLE;
        boolean flag = true;
        while(flag) {
            System.out.println("Please enter the room type Singe/Double");
            String roomTypeInput = sc01.nextLine();
            System.out.println("after input");
            switch (roomTypeInput.toLowerCase()) {
                case "single":
                    roomType = RoomType.SINGLE;
                    flag = false;
                    break;
                case "double":
                    roomType = RoomType.DOUBLE;
                    flag = false;
                    break;
                default:
                    System.out.println("wrong room type");
                    break;
            }
        }
        IRoom room = new Room(roomNumberStr, price, roomType);
        AdminRessource.addRoom(Collections.singletonList(room));
        System.out.println(room + " has been added");
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
