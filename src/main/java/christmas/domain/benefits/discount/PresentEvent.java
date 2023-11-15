package christmas.domain.benefits.discount;

import christmas.domain.benefits.BenefitResult;
import christmas.domain.benefits.present.Present;
import christmas.domain.order.OrderDTO;
import christmas.global.enums.Menu;
import java.util.Map;

public class PresentEvent implements Discount, Present {
    private String name = "증정 이벤트";
    private Menu PRESENT_MENU = Menu.CHAMPAGNE;
    private Integer PRESENT_AMOUNT = 1;
    private Integer PRESENT_CONDITION = 120_000;


    @Override
    public Discount checkDiscount(OrderDTO orderDTO) {
        Integer totalOrderPrice = orderDTO.getTotalPrice();

        if(totalOrderPrice < PRESENT_CONDITION) {
            return null;
        }
        return this;
    }

    @Override
    public BenefitResult getDiscountAmount(OrderDTO orderDTO) {
        return new BenefitResult(name, PRESENT_MENU.getPrice());
    }

    @Override
    public BenefitResult getPresentAmount() {
        return new BenefitResult(PRESENT_MENU.getName(), PRESENT_AMOUNT);
    }
}
