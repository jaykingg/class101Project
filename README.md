# Class101 Coding Test
- - - - - - 
##### 지원자 : 백엔드 개발자 모준서
- - - - - - 
### 사용 기술 스택
* Java 11
* Spring Boot 2.1.7RELEASE
* Spring JPA
* H2 
* Lombok
* Maven

### HomeWork 분석 및 가정
* Console에서 시나리오가 작동된다.
> Console을 각 Front(Client)로 가정한다.   
> 이에 따라 Console에서는 Restemplte로 서버와 통신하는 방식으로 진행한다. 
> Console 출력이므로, HATEOAS와 Self-Descriptive-Msg는 생략한다.     
* 사용자들의 동시 접근에 대해 처리하기 위해 MultiThread 방식으로 처리한다.   
> Spring Boot Web Application은 Dispatcher에 의해 각 요청들을 MultiThread로 처리한다.   
> 더불어 Datebase 격리수준을 Read-Commit으로 설정하여 Transaction 수행 중, 다른 Transaction이 끼어들지 못하게 한다.
* 주문 도중 상품이 품절됐을경우 Custom Exception을 발생시킨다.
> RuntimeException을 상속하여 Custom Exception을 생성한다.   
> SoldOutException Handler를 생성하고, 해당 Exception을 Catch할 수 있게한다.   
* 사전작업
> 애플리케이션이 실행되면 보기에 주어진 상품리스트가 저장된다.


### Table schema
|변수|타입|설명|
|----|----|----|
|id(PK)|String|확장자 명|
|category|Enum|카테고리명|
|productName|String|상품이름|
|price|Integer|상품 가격|
|stock|Integer|상품 재고|


### 테스트
* Junit4를 이용하여 테스틀 진행한다. 
1. 상품리스트 조회 테스트.
2. 상품 주문 테스트.
3. 2명의 사용자가 주문할 경우 테스트.(SoldOutException)

### 호출 주소
#### 상품 리스트 조회 Request
````
curl --location --request GET 'http://localhost:8080/getProductList'
````
#### 상품 리스트 조회 Response
````
[
     {
         "id": 2344,
         "category": [
             "categoryClass"
         ],
         "productName": "일상 유튜버 슛뚜의 감성을 그대로. 영화같은 브이로그 제작법",
         "price": 184500,
         "stock": 99999
     },
     {
         "id": 9236,
         "category": [
             "categoryKit"
         ],
         "productName": "하루의 시작과 끝, 욕실의 포근함을 선사하는 천연 비누",
         "price": 9900,
         "stock": 22
     },
     {
         "id": 16374,
         "category": [
             "categoryClass"
         ],
         "productName": "스마트스토어로 월 100만원 만들기, 평범한 사람이 돈을 만드\n는 비법",
         "price": 151950,
         "stock": 99999
     },
     {
         "id": 26825,
         "category": [
             "categoryClass"
         ],
         "productName": "해금, 특별하고 아름다운 나만의 반려악기",
         "price": 114500,
         "stock": 99999
     },
     {
         "id": 28448,
         "category": [
             "categoryClass"
         ],
         "productName": "당신도 할 수 있다! 베테랑 실무자가 알려주는 모션그래픽의모든 것",
         "price": 152200,
         "stock": 99999
     },
     {
         "id": 39712,
         "category": [
             "categoryKit"
         ],
         "productName": "집에서 느끼는 겨울의 묵직한 포근함, 플랜테리어 아이템",
         "price": 17600,
         "stock": 8
     },
     {
         "id": 42031,
         "category": [
             "categoryKit"
         ],
         "productName": "나만의 음악을 만들기 위한 장비 패키지",
         "price": 209000,
         "stock": 2
     },
     {
         "id": 45947,
         "category": [
             "categoryClass"
         ],
         "productName": "일러스트레이터 집시의 매력적인 얼굴 그리기",
         "price": 249500,
         "stock": 99999
     },
     {
         "id": 53895,
         "category": [
             "categoryKit"
         ],
         "productName": "선과 여백으로 채우는 2020년 캘린더와 엽서",
         "price": 18620,
         "stock": 31
     },
     {
         "id": 54311,
         "category": [
             "categoryClass"
         ],
         "productName": "여행 드로잉, 꿈만 꾸지 마세요. 핀든아트와 여행, 그리다",
         "price": 249500,
         "stock": 99999
     },
     {
         "id": 55527,
         "category": [
             "categoryClass"
         ],
         "productName": "코바늘로 인형을 만들자! 시은맘의 꼼지락 작업실",
         "price": 299500,
         "stock": 99999
     },
     {
         "id": 60538,
         "category": [
             "categoryKit"
         ],
         "productName": "시작에 대한 부담을 덜다. 가격 절약 아이패드 특가전",
         "price": 135800,
         "stock": 7
     },
     {
         "id": 65625,
         "category": [
             "categoryClass"
         ],
         "productName": "일상에 따뜻한 숨결을 불어넣어요, 반지수와 함께하는 아이패드 드로잉",
         "price": 174500,
         "stock": 99999
     },
     {
         "id": 73811,
         "category": [
             "categoryClass"
         ],
         "productName": "사각사각 손글씨의 낭만, 펜크래프트의 한글 정자체 펜글씨",
         "price": 209500,
         "stock": 99999
     },
     {
         "id": 74218,
         "category": [
             "categoryClass"
         ],
         "productName": "나만의 문방구를 차려요! 그리지영의 타블렛으로 굿즈 만들기",
         "price": 191600,
         "stock": 99999
     },
     {
         "id": 78156,
         "category": [
             "categoryKit"
         ],
         "productName": "일상을 따뜻하게 채우는 썬캐쳐와 무드등",
         "price": 45000,
         "stock": 16
     },
     {
         "id": 83791,
         "category": [
             "categoryClass"
         ],
         "productName": "월급으로 만족하지 못하는 분들을 위한 아마존/이베이 입문",
         "price": 178500,
         "stock": 99999
     },
     {
         "id": 91008,
         "category": [
             "categoryKit"
         ],
         "productName": "작고 쉽게 그려요 - 부담없이 시작하는 수채화 미니 키트",
         "price": 28000,
         "stock": 10
     },
     {
         "id": 96558,
         "category": [
             "categoryClass"
         ],
         "productName": "사진 입문자를 위한 쉽게 배우고 빨리 써먹는 사진과 라이트룸",
         "price": 234500,
         "stock": 99999
     },
     {
         "id": 97166,
         "category": [
             "categoryKit"
         ],
         "productName": "이렇게 멋진 수채화 키트,어때요? 클래스101과 고넹이화방이기획한 3가지 수채화 키트",
         "price": 96900,
         "stock": 5
     }
 ]
````
#### 상품 주문 Request
````
curl --location --request POST 'http://localhost:8080/postOrderList' \
--header 'Content-Type: application/json' \
--data-raw '{"orderList":{"97166":3}}'
````
#### 상품 주문 Response
````
{
    "payDetails": [
        "이렇게 멋진 수채화 키트,어때요? 클래스101과 고넹이화방이기획한 3가지 수채화 키트 - 3개"
    ],
    "orderAmount": 290700,
    "payAmount": 290700
}
````
