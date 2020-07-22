package net.class101.server1.Product;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name = "Product")
public class Product {

    @Id
    private Integer id;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<ProductEnum> category;

    private String productName;

    @Min(1)
    private Integer price;

    @Min(0)
    private Integer stock;

    public void stockUpdate(Integer discountStock) {
        this.stock = this.stock - discountStock;
    }
}
