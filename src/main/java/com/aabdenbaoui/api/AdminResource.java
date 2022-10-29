package com.aabdenbaoui.api;

import com.aabdenbaoui.model.Customer;
import com.aabdenbaoui.model.IRoom;
import com.aabdenbaoui.service.CustomerService;
import com.aabdenbaoui.service.ReservationService;

import java.util.Collection;
import java.util.List;

public class AdminResource {
    static ReservationService reservationService = ReservationService.getInstance();
    static CustomerService customerService = CustomerService.getInstance();
       public static Customer getCustomer(String email){
          return customerService.getCustomer(email);
       }
       public static void addRoom(List<IRoom> rooms){
           rooms.forEach(reservationService::addRoom);
       }
       public static Collection<IRoom> getRooms(){
           return reservationService.getAllRooms();
       }
       public static Collection<Customer> getAllCustomers(){
           return customerService.getCustomers();
       }
       public static void displayAllReservations(){
           reservationService.printAllReservation();
       }
}
