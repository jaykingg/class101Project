package net.class101.server1;

import net.class101.server1.Product.Product;
import net.class101.server1.Product.ProductOrderDto;
import net.class101.server1.Product.ProductPayDto;
import net.class101.server1.Product.ProductEnum;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

@SpringBootApplication
public class Server1Application {
    public static void main(String[] args) throws IOException {
        /** Main에서의 Console은 Web,App 등 Front를 대체하는 것으로 가정한다. **/

        SpringApplication.run(Server1Application.class, args);
        Scanner sc = new Scanner(System.in);
        RestTemplate restTemplate = new RestTemplate();
        DecimalFormat df = new DecimalFormat("#,###");
        String input = null;

        /** 장바구니 **/
        Map<String,Integer> orderList = new HashMap<>();

        while (true)  {
            /** 사용자 입력 **/
            System.out.println("입력(o[order]: 주문, q[quit]: 종료) : o + ENTER (입력) ");
            input = sc.nextLine();

            if(input.equals("o") || input.equals("order")) {
                /** [GET] getProductList , 통상 Client에서 받은 Json Data라고 가정한다. **/
                Product[] returnProducts = restTemplate.getForObject("http://localhost:8080/getProductList",Product[].class);
                List<Product> productList = Arrays.asList(returnProducts);

                /** Data print  **/
                System.out.println("상품번호\t\t|\t\t상품명\t|\t판매가격\t|\t재고수");
                for(int i=0;i<productList.size();i++) {
                    System.out.println(productList.get(i).getId() + " | " + productList.get(i).getProductName() + " | " + productList.get(i).getPrice() + " | "+ productList.get(i).getStock());
                }

                String productNum = null;
                String productAmount = null;

                while(true) {
                    System.out.printf("상품번호 : ");
                    productNum = sc.nextLine();

                    /** 주문 끝 **/
                    if(productNum.equals("")) {
                        /** [POST] postOrderList , 통상 Client에서 받은 Json Data라고 가정한다.**/
                        ProductOrderDto productOrderDto = ProductOrderDto.builder()
                                .orderList(orderList)
                                .build();
                        ProductPayDto productPayDto = restTemplate.postForObject("http://localhost:8080/postOrderList",productOrderDto, ProductPayDto.class);

                        /** 주문내역 출력 **/
                        System.out.println("주문 내역:");
                        System.out.println("-------------------------------------");
                        List<String> printOrder = productPayDto.getPayDetails();
                        for(int i=0;i<printOrder.size();i++) {
                            System.out.println(printOrder.get(i));
                        }
                        System.out.println("-------------------------------------");
                        System.out.println("주문금액: " + df.format(productPayDto.getOrderAmount())+"원");
                        if(productPayDto.getOrderAmount() < productPayDto.getPayAmount()) {
                            System.out.println("배송비: 5000원");
                        }
                        System.out.println("-------------------------------------");
                        System.out.println("지불금액: " + df.format(productPayDto.getPayAmount())+"원");
                        System.out.println("-------------------------------------");
                        break;
                    }

                    /** 상품번호 예외처리 **/
                    boolean inputIsInList = false;
                    for(int i=0;i<productList.size();i++) {
                        if(productList.get(i).getId().toString().equals(productNum)) {
                            inputIsInList = true;
                        }
                    }
                    if(!inputIsInList) {
                        System.out.println("잘못된 상품 번호를 입력하였습니다. 다시 입력해주세요.");
                        continue;
                    }

                    /** 클래스는 1개만 가능하다. **/
                    /** 키트는 복수개 가능하며, 최대 수량을 넘길 수 없다.**/
                    System.out.printf("수량 : ");
                    productAmount = sc.nextLine();
                    if(Integer.parseInt(productAmount) < 1) {
                        System.out.println("잘못된 수량 입력입니다. 다시 입력해주세요.");
                        continue;
                    }

                    /** 장바구니에 담기 전 최종 예외처리 **/
                    for(int i=0;i<productList.size();i++) {
                        if(productList.get(i).getId().toString().equals(productNum)) {
                            String mapKey = productList.get(i).getId().toString();
                            /** 해당 상품 재고가 0인 경우 **/
                            if(productList.get(i).getStock() == 0) {
                                System.out.println("해당 상품은 품절되었습니다.");
                                break;
                            }
                            /** 클래스 상품에서 수량이 1개 초과인 경우 **/
                            if(productList.get(i).getCategory().equals(Set.of(ProductEnum.categoryClass))
                            && Integer.parseInt(productAmount) > 1) {
                                System.out.println("클래스 상품은 1개만 입력가능합니다. 다시 입력해주세요.");
                                break;
                            }
                            /** 클래스 상품에서 이미 같은 클래스 상품이 추가된 경우 **/
                            else if(productList.get(i).getCategory().equals(Set.of(ProductEnum.categoryClass)) &&
                                    orderList.containsKey(mapKey)) {
                                System.out.println("이미 해당 클래스가 장바구니에 담겨있습니다.");
                                break;
                            }
                            /** 키트 상품에서 수량이 최대수량을 초과한 경우 **/
                            else if(productList.get(i).getCategory().equals(Set.of(ProductEnum.categoryKit)) &&
                                    Integer.parseInt(productAmount) > productList.get(i).getStock()) {
                                System.out.println("최대 수량을 넘겨서 입력할 수 없습니다. 다시 입력해주세요.");
                                break;
                            }
                            /** 키트 상품에서 수량의 합이 최대수량을 초과한 경우 **/
                            else if(productList.get(i).getCategory().equals(Set.of(ProductEnum.categoryKit)) &&
                                    orderList.containsKey(mapKey) &&
                                    orderList.get(mapKey)+Integer.parseInt(productAmount) > productList.get(i).getStock()) {
                                System.out.println("장바구니의 수량과 현재의 수량이 최대 수량을 넘길 수 없습니다. 다시 입력해주세요.");
                                break;
                            }
                            else {
                                if(orderList.containsKey(mapKey)) {
                                    orderList.replace(mapKey,orderList.get(mapKey)+Integer.parseInt(productAmount));
                                    System.out.println(productList.get(i).getId() + "상품을 장바구니에 넣었습니다.");
                                }
                                else {
                                    orderList.put(mapKey,Integer.parseInt(productAmount));
                                    System.out.println(productList.get(i).getId() + "상품을 장바구니에 넣었습니다.");
                                }
                            }
                        }
                    }
                }
            }
            else if(input.equals("q") || input.equals("quit")) {
                System.out.println("고객님의 주문 감사합니다.");
                sc.close();
                return;
            }
        }
    }
}
