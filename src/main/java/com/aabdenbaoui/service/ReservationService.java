package com.aabdenbaoui.service;

import com.aabdenbaoui.model.Customer;
import com.aabdenbaoui.model.IRoom;
import com.aabdenbaoui.model.Reservation;

import javax.naming.spi.ResolveResult;
import java.time.LocalDate;
import java.util.*;

public class ReservationService {

     private static HashMap<String, IRoom> rooms = new HashMap<>();
     private static HashMap<Customer, Collection<Reservation>> customerReservations = new HashMap<>();
     private static Collection<Reservation> reservationCollections = new ArrayList<>();

     public static void addRoom(IRoom room){
          rooms.put(room.getRoomNumber(), room);
     }
     public static IRoom getARoom(String roomId){
          return rooms.get(roomId);
     }

     public static Collection<IRoom> getAllRooms() {
          return rooms.values();
     }
     public static Reservation reserveARoom(Customer customer, IRoom room, LocalDate checkInDate, LocalDate checkOutDate){
          Reservation reservation =  new Reservation(customer, room, checkInDate, checkOutDate);
          if( customerReservations.get(customer) == null){
               customerReservations.put(customer, new ArrayList<>());
               customerReservations.get(customer).add(reservation);
          }else{
               customerReservations.get(customer).add(reservation);
          }
          room.setFree(false);
          return  reservation;
     }

     public static Collection<Reservation> getCustomerReservations(Customer customer) {
          return customerReservations.get(customer);
     }
     public static void printAllReservation(){

          for(Customer customer : customerReservations.keySet()){
                  for(Reservation reservation : customerReservations.get(customer)){
                       reservationCollections.add(reservation);
                       System.out.println(reservation);
                  }
          }
     }
     private static void  addReserveationsToCollection(){
          for(Customer customer : customerReservations.keySet()){
               for(Reservation reservation : customerReservations.get(customer)){
                    reservationCollections.add(reservation);
               }
          }
     }
     public static Collection<Reservation> getReservationCollections() {
          addReserveationsToCollection();
          return reservationCollections;
     }
}
