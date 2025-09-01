package main.java.com.example.order.store;

import com.example.order.model.Order;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class InMemoryOrders {
    public final Map<String, Order> orders = new ConcurrentHashMap<>();
}

