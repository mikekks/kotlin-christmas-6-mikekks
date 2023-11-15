package christmas.domain.benefits.discount;

import christmas.domain.benefits.BenefitResult;
import christmas.domain.order.OrderDTO;
import java.util.List;

public class SpecialDiscount implements Discount{
    List<Integer> discountDay = List.of(3, 10, 17, 24, 25, 31);
    private String name = "특별 할인";
    private Integer amount = 1000;

    @Override
    public Discount checkDiscount(OrderDTO orderDTO) {
        Integer integer = discountDay.stream()
                .filter(x -> x == orderDTO.visitDate().getDayOfMonth())
                .findAny().orElse(null);
        if (integer == null) {
            return null;
        }
        return this;
    }

    @Override
    public BenefitResult getDiscountAmount(OrderDTO orderDTO) {
        return new BenefitResult(name, amount);
    }
}
