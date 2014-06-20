package com.epam.am.entity;

public class Passenger {
    private long id;
    private String name;
    private String lastName;

    public Passenger(long id, String name, String lastName) {
        this.id = idCheck(id);
        this.name = name;
        this.lastName = lastName;
    }

    private long idCheck(long id) {
        return id > -1 ? id : -id;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
