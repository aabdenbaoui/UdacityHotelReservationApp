package com.aabdenbaoui.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FreeRoomTest {

    @Test
    public void testFreeRoom(){
        FreeRoom freeRoom = new FreeRoom("350",RoomType.SINGLE);



        assertEquals(0.0, freeRoom.getRoomPrice());

    }

}