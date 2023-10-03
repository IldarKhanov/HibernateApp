package org.example.Coffee_Shop;

interface OrderService {
    public void publishEvent(OrderEvent event);
    Order findOrder(int id);
}