package ru.polyakov;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.polyakov.service.OrderStatusService;

@SpringBootApplication
public class OrderStatusServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderStatusServiceApplication.class, args).getBean(OrderStatusService.class);
    }
}
