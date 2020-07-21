package net.class101.server1.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(produces = MediaTypes.HAL_JSON_VALUE)
public class ProductController {

    @Autowired
    ProductServiceImpl productServiceImpl;

    @GetMapping("/getProductList")
    public ResponseEntity getProductList() {
        return productServiceImpl.productList();
    }

    @PostMapping("/postOrderList")
    public ResponseEntity postOrderList(@RequestBody ProductOrderDto productOrderDto) {
        return productServiceImpl.orderProduct(productOrderDto);
    }
}
