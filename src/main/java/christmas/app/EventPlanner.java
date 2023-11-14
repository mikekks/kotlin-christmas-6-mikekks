package christmas.app;

import christmas.Prompt.InputView;
import christmas.Prompt.OutputView;
import christmas.domain.Benefits.BenefitResult;
import christmas.domain.Benefits.Discount;
import christmas.domain.OrderDTO;
import christmas.global.enums.Benefit;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EventPlanner {
    public void startOrder(){
        LocalDate visitDate = InputView.inputVisitDate();
        Map<String, Integer> orderList = InputView.inputOrder();
        OrderDTO orderDTO = new OrderDTO(visitDate, orderList);

        List<BenefitResult> benefitResults = Arrays.stream(Benefit.values()).map(x -> x.checkDiscount(orderDTO))
                .filter(x -> x != null)
                .collect(Collectors.toList());

        OutputView.printEventBenefits(orderDTO, benefitResults);
    }
}
