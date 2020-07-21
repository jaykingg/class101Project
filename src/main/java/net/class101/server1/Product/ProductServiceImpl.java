package net.class101.server1.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> productList() {
        return productRepository.findAll();
    }

    @Override
    public ProductDto orderProduct(List<Product> orderList) {
        return null;
    }
}
