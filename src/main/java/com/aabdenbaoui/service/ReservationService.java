package com.aabdenbaoui.service;

import com.aabdenbaoui.model.Customer;
import com.aabdenbaoui.model.IRoom;
import com.aabdenbaoui.model.Reservation;

import java.time.LocalDate;
import java.util.*;

public class ReservationService {

     private static ReservationService reservationService = null;

     private ReservationService(){

     }
     public static ReservationService getInstance(){
          if (reservationService == null)
               reservationService = new ReservationService();

          return reservationService;
     }
     HashMap<String, IRoom> rooms = new HashMap<>();
     private  HashMap<Customer, Collection<Reservation>> customerReservations = new HashMap<>();
     private  Collection<Reservation> reservationCollections = new ArrayList<>();

     public  void addRoom(IRoom room){
          rooms.put(room.getRoomNumber(), room);
     }
     public  IRoom getARoom(String roomId){
          return rooms.get(roomId);
     }

     public  Collection<IRoom> getAllRooms() {
          return rooms.values();
     }
     public  Reservation reserveARoom(Customer customer, IRoom room, LocalDate checkInDate, LocalDate checkOutDate){
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

     public  Collection<Reservation> getCustomerReservations(Customer customer) {
          return customerReservations.get(customer);
     }
     public  void printAllReservation(){

          for(Customer customer : customerReservations.keySet()){
                  for(Reservation reservation : customerReservations.get(customer)){
                       reservationCollections.add(reservation);
                       System.out.println(reservation);
                  }
          }
     }
     void addReservationToCollection(){
          for(Customer customer : customerReservations.keySet()){
               for(Reservation reservation : customerReservations.get(customer)){
                    reservationCollections.add(reservation);
               }
          }
     }
      public Collection<Reservation> getReservationCollections() {
          addReservationToCollection();
          return reservationCollections;
     }

}
