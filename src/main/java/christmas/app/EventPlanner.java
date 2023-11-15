package christmas.app;

import christmas.Prompt.InputView;
import christmas.Prompt.OutputView;
import christmas.domain.benefits.BenefitFactory;
import christmas.domain.benefits.discount.Discount;
import christmas.domain.order.Order;
import christmas.domain.order.OrderDTO;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;


public class EventPlanner {
    public void startOrder(){
        LocalDate visitDate = InputView.inputVisitDate();
        Map<String, Integer> orderList = InputView.inputOrder();
        OrderDTO orderDTO = new OrderDTO(visitDate, orderList);

        List<Discount> benefitResults = BenefitFactory.getDiscountList(orderDTO);

        OutputView.printEventBenefits(new Order(benefitResults, orderDTO));
    }
}
