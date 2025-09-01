package main.java.com.example.order.controller;

import com.example.order.client.RestaurantClient;
import com.example.order.dto.CreateOrderRequest;
import com.example.order.dto.MenuItemDto;
import com.example.order.model.Order;
import com.example.order.model.OrderItem;
import com.example.order.store.InMemoryOrders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
@CrossOrigin
public class OrderController {
    private final InMemoryOrders store;
    private final RestaurantClient client;

    public OrderController(InMemoryOrders store, RestaurantClient client) {
        this.store = store;
        this.client = client;
    }

    @PostMapping
    public Order create(@RequestBody CreateOrderRequest req) {
        if (req.getItems() == null || req.getItems().isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "items required");

        // 1) fetch menu items from restaurant-service
        List<String> ids = req.getItems().stream().map(CreateOrderRequest.Item::getMenuItemId).toList();
        Map<String, MenuItemDto> menu = client.fetchMenuItems(ids).stream()
                .collect(Collectors.toMap(MenuItemDto::getId, Function.identity()));

        // 2) validate & compute totals
        int subtotal = 0;
        List<OrderItem> orderItems = new ArrayList<>();
        for (var it : req.getItems()) {
            MenuItemDto mi = menu.get(it.getMenuItemId());
            if (mi == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid menuItemId: " + it.getMenuItemId());
            if (!mi.isAvailable()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "item unavailable: " + mi.getName());
            int qty = Math.max(1, it.getQty());
            int line = mi.getPriceCents() * qty;
            subtotal += line;
            orderItems.add(new OrderItem(mi.getId(), qty, mi.getPriceCents(), mi.getName()));
        }
        int tax = (int)Math.round(subtotal * 0.08);
        int total = subtotal + tax;

        // 3) create and store order (in-memory)
        String oid = UUID.randomUUID().toString();
        Order order = new Order(oid, orderItems, subtotal, tax, total, "CREATED");
        store.orders.put(oid, order);
        return order;
    }

    @GetMapping("/{id}")
    public Order get(@PathVariable String id) {
        Order o = store.orders.get(id);
        if (o == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "order not found");
        return o;
    }

    @GetMapping
    public List<Order> list() {
        return new ArrayList<>(store.orders.values());
    }
}
