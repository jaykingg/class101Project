package net.class101.server1.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductRepository productRepository;

    @Override
    public ResponseEntity productList() {
        /** Console 출력이므로 HATEOAS,Self-Descriptive-Msg 처리 안함. **/
        return ResponseEntity.ok(productRepository.findAll());
    }

    /** DB 고립 상태로 해당 트랜잭션 수행 중, 다른 트랜잭션이 끼어들지 못하게 함. **/
    /** 장바구니에 담고있는 중 누가 이미 사서 재고가 떨어졌다면, SoldOutException **/
    @Transactional(isolation = Isolation.DEFAULT)
    @Override
    public ResponseEntity orderProduct(ProductOrderDto productOrderDto) {
        /** Console 출력이므로 HATEOAS,Self-Descriptive-Msg 처리 안함. **/
        List<String> payDetails = new ArrayList<>();
        Integer orderAmount = 0;
        Integer payAmount = 0;

        /** 금액 계산 **/
        Map<String,Integer> orderList = productOrderDto.orderList;
        for(String key : orderList.keySet()) {
            /** id , count **/
            Integer convertKey = Integer.parseInt(key);
            Product getProduct = productRepository.findById(convertKey).orElseThrow(()->new IllegalArgumentException("값을 찾을 수 없습니다."));

            /** 장바구니에서 결제를 진행할 때, 다시 한번 체크하여 원하는 재고를 주문할 수 없을 경우 SOLD OUT 처리를 한다. **/
            if(getProduct.getStock() == 0 || getProduct.getStock() < orderList.get(key)) {
                throw new IllegalArgumentException("SOLD OUT");
            }
            /** 상품명 - 갯수 **/
            String productDetail = getProduct.getProductName() + " - " + orderList.get(key)+"개";
            payDetails.add(productDetail);

            /** 주문 금액 **/
            orderAmount += (getProduct.getPrice()*orderList.get(key));

            /** 재고 차감 **/
            /** 키트인 경우에만 차감한다. **/
            if(getProduct.getCategory().equals(Set.of(ProductEnum.categoryKit))) {
                getProduct.setStock(getProduct.getStock()-orderList.get(key));
            }
            productRepository.save(getProduct);

        }

        payAmount = orderAmount;

        if(orderAmount < 50000) {
            payAmount+= 5000;
        }

        ProductPayDto productPayDto = ProductPayDto.builder()
                .payDetails(payDetails)
                .orderAmount(orderAmount)
                .payAmount(payAmount)
                .build();

        return ResponseEntity.ok(productPayDto);
    }
}
