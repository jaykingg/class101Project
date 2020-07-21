package net.class101.server1.Product;

import java.util.List;

public interface ProductService {
    List<Product> productList();
    ProductDto orderProduct(List<Product> orderList);
}
