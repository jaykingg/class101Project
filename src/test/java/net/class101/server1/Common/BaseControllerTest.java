package net.class101.server1.Common;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.class101.server1.Product.Product;
import net.class101.server1.Product.ProductEnum;
import net.class101.server1.Product.ProductRepository;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import java.util.Set;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Ignore
public class BaseControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected ModelMapper modelMapper;

    @Autowired
    protected ProductRepository productRepository;


    @ExceptionHandler(SoldOutException.class)
    public @ResponseBody
    AppError sampleError(SoldOutException e){
        AppError appError = new AppError();
        appError.setMessage("Sold Out");
        appError.setReason("재고가 모두 소진되었습니다.");
        return appError;
    }
    @Before
    public void createProducts() {
        Product initProduct1 = Product.builder()
                .id(16374)
                .category(Set.of(ProductEnum.categoryClass))
                .productName("스마트스토어로 월 100만원 만들기, 평범한 사람이 돈을 만드\n" +
                        "는 비법")
                .price(151950)
                .stock(99999)
                .build();
        productRepository.save(initProduct1);

        Product initProduct2 = Product.builder()
                .id(26825)
                .category(Set.of(ProductEnum.categoryClass))
                .productName("해금, 특별하고 아름다운 나만의 반려악기")
                .price(114500)
                .stock(99999)
                .build();
        productRepository.save(initProduct2);

        Product initProduct3 = Product.builder()
                .id(65625)
                .category(Set.of(ProductEnum.categoryClass))
                .productName("일상에 따뜻한 숨결을 불어넣어요, 반지수와 함께하는 아이패" +
                        "드 드로잉")
                .price(174500)
                .stock(99999)
                .build();
        productRepository.save(initProduct3);

        Product initProduct4 = Product.builder()
                .id(91008)
                .category(Set.of(ProductEnum.categoryKit))
                .productName("작고 쉽게 그려요 - 부담없이 시작하는 수채화 미니 키트")
                .price(28000)
                .stock(10)
                .build();
        productRepository.save(initProduct4);

        Product initProduct5 = Product.builder()
                .id(9236)
                .category(Set.of(ProductEnum.categoryKit))
                .productName("하루의 시작과 끝, 욕실의 포근함을 선사하는 천연 비누")
                .price(9900)
                .stock(22)
                .build();
        productRepository.save(initProduct5);

        Product initProduct6 = Product.builder()
                .id(55527)
                .category(Set.of(ProductEnum.categoryClass))
                .productName("코바늘로 인형을 만들자! 시은맘의 꼼지락 작업실")
                .price(299500)
                .stock(99999)
                .build();
        productRepository.save(initProduct6);

        Product initProduct7 = Product.builder()
                .id(2344)
                .category(Set.of(ProductEnum.categoryClass))
                .productName("일상 유튜버 슛뚜의 감성을 그대로. 영화같은 브이로그 제작법")
                .price(184500)
                .stock(99999)
                .build();
        productRepository.save(initProduct7);

        Product initProduct8 = Product.builder()
                .id(60538)
                .category(Set.of(ProductEnum.categoryKit))
                .productName("시작에 대한 부담을 덜다. 가격 절약 아이패드 특가전")
                .price(135800)
                .stock(7)
                .build();
        productRepository.save(initProduct8);

        Product initProduct9 = Product.builder()
                .id(78156)
                .category(Set.of(ProductEnum.categoryKit))
                .productName("일상을 따뜻하게 채우는 썬캐쳐와 무드등")
                .price(45000)
                .stock(16)
                .build();
        productRepository.save(initProduct9);

        Product initProduct10 = Product.builder()
                .id(54311)
                .category(Set.of(ProductEnum.categoryClass))
                .productName("여행 드로잉, 꿈만 꾸지 마세요. 핀든아트와 여행, 그리다")
                .price(249500)
                .stock(99999)
                .build();
        productRepository.save(initProduct10);

        Product initProduct11 = Product.builder()
                .id(73811)
                .category(Set.of(ProductEnum.categoryClass))
                .productName("사각사각 손글씨의 낭만, 펜크래프트의 한글 정자체 펜글씨")
                .price(209500)
                .stock(99999)
                .build();
        productRepository.save(initProduct11);

        Product initProduct12 = Product.builder()
                .id(97166)
                .category(Set.of(ProductEnum.categoryKit))
                .productName("이렇게 멋진 수채화 키트,어때요? 클래스101과 고넹이화방이" +
                        "기획한 3가지 수채화 키트")
                .price(96900)
                .stock(5)
                .build();
        productRepository.save(initProduct12);

        Product initProduct13 = Product.builder()
                .id(83791)
                .category(Set.of(ProductEnum.categoryClass))
                .productName("월급으로 만족하지 못하는 분들을 위한 아마존/이베이 입문")
                .price(178500)
                .stock(99999)
                .build();
        productRepository.save(initProduct13);

        Product initProduct14 = Product.builder()
                .id(53895)
                .category(Set.of(ProductEnum.categoryKit))
                .productName("선과 여백으로 채우는 2020년 캘린더와 엽서")
                .price(18620)
                .stock(31)
                .build();
        productRepository.save(initProduct14);

        Product initProduct15 = Product.builder()
                .id(39712)
                .category(Set.of(ProductEnum.categoryKit))
                .productName("집에서 느끼는 겨울의 묵직한 포근함, 플랜테리어 아이템")
                .price(17600)
                .stock(8)
                .build();
        productRepository.save(initProduct15);

        Product initProduct16 = Product.builder()
                .id(96558)
                .category(Set.of(ProductEnum.categoryClass))
                .productName("사진 입문자를 위한 쉽게 배우고 빨리 써먹는 사진과 라이트룸")
                .price(234500)
                .stock(99999)
                .build();
        productRepository.save(initProduct16);

        Product initProduct17 = Product.builder()
                .id(42031)
                .category(Set.of(ProductEnum.categoryKit))
                .productName("나만의 음악을 만들기 위한 장비 패키지")
                .price(209000)
                .stock(2)
                .build();
        productRepository.save(initProduct17);

        Product initProduct18 = Product.builder()
                .id(45947)
                .category(Set.of(ProductEnum.categoryClass))
                .productName("일러스트레이터 집시의 매력적인 얼굴 그리기")
                .price(249500)
                .stock(99999)
                .build();
        productRepository.save(initProduct18);

        Product initProduct19 = Product.builder()
                .id(74218)
                .category(Set.of(ProductEnum.categoryClass))
                .productName("나만의 문방구를 차려요! 그리지영의 타블렛으로 굿즈 만들기")
                .price(191600)
                .stock(99999)
                .build();
        productRepository.save(initProduct19);

        Product initProduct20 = Product.builder()
                .id(28448)
                .category(Set.of(ProductEnum.categoryClass))
                .productName("당신도 할 수 있다! 베테랑 실무자가 알려주는 모션그래픽의" +
                        "모든 것")
                .price(152200)
                .stock(99999)
                .build();
        productRepository.save(initProduct20);
    }
}
