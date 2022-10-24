package com.aabdenbaoui.model;

public class FreeRoom extends Room{

    public FreeRoom(String roomNumber,RoomType roomType) {
        super(roomNumber, 0.0, roomType);
    }

    @Override
    public String getRoomNumber() {
        return super.getRoomNumber();
    }

    @Override
    public Double getRoomPrice() {
        return super.getRoomPrice();
    }

    @Override
    public RoomType getRoomType() {
        return super.getRoomType();
    }

    @Override
    public boolean isFree() {
        return super.isFree();
    }

    @Override
    public String toString() {
        return "FreeRoom{} " + super.toString();
    }
}
