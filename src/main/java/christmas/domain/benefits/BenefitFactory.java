package christmas.domain.benefits;

import christmas.domain.benefits.discount.ChristMasDayDiscount;
import christmas.domain.benefits.discount.Discount;
import christmas.domain.benefits.discount.PresentEvent;
import christmas.domain.benefits.discount.SpecialDiscount;
import christmas.domain.benefits.discount.WeekDayDiscount;
import christmas.domain.benefits.discount.WeekEndDiscount;
import christmas.domain.order.OrderDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BenefitFactory {

    public static List<Discount> getDiscountList(OrderDTO orderDTO){
        List<Discount> discount = getDiscount();

        return discount.stream().map(x -> x.checkDiscount(orderDTO))
                .filter(x -> x != null)
                .collect(Collectors.toList());
    }


    // 새로운 할인 정책은 여기에 추가하면 된다.
    private static List<Discount> getDiscount(){
        List<Discount> discountList = new ArrayList<>();

        discountList.add(new ChristMasDayDiscount());
        discountList.add(new SpecialDiscount());
        discountList.add(new WeekDayDiscount());
        discountList.add(new WeekEndDiscount());
        discountList.add(new PresentEvent());

        return discountList;
    }
}
