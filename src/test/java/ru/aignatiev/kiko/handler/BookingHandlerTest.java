package ru.aignatiev.kiko.handler;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.aignatiev.kiko.data.Booking;
import ru.aignatiev.kiko.data.BookingRequest;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;

public class BookingHandlerTest {

    private BookingHandler fixture;

    @Before
    public void setUp() throws Exception {
        fixture = new BookingHandler();
    }

    @Test
    public void book() {
        BookingRequest bookingRequest = getBookingRequest("user1");
        Optional<BookingRequest> booked = fixture.book(bookingRequest);
        Assert.assertThat(booked, is(Optional.of(bookingRequest)));
    }

    @Test
    public void bookNotPossible() {
        BookingRequest bookingRequest1 = getBookingRequest("user1");
        BookingRequest bookingRequest2 = getBookingRequest("user2");
        fixture.book(bookingRequest1);
        Optional<BookingRequest> booked = fixture.book(bookingRequest2);
        Assert.assertThat(booked, is(Optional.empty()));
    }

    @Test
    public void cancel() {
        BookingRequest bookingRequest = getBookingRequest("user1");
        fixture.book(bookingRequest);
        Optional<BookingRequest> cancel = fixture.cancel(bookingRequest);
        Assert.assertThat(cancel, is(Optional.of(bookingRequest)));
    }

    @Test
    public void reject() {
        BookingRequest bookingRequest = getBookingRequest("user1");
        fixture.book(bookingRequest);
        Optional<BookingRequest> reject = fixture.reject(bookingRequest);
        Assert.assertThat(reject, is(Optional.of(bookingRequest)));
    }

    @Test
    public void approve() {
        BookingRequest bookingRequest = getBookingRequest("user1");
        fixture.book(bookingRequest);
        Optional<BookingRequest> approve = fixture.approve(bookingRequest);
        Assert.assertThat(approve, is(Optional.of(bookingRequest)));
    }

    private BookingRequest getBookingRequest(String user1) {
        Booking booking = new Booking(10, 1);
        return new BookingRequest(booking, user1);
    }
}