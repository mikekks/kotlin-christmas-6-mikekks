package christmas.domain.benefits.discount;

import christmas.domain.benefits.BenefitResult;
import christmas.domain.order.OrderDTO;
import java.time.LocalDate;

public class ChristMasDayDiscount implements Discount{
    private String name = "크리스마스 디데이 할인";
    private Integer amount = 1000;
    @Override
    public Discount checkDiscount(OrderDTO orderDTO) {
        if(orderDTO.visitDate().isAfter(LocalDate.of(2023, 12, 25))) {
            return null;
        }
        return this;
    }

    @Override
    public BenefitResult getDiscountAmount(OrderDTO orderDTO) {
        int date = orderDTO.visitDate().minusDays(1).getDayOfMonth();
        Integer discountPrice = (date * 100) + amount;
        return new BenefitResult(name, discountPrice);
    }
}
