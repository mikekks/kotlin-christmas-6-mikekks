package christmas.domain.benefits.discount;

import christmas.domain.benefits.BenefitResult;
import christmas.domain.order.OrderDTO;

public interface Discount {
    // 해당 인터페이스 반환하는 메서드
    Discount checkDiscount(OrderDTO orderDTO);

    // [혜택 내용 : 금액] 출력하는 로직
    BenefitResult getDiscountAmount(OrderDTO orderDTO);
}
