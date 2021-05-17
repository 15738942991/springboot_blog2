package com.zp.test;

public class Bus<Ticket>{
    private String number;
    private int seat;

    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public int getSeat() {
        return seat;
    }
    public void setSeat(int seat) {
        this.seat = seat;
    }
    //方法
    public String run(Ticket ticket) {
        return "你到站了";
    }

    @Override
    public String toString() {
        return "Bus{" +
                "number='" + number + '\'' +
                ", seat=" + seat +
                '}';
    }
}

