package ru.aignatiev.kiko.handler;

import io.undertow.server.HttpServerExchange;
import ru.aignatiev.kiko.data.Booking;
import ru.aignatiev.kiko.data.BookingRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Utils {

    static BookingRequest getBookingRequest(HttpServerExchange exchange) throws IOException {
        BookingRequest bookingRequest;
        try (BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(exchange.getInputStream(), StandardCharsets.UTF_8))) {
            String[] lines = bufferedReader.lines().toArray(String[]::new);
            int hour = Integer.parseInt(lines[0]);
            int slot = Integer.parseInt(lines[1]);
            Booking booking = new Booking(hour, slot);
            String user = lines[2];
            bookingRequest = new BookingRequest(booking, user);
        }
        return bookingRequest;
    }
}
