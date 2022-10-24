package com.aabdenbaoui.service;

import com.aabdenbaoui.model.*;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;


class ReservationClassTest {

       @Test
       public void testResevationService() throws ParseException {
              String sDate01="10/09/2022";
              String sDate02="10/19/2022";

              DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

              LocalDate date01 = LocalDate.parse(sDate01, formatter);
              LocalDate date02 = LocalDate.parse(sDate02, formatter);
              System.out.println(date02);
              IRoom roomTest = new Room("315", 15.5, RoomType.SINGLE);
              IRoom roomTest01 = new Room("316", 15.5, RoomType.SINGLE);
              IRoom room318 = new Room("318", 15.5, RoomType.SINGLE);



              Customer customer = new Customer("Aniss", "Abdenbaoui", "aabdenb@gmail.com");
              ReservationService.addRoom(roomTest);
              //test the add room
              assertEquals(1, ReservationService.getAllRooms().size());
              assertNotEquals(2, ReservationService.getAllRooms().size());
              assertEquals(roomTest,ReservationService.getARoom("315"));
              assertNotEquals(roomTest, ReservationService.getARoom("316"));
              //test a reserve room
              assertNotNull(customer);
              assertNotNull(date01);
              assertNotNull(date02);
//              assertNotNull(ReservationService.reserveARoom(customer, roomTest,date01, date02));
              ReservationService.reserveARoom(customer, roomTest,date01, date02);
              assertEquals(1,  ReservationService.getCustomerReservations(customer).size());
              ReservationService.reserveARoom(customer, roomTest01,date01, date02);
              assertEquals(2,  ReservationService.getCustomerReservations(customer).size());
              //test if room is free
              assertEquals(false, roomTest.isFree());
              assertEquals(true, room318.isFree() );
       }


}