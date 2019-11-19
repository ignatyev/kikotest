package ru.aignatiev.kiko.service;

import ru.aignatiev.kiko.data.Booking;

public class NotifierService {

    public static void notifyCurrentTenant(Booking booking, String user, String message) {
        System.out.println(booking + " was " + message + " by " + user);
    }

    public static void notifyNewTenant(Booking booking, String user, String approve) {
        System.out.println(user + ", your " + booking + " was " + approve);
    }
}
