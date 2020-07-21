package net.class101.server1;

import net.class101.server1.Product.Product;
import net.class101.server1.Product.ProductController;
import net.class101.server1.Product.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class Server1Application {

    public static void main(String[] args) {
        SpringApplication.run(Server1Application.class, args);
        Scanner sc = new Scanner(System.in);
        RestTemplate restTemplate = new RestTemplate();
        String input = null;

        while (true) {
            System.out.println("입력(o[order]: 주문, q[quit]: 종료) : o + ENTER (입력) ");
            input = sc.nextLine();

            if(input.equals("o") || input.equals("order")) {
                String productList = restTemplate.getForObject("http://localhost:8080/getProductList",String.class);
                System.out.println(productList);

//                for(int i=0;i<productList.size();i++) {
//                    System.out.println(productList.get(i).getId() + " " + productList.get(i).getProductName() + "\t"
//                    + productList.get(i).getPrice() + "\t" + productList.get(i).getStock());
//                }

                String productNum = null;
                String productAmount = null;

                while(true) {
                    System.out.printf("상품번호 : ");
                    productNum = sc.nextLine();
                    if(productNum.equals("")) {
                        System.out.println("주문 내역:");
                        System.out.println("-------------------------------------");
                        // --> List 뿌려줌
                        System.out.println("-------------------------------------");
                        System.out.println("주문금액: ");
                        System.out.println("배송비: ");
                        System.out.println("-------------------------------------");
                        System.out.println("지불금액: ");
                        System.out.println("-------------------------------------");
                        break;
                    }
                    System.out.printf("수량 : ");
                    productAmount = sc.nextLine();
                    // --> List에 추가
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
