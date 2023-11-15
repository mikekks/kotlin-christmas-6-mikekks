
# 핵심기능
### `EventPlanner`
- 전체적인 흐름 담당하는 클래스
- [x] 입출력 요청
- [x] 주문 DTO 만드는 로직
- [x] 팩토리 메서드 호출 로직

### `Menu` enum
- ["이름", 금액, `MenuType`]
- `MenuType` : 에피타이저, 메인, 음료, 디저트
- [x] 특정 메뉴를 입력하면 해당 메뉴가 존재하는 메뉴인지 찾는 로직
- [x] 특정 메뉴의 `MenuType`이 매개변수의 메뉴와 같은지 확인하는 로직

### `Discount` 인터페이스
- [x] 할인에 대한 인터페이스
- `checkDiscount` : 해당 정책을 사용해도 되는지 확인, 사용가능하면 해당 인터페이스 반환
- `getDiscountAmount`
  - 할인 내용과 할인 금액 반환
  - `BenefitResult` 반환 : {"혜택내용", "혜택가격"}

### `BenefitFactory`
- [x] AppConfig와 같이 할인 정책을 모두 저장하는 기능
- [x] 주문정보에 적용되는 할인 구현체들을 `List<Discount>`로 만드는 기능


### `Discount` 구현체
- 각각의 구현체마다 다른 혜택 로직 구현
  - [x] 크리스마스 디데이 할인
  - [x] 특별 할인
  - [x] 평일 할인 : 추가로 디저트 메뉴가 있는지도 확인
  - [x] 주말 할인 : 추가로 메인 메뉴가 있는지도 확인
  - [x] 증정 이벤트 : Present를 추가로 implements 한다.
    -  `getPresentAmount` : 증정 상품과 갯수를 반

### `Badge` enum 
- [금액, "등급이름"]
- [X] {별, 트리, 산타, 없음} 으로 구성하기

### `OrderDTO`
- 주문 정보를 혜택 구현체에게 전달하기 위한 DTO

# 입출력
### inputView
- [x] 날짜 정보를 `LocalDate`로 변환 기능
  - [x] 날짜 정보 검증 로직
- [x] MenuList `HashMap`으로 변환 기능
  - [x] MenuList 검증 로직

### OutputView
- [X] 파라미터 : `Order` 
- 혜택 전 상태 출력 로직
  - [x] 메뉴리스트 출력
  - [x] 혜택 전 총 금액 출력
- 혜택 후 상태 출력 로직
  - [x] 증정 이벤트 출력
  - [x] 혜택 상세 내역 출력
  - [x] 총 혜택 금액 출력
  - [x] 혜택 적용 후 금액 출력
  - [x] 적절한 뱃지 출력