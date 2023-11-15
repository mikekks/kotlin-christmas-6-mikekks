package christmas.domain.benefits.discount;

import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.SATURDAY;

import christmas.domain.benefits.BenefitResult;
import christmas.domain.order.OrderDTO;
import christmas.global.enums.Menu;
import christmas.global.enums.MenuType;
import java.time.DayOfWeek;
import java.util.Map;

public class WeekDayDiscount implements Discount{
    private String name = "평일 할인";
    private Integer amount = 2023;
    int cnt = 0;
    @Override
    public Discount checkDiscount(OrderDTO orderDTO) {
        DayOfWeek dayOfWeek = orderDTO.visitDate().getDayOfWeek();
        if(dayOfWeek == FRIDAY || dayOfWeek == SATURDAY){
            return null;
        }

        getSatisfiedCnt(orderDTO);
        if(cnt == 0) {
            return null;
        }
        
        return this;
    }

    @Override
    public BenefitResult getDiscountAmount(OrderDTO orderDTO) {
        return new BenefitResult(name, amount * cnt);
    }

    private void getSatisfiedCnt(OrderDTO orderDTO) {
        for(Map.Entry<String, Integer> entry : orderDTO.menuList().entrySet()){
            if(Menu.getSpecifiedMenu(entry.getKey(), MenuType.DESSERT) == null){
                continue;
            }
            cnt += entry.getValue();
        }
    }
}
