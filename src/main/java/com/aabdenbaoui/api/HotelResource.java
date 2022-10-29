package com.aabdenbaoui.api;

import com.aabdenbaoui.model.*;
import com.aabdenbaoui.service.CustomerService;
import com.aabdenbaoui.service.ReservationService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

public class HotelResource {

    static ReservationService reservationService = ReservationService.getInstance();
    static CustomerService customerService = CustomerService.getInstance();

    private static Collection<IRoom> availableRooms =  new HashSet<>();


    public static Customer getCustomer(String email) {
        return customerService.getCustomer(email);
    }

    public static void createCustomer(String email, String firstName, String lastName) {
        customerService.addCustomer(email, firstName, lastName);
    }

    public static IRoom getRoom(String roomNumber) {
        return reservationService.getARoom(roomNumber);
    }

    public static Reservation bookRoom(String customerEmail, IRoom room, LocalDate checkInDate, LocalDate checkOutDate) {
        if(availableRooms.contains(room)){
            return reservationService.reserveARoom(getCustomer(customerEmail), room, checkInDate, checkOutDate);
        }else{
            return null;
        }

    }

    public static Collection<Reservation> getCustomerReservations(String customerEmail) {
        return reservationService.getCustomerReservations(getCustomer(customerEmail));
    }

    public static Collection<IRoom> findARoom(LocalDate checkIn, LocalDate checkOut) {


//        Collection<IRoom> roomsFree = new ArrayList<>();
//        for (IRoom room : reservationService.getAllRooms()) {
//            availableRooms.add(room);
//        }
        getAvailableRooms();
        for (Reservation reservation : reservationService.getReservationCollections()) {
            if ((reservation.getCheckInDate().isBefore(checkIn) && reservation.getCheckOutDate().isAfter(checkIn))
                    || (reservation.getCheckInDate().isBefore(checkOut) && reservation.getCheckOutDate().isAfter(checkOut))
                    || (reservation.getCheckInDate().isAfter(checkIn) && reservation.getCheckOutDate().isBefore(checkOut))
                    || (reservation.getCheckOutDate().equals(checkOut) && reservation.getCheckInDate().equals(checkIn))
                    || (reservation.getCheckOutDate().equals(checkOut) && (reservation.getCheckInDate().isBefore(checkIn)) || (reservation.getCheckInDate().isAfter(checkIn)))
                    || (reservation.getCheckInDate().equals(checkIn) && (reservation.getCheckOutDate().isBefore(checkOut)) || (reservation.getCheckOutDate().isAfter(checkOut)))) {
                availableRooms.remove(reservation.getRoom());
            }
        }
//
//        System.out.println(availableRooms);
        return availableRooms;
    }

    public static Collection<IRoom> getAvailableRooms() {
        for (IRoom room : reservationService.getAllRooms()) {
            availableRooms.add(room);
        }
        return availableRooms;
    }
}


