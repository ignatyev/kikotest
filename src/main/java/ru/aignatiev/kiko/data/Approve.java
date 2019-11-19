package ru.aignatiev.kiko.data;

import java.util.Objects;

public class Approve {
    private String user;
    private Status status;

    public Approve(String user, Status status) {
        this.user = user;
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Approve approve = (Approve) o;
        return Objects.equals(user, approve.user) &&
                status == approve.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, status);
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
