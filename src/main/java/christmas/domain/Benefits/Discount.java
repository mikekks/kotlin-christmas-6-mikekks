package christmas.domain.Benefits;

import christmas.domain.OrderDTO;

public interface Discount {
    BenefitResult checkDiscount(OrderDTO orderDTO);
}
