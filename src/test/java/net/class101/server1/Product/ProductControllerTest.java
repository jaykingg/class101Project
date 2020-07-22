package net.class101.server1.Product;

import net.class101.server1.Common.BaseControllerTest;
import net.class101.server1.Common.SoldOutException;
import net.class101.server1.Common.TestDescription;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.util.NestedServletException;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProductControllerTest extends BaseControllerTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Test
    @TestDescription("상품목록을 가져오는 테스트")
    public void getProductListTest() throws Exception {
        this.mockMvc.perform(get("/getProductList"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].id").exists())
                .andExpect(jsonPath("[0].category").exists())
                .andExpect(jsonPath("[0].productName").exists())
                .andExpect(jsonPath("[0].price").exists())
                .andExpect(jsonPath("[0].stock").exists())
        ;
    }

    @Test
    @TestDescription("상품을 주문하는 테스트(단일 사용자)")
    public void postOrderListTest() throws Exception {
        //given
        // 97166 상품을 3개 주문한다. (키트)
        Map<String,Integer> orderList = new HashMap<>();
        orderList.put("97166",3);

        ProductOrderDto productOrderDto = ProductOrderDto.builder()
                .orderList(orderList)
                .build();

        //when&then
        this.mockMvc.perform(post("/postOrderList")
                .content(objectMapper.writeValueAsString(productOrderDto))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("payDetails").exists())
                .andExpect(jsonPath("orderAmount").exists())
                .andExpect(jsonPath("payAmount").exists())
        ;

    }

    @Test
    @TestDescription("상품을 동시에 여러명이 주문할 경우 테스트")
    public void postOrderListMultiTest() throws Exception {
        /** Spring boot 의 경우, DispatcherServlet에 의해 Multi-Threading을 지원함. **/
        /** 더불어 Database 격리 수준을 READ COMMITTED 로 설정하여 변경내용이 Commit 되어야
         * 다른 Transaction에서 조회가능하게 설정 **/

        // 97166 상품은 키트상품으로 최대 5개의 상품이 있다.
        // 1번 유저는 97166 상품을 3개 장바구니에 담는다.
        // 2번 유저는 97166 상품을 5개 장바구니에 담는다.
        // 2번 유저가 먼저 상품을 주문한다.
        // 1번 유저가 주문할 때 SoldOut Exception 발생한다.

        //given
        Map<String,Integer> orderListUser1 = new HashMap<>();
        orderListUser1.put("97166",3);

        ProductOrderDto productOrderDtoUser1 = ProductOrderDto.builder()
                .orderList(orderListUser1)
                .build();
        /***************************************************************/
        Map<String,Integer> orderListUser2 = new HashMap<>();
        orderListUser2.put("97166",5);

        ProductOrderDto productOrderDtoUser2 = ProductOrderDto.builder()
                .orderList(orderListUser2)
                .build();

        //when&then
        this.mockMvc.perform(post("/postOrderList")
                .content(objectMapper.writeValueAsString(productOrderDtoUser2))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
        ;

        /** SoldOutException 발생 **/
        this.mockMvc.perform(post("/postOrderList")
                .content(objectMapper.writeValueAsString(productOrderDtoUser1))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(jsonPath("$.message").value("SoldOutException"))
        ;
    }
}