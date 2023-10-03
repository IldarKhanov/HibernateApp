package org.example.Coffee_Shop;

import jdk.jfr.Event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Order {


    private int id;
    private EventType status;

    private List<OrderEvent> events;

    public List<OrderEvent> getEvents() {
        return events;
    }

    public void setEvents(List<OrderEvent> orderEvents) {
        this.events = orderEvents;
    }

    public Order(int id, EventType status) {
        this.id = id;
        this.status = status;
    }

    public Order(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EventType getStatus() {
        return status;
    }

    public void setStatus(EventType status) {
        this.status = status;
    }

    public String toString() {
        return "Order{" +
                "status='" + status + '\'' +
                ", id=" + id +
                ", events=" + events +
                '}';
    }
}
