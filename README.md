# Flow Coding Test
- - - - - - 
##### 지원자 : 백엔드 개발자 모준서
- - - - - - 
### 사용 기술 스택
* Java 11
* Spring Boot 2.1.7RELEASE
* Spring JPA
* Spring HATEOAS
* H2 
* Lombok
* Maven

### Table schema
|변수|타입|설명|
|----|----|----|
|extensionName(PK)|String|확장자 명|
|useThis|Boolean|활성화 유무|

### 사전 작업
* 애플리케이션이 실행되면 기존 확장자가 DB에 저장되는 시나리오를 가진다.
* 기존 확장자는 bat, cmd, com, cpl, exe, scr, js 이다.

### 테스트
* Junit4를 이용하여 테스틀 진행한다. 
1. 확장자 조회 테스트.
2. 확장자 수정 테스트.
3. 확장자 수정에서 잘못된 값을 넘겼을 경우 테스트.

### 호출 주소
#### 확장자 조회 Request
````
curl --location --request GET 'http://localhost:8080/ext_select'
````
#### 확장자 조회 Response
````
{
    "extensions": [
        {
            "createdAt": "2020-07-15 23:29:59",
            "modifiedAt": "2020-07-15 23:29:59",
            "extensionName": "bat",
            "useThis": true
        },
        {
            "createdAt": "2020-07-15 23:29:59",
            "modifiedAt": "2020-07-15 23:29:59",
            "extensionName": "cmd",
            "useThis": true
        },
        {
            "createdAt": "2020-07-15 23:29:59",
            "modifiedAt": "2020-07-15 23:29:59",
            "extensionName": "com",
            "useThis": false
        },
        {
            "createdAt": "2020-07-15 23:29:59",
            "modifiedAt": "2020-07-15 23:29:59",
            "extensionName": "cpl",
            "useThis": false
        },
        {
            "createdAt": "2020-07-15 23:29:59",
            "modifiedAt": "2020-07-15 23:29:59",
            "extensionName": "exe",
            "useThis": false
        },
        {
            "createdAt": "2020-07-15 23:29:59",
            "modifiedAt": "2020-07-15 23:29:59",
            "extensionName": "scr",
            "useThis": false
        },
        {
            "createdAt": "2020-07-15 23:29:59",
            "modifiedAt": "2020-07-15 23:29:59",
            "extensionName": "js",
            "useThis": false
        }
    ],
    "_links": {
        "[GET]확장자 조회": {
            "href": "http://localhost:8080/ext_select"
        },
        "[PUT]확장자 수정": {
            "href": "http://localhost:8080/ext_update"
        },
        "self": {
            "href": "http://localhost:8080/ext_select"
        },
        "profile": {
            "href": "README.md"
        }
    }
}
````
### 확장자 수정 
#### 확장자 update value 명세
* update할 경우 'extensions' 이라는 이름으로 List에 담아보낸다.    
* List에는 Entity의 명세대로 값을 지정한다.   
* useThis는 기존 확장의 경우 checkbox 에 따라 true/false값, 새로운 확장자라면 무조건 true로 설정한다.

#### 확장자 수정 Request
````
curl --location --request PUT 'http://localhost:8080/ext_update' \
--header 'Content-Type: application/json' \
--data-raw '{
    "extensions": [
        {
            "extensionName": "bat",
            "useThis": true
        },
        {
            "extensionName": "test",
            "useThis": true
        }
    ]
}'
````
#### 확장자 수정 Response
````
{
    "extensions": [
        {
            "createdAt": "2020-07-15 23:29:59",
            "modifiedAt": "2020-07-15 23:29:59",
            "extensionName": "bat",
            "useThis": true
        },
        {
            "createdAt": "2020-07-15 23:29:59",
            "modifiedAt": "2020-07-15 23:29:59",
            "extensionName": "cmd",
            "useThis": true
        },
        {
            "createdAt": "2020-07-15 23:29:59",
            "modifiedAt": "2020-07-15 23:29:59",
            "extensionName": "com",
            "useThis": false
        },
        {
            "createdAt": "2020-07-15 23:29:59",
            "modifiedAt": "2020-07-15 23:29:59",
            "extensionName": "cpl",
            "useThis": false
        },
        {
            "createdAt": "2020-07-15 23:29:59",
            "modifiedAt": "2020-07-15 23:29:59",
            "extensionName": "exe",
            "useThis": false
        },
        {
            "createdAt": "2020-07-15 23:29:59",
            "modifiedAt": "2020-07-15 23:29:59",
            "extensionName": "scr",
            "useThis": false
        },
        {
            "createdAt": "2020-07-15 23:29:59",
            "modifiedAt": "2020-07-15 23:29:59",
            "extensionName": "js",
            "useThis": false
        },
        {
            "createdAt": "2020-07-15 23:30:06",
            "modifiedAt": "2020-07-15 23:30:06",
            "extensionName": "test",
            "useThis": true
        }
    ],
    "_links": {
        "[GET]확장자 조회": {
            "href": "http://localhost:8080/ext_select"
        },
        "[PUT]확장자 수정": {
            "href": "http://localhost:8080/ext_update"
        },
        "self": {
            "href": "http://localhost:8080/ext_update"
        },
        "profile": {
            "href": "README.md"
        }
    }
}
````
