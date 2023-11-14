
# 핵심기능
### `EventPlanner`
- 전체적인 흐름 담당하는 클래스
- [x] 입출력 요청
- [x] 주문 DTO 만드는 로직
- [ ] 주문정보를 각각의 혜택 로직에 보내는 로직

### `Menu` enum
- ["이름", 금액, `MenuType`]
- `MenuType` : 에피타이저, 메인, 음료, 디저트
- [x] 특정 메뉴를 입력하면 해당 메뉴가 존재하는 메뉴인지 찾는 로직
- [x] 특정 메뉴의 `MenuType`이 매개변수의 메뉴와 같은지 확인하는 로직

### `Discount` 인터페이스
- 할인에 대한 인터페이스
- `checkDiscount` 정의됨
- `BenefitResult` 반환
  - `BenefitResult` : {"혜택내용", "혜택가격"}

### `Benefit` enum
- ["혜택내용", "혜택양", `BenefitType`, (증정이벤트일시, 증정품목) `Menu`]
- `BenefitType` : 할인, 증정
- 각각의 상수마다 다른 혜택 로직 구현
  - [ ] 크리스마스 디데이 할인
  - [ ] 특별 할인
  - [ ] 평일 할인
  - [ ] 주말 할인
  - [ ] 증정 이벤트
    -  `FreeGift` class : [증정품목, 갯수]
    - 증정 이벤트의 경우, 상수에 `FreeGift`도 정의됨
    - [ ] (Menu.CHAMPAGNE, 1) 반환 기능

### `Badge` enum 
- [금액, "등급이름"]
- [ ] {별, 트리, 산타, 없음} 으로 구성하기

### `OrderDTO`
- 주문 정보를 혜택 객체에게 전달하기 위한 DTO

# 입출력
### inputView
- [ ] 날짜 정보를 `LocalDate`로 변환 기능
  - [ ] 날짜 정보 검증 로직
- [ ] MenuList `HashMap`으로 변환 기능
  - [ ] MenuList 검증 로직

### OutputView
- [ ] 파라미터 : `OrderDTO`, `List<BenefitResult>` 
- 혜택 전 상태 출력 로직
  - [ ] 메뉴리스트 출력
  - [ ] 혜택 전 총 금액 출력
- 혜택 후 상태 출력 로직
  - [ ] 증정 이벤트 출력
  - [ ] 혜택 상세 내역 출력
  - [ ] 총 혜택 금액 출력
  - [ ] 혜택 적용 후 금액 출력
  - [ ] 적절한 뱃지 출력