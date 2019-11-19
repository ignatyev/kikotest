package ru.aignatiev.kiko.handler;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.StatusCodes;
import ru.aignatiev.kiko.data.Approve;
import ru.aignatiev.kiko.data.Booking;
import ru.aignatiev.kiko.data.BookingRequest;
import ru.aignatiev.kiko.data.Status;
import ru.aignatiev.kiko.service.NotifierService;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class BookingHandler implements HttpHandler {
    private final ConcurrentMap<Booking, Approve> bookings = new ConcurrentHashMap<>();
    public static final String BOOK = "/book";
    public static final String CANCEL = "/cancel";
    public static final String APPROVE = "/approve";
    public static final String REJECT = "/reject";
    private static final String CANCELED = "canceled";

    @Override
    public void handleRequest(HttpServerExchange exchange) throws IOException {
        BookingRequest bookingRequest = Utils.getBookingRequest(exchange);
        Optional<BookingRequest> result = Optional.empty();
        switch (exchange.getRelativePath()) {
            case BOOK:
                result = book(bookingRequest);
                break;
            case CANCEL:
                result = cancel(bookingRequest);
                break;
            case APPROVE:
                result = approve(bookingRequest);
                break;
            case REJECT:
                result = reject(bookingRequest);
                break;
            default:
        }
        if (!result.isPresent()) {
            exchange.setStatusCode(StatusCodes.BAD_REQUEST);
        } else {
            exchange.getResponseSender().send(result.get().toString());
        }

    }

    Optional<BookingRequest> book(BookingRequest bookingRequest) {
        Approve previous = bookings.putIfAbsent(
                bookingRequest.getBooking(),
                new Approve(bookingRequest.getUser(), Status.RESERVED));
        if (previous == null) {
            NotifierService.notifyCurrentTenant(bookingRequest.getBooking(), bookingRequest.getUser(), Status.RESERVED.name());
            return Optional.of(bookingRequest);
        } else {
            return Optional.empty();
        }
    }

    Optional<BookingRequest> cancel(BookingRequest bookingRequest) {
        Approve canceled = bookings.computeIfPresent(
                bookingRequest.getBooking(),
                (booking, approve) -> {
                    if (approve.getUser().equals(bookingRequest.getUser())) {
                        NotifierService.notifyCurrentTenant(bookingRequest.getBooking(), bookingRequest.getUser(), CANCELED);
                        return null;
                    }
                    return approve;
                });
        if (canceled == null) {
            return Optional.of(bookingRequest);
        } else {
            return Optional.empty();
        }
    }

    Optional<BookingRequest> reject(BookingRequest bookingRequest) {
        return approveOrReject(bookingRequest, Status.REJECTED);
    }

    Optional<BookingRequest> approve(BookingRequest bookingRequest) {
        return approveOrReject(bookingRequest, Status.APPROVED);
    }

    private Optional<BookingRequest> approveOrReject(BookingRequest bookingRequest, Status status) {
        Approve rejected = bookings.computeIfPresent(
                bookingRequest.getBooking(),
                (booking, approve) -> {
                    if (approve.getUser().equals(bookingRequest.getUser())) {
                        NotifierService.notifyNewTenant(bookingRequest.getBooking(), bookingRequest.getUser(), status.name());
                        return new Approve(bookingRequest.getUser(), status);
                    }
                    return approve;
                });
        if (rejected != null && rejected.getStatus() == status) {
            return Optional.of(bookingRequest);
        } else {
            return Optional.empty();
        }
    }

}
