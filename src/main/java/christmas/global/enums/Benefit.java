package christmas.global.enums;

import static java.time.DayOfWeek.*;

import christmas.domain.Benefits.BenefitResult;
import christmas.domain.Benefits.Discount;
import christmas.domain.Benefits.FreeGift;
import christmas.domain.OrderDTO;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public enum Benefit implements Discount{

    크리스마스_디데이_할인("크리스마스 디데이 할인", 1_000, BenefitType.DISCOUNT,null){
        @Override
        public BenefitResult checkDiscount(OrderDTO orderDTO) {
            if(orderDTO.getVisitDate().isAfter(LocalDate.of(2023, 12, 25))) {
                return null;
            }

            int date = orderDTO.getVisitDate().minusDays(1).getDayOfMonth();
            Integer discountPrice = (date * 100) + Benefit.크리스마스_디데이_할인.getAmount();
            return new BenefitResult(Benefit.크리스마스_디데이_할인, discountPrice);
        }
    },
    특별_할인("특별 할인", 1_000, BenefitType.DISCOUNT, null){
        @Override
        public BenefitResult checkDiscount(OrderDTO orderDTO) {
            List<Integer> discountDay = List.of(3, 10, 17, 24, 25, 31);
            Integer integer = discountDay.stream()
                    .filter(x -> x == orderDTO.getVisitDate().getDayOfMonth())
                    .findAny().orElse(null);
            if(integer == null) {
                return null;
            }
            return new BenefitResult(Benefit.특별_할인, Benefit.특별_할인.getAmount());
        }
    },
    평일_할인("평일 할인", 2_023, BenefitType.DISCOUNT, null){
        int cnt = 0;
        @Override
        public BenefitResult checkDiscount(OrderDTO orderDTO) {
            if (extracted(orderDTO)) {
                return null;
            }
            Map<String, Integer> menuList = orderDTO.getMenuList();
            for(Map.Entry<String, Integer> entry : menuList.entrySet()){
                if(Menu.getSpecifiedMenu(entry.getKey(), MenuType.DESSERT) == null){
                    continue;
                }
                cnt += entry.getValue();
            }
            if(cnt == 0) return null;
            return new BenefitResult(Benefit.평일_할인, Benefit.평일_할인.getAmount() * cnt);
        }

        private boolean extracted(OrderDTO orderDTO) {
            DayOfWeek dayOfWeek = orderDTO.getVisitDate().getDayOfWeek();
            if(dayOfWeek == FRIDAY || dayOfWeek == SATURDAY){
                return true;
            }
            return false;
        }
    },
    주말_할인("주말 할인", 2_023, BenefitType.DISCOUNT, null){
        @Override
        public BenefitResult checkDiscount(OrderDTO orderDTO) {
            DayOfWeek dayOfWeek = orderDTO.getVisitDate().getDayOfWeek();
            if(dayOfWeek != FRIDAY && dayOfWeek != SATURDAY){
                return null;
            }
            Map<String, Integer> menuList = orderDTO.getMenuList();
            int cnt = 0;

            for(Map.Entry<String, Integer> entry : menuList.entrySet()){
                if(Menu.getSpecifiedMenu(entry.getKey(), MenuType.MAIN) == null){
                    continue;
                }
                cnt += entry.getValue();
            }
            return new BenefitResult(Benefit.주말_할인, Benefit.주말_할인.getAmount() * cnt);
        }
    },
    증정_이벤트("증정 이벤트", Menu.CHAMPAGNE.getPrice(), BenefitType.PRESENT, new FreeGift(Menu.CHAMPAGNE.getName(), 1)){
        private Integer FREE_GIFT_CONDITION = 120_000;

        @Override
        public BenefitResult checkDiscount(OrderDTO orderDTO) {
            Map<String, Integer> menuList = orderDTO.getMenuList();

            Integer totalOrderPrice = 0;
            totalOrderPrice = getTotalOrderPrice(menuList, totalOrderPrice);

            if(totalOrderPrice < FREE_GIFT_CONDITION) {
                return null;
            }
            return new BenefitResult(Benefit.증정_이벤트, Benefit.증정_이벤트.getAmount());
        }
        private Integer getTotalOrderPrice(Map<String, Integer> menuList, Integer totalOrderPrice) {
            for(Map.Entry<String, Integer> entry : menuList.entrySet()){
                Menu menu = Menu.getMenu(entry.getKey());
                totalOrderPrice += (menu.getPrice() * entry.getValue());
            }
            return totalOrderPrice;
        }
    },
    할인_없음("없음", 0, BenefitType.DISCOUNT, null){
        @Override
        public BenefitResult checkDiscount(OrderDTO orderDTO) {
            return null;
        }
    },
    증정_없음("없음", 0, BenefitType.PRESENT, null){
        @Override
        public BenefitResult checkDiscount(OrderDTO orderDTO) {
            return null;
        }
    };


    private final String name;
    private final int amount;
    private final BenefitType benefitType;
    private final FreeGift freeGift;

    Benefit(String name, int amount, BenefitType benefitType, FreeGift freeGift) {
        this.name = name;
        this.amount = amount;
        this.benefitType = benefitType;
        this.freeGift = freeGift;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public BenefitType getBenefitType() {
        return benefitType;
    }

    public FreeGift getFreeGift() {
        return freeGift;
    }
}
