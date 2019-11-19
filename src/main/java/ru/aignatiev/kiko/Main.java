package ru.aignatiev.kiko;

import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.handlers.BlockingHandler;
import ru.aignatiev.kiko.handler.BookingHandler;

public class Main {

    private static final String LOCALHOST = "localhost";
    private static final int PORT = 8080;

    public static void main(String[] args) {
        HttpHandler bookingHandler = new BookingHandler();
        Undertow server = Undertow.builder()
                .addHttpListener(PORT, LOCALHOST)
                .setHandler(Handlers.routing()
                        .post(BookingHandler.BOOK, new BlockingHandler(bookingHandler))
                        .delete(BookingHandler.CANCEL, new BlockingHandler(bookingHandler))
                        .put(BookingHandler.APPROVE, new BlockingHandler(bookingHandler))
                        .put(BookingHandler.REJECT, new BlockingHandler(bookingHandler)))
                .build();
        server.start();
    }
}
