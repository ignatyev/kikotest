package ru.aignatiev.kiko.data;

public class Booking {
    private int hour;
    private int slot;

    public Booking(int hour, int slot) {
        this.hour = hour;
        this.slot = slot;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Booking booking = (Booking) o;

        if (hour != booking.hour) return false;
        return this.slot == booking.slot;
    }

    @Override
    public int hashCode() {
        int result = hour;
        result = 31 * result + slot;
        return result;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "hour=" + hour +
                ", slot=" + slot +
                '}';
    }
}
