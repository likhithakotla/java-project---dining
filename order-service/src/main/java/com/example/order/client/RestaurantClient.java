package main.java.com.example.order.client;

import com.example.order.dto.MenuItemDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class RestaurantClient {
    private final RestTemplate rest;

    @Value("${restaurant.baseUrl:http://localhost:8081}")
    private String baseUrl;

    public RestaurantClient(RestTemplate rest) {
        this.rest = rest;
    }

    public List<MenuItemDto> fetchMenuItems(List<String> ids) {
        String joined = String.join(",", ids);
        String url = baseUrl + "/menu-items?ids=" + joined;
        ResponseEntity<MenuItemDto[]> resp = rest.getForEntity(url, MenuItemDto[].class);
        MenuItemDto[] body = resp.getBody();
        return body == null ? List.of() : Arrays.asList(body);
    }
}

