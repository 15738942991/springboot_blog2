package com.zp.test;

public class Goschool {
    public static void main(String [] args) {
        Ticket piao = new Ticket();
        Bus bus = new Bus();
        System.out.println("---------------------------------");
        System.out.println(bus.run(piao));
    }
}
