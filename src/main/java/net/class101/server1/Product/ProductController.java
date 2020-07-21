package net.class101.server1.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(produces = MediaTypes.HAL_JSON_VALUE)
public class ProductController {

    @Autowired
    ProductServiceImpl productServiceImpl;

    @GetMapping("/getProductList")
    public List<Product> getProductList() {
        return productServiceImpl.productList();
    }
}
