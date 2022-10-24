package com.aabdenbaoui.api;

import com.aabdenbaoui.model.Customer;
import com.aabdenbaoui.model.IRoom;
import com.aabdenbaoui.model.Reservation;
import com.aabdenbaoui.model.Room;
import com.aabdenbaoui.service.CustomerService;
import com.aabdenbaoui.service.ReservationService;

import java.util.Collection;
import java.util.List;

public class AdminRessource {

       public static Customer getCustomer(String email){
          return CustomerService.getCustomer(email);
       }
       public static void addRoom(List<IRoom> rooms){
           rooms.forEach(ReservationService::addRoom);
       }
       public static Collection<IRoom> getRooms(){
           return ReservationService.getAllRooms();
       }
       public static Collection<Customer> getAllCustomers(){
           return CustomerService.getCustomers();
       }
       public static void displayAllReservations(){
           ReservationService.printAllReservation();
       }
}
