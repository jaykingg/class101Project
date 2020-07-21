package net.class101.server1.Product;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "Product")
public class Product {

    @Id
    private Integer id;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<ProductEnum> category;

    private String productName;

    private Integer price;

    private Integer stock;
}
