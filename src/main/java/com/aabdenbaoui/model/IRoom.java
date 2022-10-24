package com.aabdenbaoui.model;

public interface IRoom {

    public String getRoomNumber();
    public Double getRoomPrice();
    Double getPrice();

    public RoomType getRoomType();
    public boolean isFree();

    void setFree(boolean b);
}
