package org.example.Coffee_Shop;


import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class OrderEventsImp implements OrderService {

    public void publishEvent(OrderEvent orderEvent) {
        Configuration configuration = new Configuration().addAnnotatedClass(OrderEvent.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            int id = orderEvent.getId();

            Order existingOrder = findOrder(id);
            if (existingOrder != null && (existingOrder.getStatus() == EventType.CANCELED || existingOrder.getStatus() == EventType.ISSUED)) {
                throw new IllegalStateException("Cannot publish events for an issued or canceled order.");
            }
            if (orderEvent.getEventType() != EventType.REGISTERED) {
                List<OrderEvent> orderEvents = getOrderEvents(id);
                boolean isOrderRegistered = orderEvents.stream()
                        .anyMatch(e -> e.getEventType() == EventType.REGISTERED);
                if (!isOrderRegistered) {
                    throw new IllegalStateException("Order registration event must precede other events.");
                }
            }
            session.beginTransaction();
            session.persist(orderEvent);

            session.getTransaction().commit();

        } finally {
            sessionFactory.close();
        }
    }


    public Order findOrder(int id){
        List<OrderEvent> orderEvents = getOrderEvents(id);
        Order order = new Order();
        order.setId(id);
        order.setEvents(orderEvents);
        if (orderEvents.isEmpty()) {
            order.setStatus(EventType.NOT_REGISTERED);
        } else {
            OrderEvent lastEvent = orderEvents.get(orderEvents.size() - 1);
            order.setStatus(lastEvent.getEventType());
        }
        return order;
    }


    public static List<OrderEvent> getOrderEvents(int id) {
        Configuration configuration = new Configuration().addAnnotatedClass(OrderEvent.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();
            TypedQuery query = session.createQuery("FROM OrderEvent where id = :id");
            query.setParameter("id", id);
            List<OrderEvent> orderEvents = query.getResultList();

            session.getTransaction().commit();
            return orderEvents;

        } finally {
            sessionFactory.close();
        }

    }






}
