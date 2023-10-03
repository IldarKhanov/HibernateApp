package org.example.Coffee_Shop;


public class Test {
    public static void main(String[] args) {

        OrderService orderService = new OrderEventsImp();
        OrderEvent orderEvent1 = new OrderEvent(6, 10, 1, 5, 10.4, EventType.REGISTERED);
        OrderEvent orderEvent2 = new OrderEvent(6, 10, 1, 5, 10.4, EventType.ACCEPTED);
        OrderEvent orderEvent3 = new OrderEvent(6, 10, 1, 5, 10.4, EventType.CANCELED);

        orderService.publishEvent(orderEvent1);
        orderService.publishEvent(orderEvent2);
        orderService.publishEvent(orderEvent3);
        orderService.findOrder(6);

    }
}
