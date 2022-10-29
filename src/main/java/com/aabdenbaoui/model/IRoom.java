package com.aabdenbaoui.model;

public interface IRoom {

    String getRoomNumber();
    Double getRoomPrice();
     Double getPrice();

    public RoomType getRoomType();
    public boolean isFree();

    void setFree(boolean b);


}
