package net.class101.server1.Product;

import org.springframework.http.ResponseEntity;

public interface ProductService {
    ResponseEntity productList();
    ResponseEntity orderProduct(ProductOrderDto productOrderDto);
}
