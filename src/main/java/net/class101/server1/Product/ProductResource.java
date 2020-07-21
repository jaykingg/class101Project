package net.class101.server1.Product;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

public class ProductResource extends Resource<Product> {
    /** HATEOAS를 위한 Resource, Console로 진행하기 때문에 Link 추가하지 않음. **/
    public ProductResource(Product content, Link... links) {
        super(content, links);
    }
}
