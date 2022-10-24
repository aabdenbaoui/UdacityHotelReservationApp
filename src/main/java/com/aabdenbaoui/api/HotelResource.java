package com.aabdenbaoui.api;

import com.aabdenbaoui.model.*;
import com.aabdenbaoui.service.CustomerService;
import com.aabdenbaoui.service.ReservationService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class HotelResource {


    public static Customer getCustomer(String email){
        return CustomerService.getCustomer(email);
    }
    public static void createCustomer(String email, String firstName, String lastName){
         CustomerService.addCustomer(email,firstName,lastName);
    }
    public static IRoom getRoom(String roomNumber){
        return ReservationService.getARoom(roomNumber);
    }
    public static Reservation bookRoom(String customerEmail, IRoom room, LocalDate checkInDate, LocalDate checkOutDate){
        return ReservationService.reserveARoom(getCustomer(customerEmail), room, checkInDate, checkOutDate);
    }
    public static Collection<Reservation> getCustomerReservations(String customerEmail){
        return ReservationService.getCustomerReservations(getCustomer(customerEmail));
    }
    public static Collection<IRoom> findARoom(LocalDate checkIn, LocalDate checkOut){
        Collection<IRoom> roomsFree = new ArrayList<>();
         for(IRoom  room : ReservationService.getAllRooms()){
                    roomsFree.add(room);
         }
        for(Reservation reservation : ReservationService.getReservationCollections()){
            if((reservation.getCheckInDate().isBefore(checkIn) && reservation.getCheckOutDate().isAfter(checkIn))
                    || reservation.getCheckInDate().isBefore(checkOut) && reservation.getCheckOutDate().isAfter(checkOut)
                    || reservation.getCheckInDate().isAfter(checkIn) && reservation.getCheckOutDate().isBefore(checkOut)){
                roomsFree.remove(reservation.getRoom());
            }
        }
        return roomsFree;
    }
}
