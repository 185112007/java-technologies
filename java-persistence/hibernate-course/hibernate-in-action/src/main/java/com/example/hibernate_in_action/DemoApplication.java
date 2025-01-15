package com.example.hibernate_in_action;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Formula;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
@Slf4j
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(final CustomerRepository repository) {
        return args -> {
            Customer customer = new Customer();
            customer.setName("John");
            customer.setType(CustomerType.ELITE);
            customer.setMetadata(Metadata.builder()
                    .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                    .updatedAt(Timestamp.valueOf(LocalDateTime.now()))
                    .build());

            Product p1 = new Product();
            p1.setTitle("iphone");
            p1.setPrice(BigDecimal.valueOf(16.17));
            p1.setMetadata(Metadata.builder()
                    .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                    .updatedAt(Timestamp.valueOf(LocalDateTime.now()))
                    .build());

            Product p2 = new Product();
            p2.setTitle("android");
            p2.setPrice(BigDecimal.valueOf(15.14));
            p2.setMetadata(Metadata.builder()
                    .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                    .updatedAt(Timestamp.valueOf(LocalDateTime.now()))
                    .build());

            customer.setProducts(List.of(p1, p2));

            repository.save(customer);

            List<Customer> all = repository.findAll();
            log.error(String.valueOf(all));
        };
    }
}

interface CustomerRepository extends JpaRepository<Customer, Long> {
}

@Data
@Entity
class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @Enumerated(EnumType.STRING)
    private CustomerType type;

    private Metadata metadata;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Product> products;
}

@Data
@Entity
class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private BigDecimal price;

    @Formula("price * 0.18")
    private BigDecimal tax;

    private Metadata metadata;
}

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class Metadata {
    private Timestamp createdAt;
    private Timestamp updatedAt;
}

enum CustomerType {
    ELITE, SUPER, NORMAL
}
