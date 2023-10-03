package org.example.Coffee_Shop;


import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.*;
import org.hibernate.annotations.Generated;

@Entity
@Table(name="OrderEvent")

public class OrderEvent {


    @Id
    @Column(name="register_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int registerId;

    @Column(name="id")
    private int id;

    public OrderEvent(){}


    @Column(name="register_time")
    @Temporal(TemporalType.TIMESTAMP)
    LocalDateTime localDateTime = LocalDateTime.now();


    @Column(name="expected_time")
    @Temporal(TemporalType.TIMESTAMP)
        LocalDateTime expectedLocalTime = LocalDateTime.now().plusMinutes(10);




    @Column(name="order_Id")
    private int orderId;

    @Column(name="client_Id")
    private int clientId;

    @Column(name="employee_Id")
    private int employeeId;


    @Column(name="product_Id")
    private int productId;



    @Column(name="product_Price")
    private double productPrice;


    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    @Enumerated(EnumType.STRING)
    private EventType eventType;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public OrderEvent(int id, int employeeId, String cancelReason, EventType eventType) {
        this.id = id;
        this.employeeId = employeeId;
        //this.cancelReason = cancelReason;
        this.eventType = eventType;
    }

    public OrderEvent(int id, int clientId, int employeeId, int productId, double productPrice, EventType eventType) {
        this.id = id;
        this.clientId = clientId;
        this.employeeId = employeeId;
        this.productId = productId;
        this.productPrice = productPrice;
        this.eventType = eventType;
    }

    public OrderEvent(int id, int employeeId, EventType eventType) {
        this.id = id;
        this.employeeId = employeeId;
        this.eventType = eventType;
    }


    @Override
    public String toString() {
        return "OrderEvent{" +
                "id=" + id +
                ", localDateTime=" + localDateTime +
                ", eventType=" + eventType +
                '}';
    }
}

