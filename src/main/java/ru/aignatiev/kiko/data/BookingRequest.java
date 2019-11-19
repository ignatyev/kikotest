package ru.aignatiev.kiko.data;

import java.util.Objects;

public class BookingRequest {
    private Booking booking;
    private String user;

    public BookingRequest(Booking booking, String user) {
        this.booking = booking;
        this.user = user;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookingRequest that = (BookingRequest) o;

        if (!Objects.equals(booking, that.booking)) return false;
        return Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        int result = booking != null ? booking.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BookingRequest{" +
                "booking=" + booking +
                ", user='" + user + '\'' +
                '}';
    }
}
