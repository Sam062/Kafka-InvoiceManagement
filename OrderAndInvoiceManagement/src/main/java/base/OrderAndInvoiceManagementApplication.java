package base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class OrderAndInvoiceManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderAndInvoiceManagementApplication.class, args);
    }

}
