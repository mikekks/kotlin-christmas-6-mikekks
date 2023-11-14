package christmas.Prompt;

import static christmas.global.GuildMessage.*;
import static java.text.NumberFormat.getInstance;

import christmas.domain.Benefits.BenefitResult;
import christmas.domain.OrderDTO;
import christmas.global.enums.Badge;
import christmas.global.enums.BenefitType;
import christmas.global.enums.Menu;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    public static void printEventBenefits(OrderDTO orderDTO, List<BenefitResult> benefitResults) {
        System.out.println(PRINT_EVENT_BENEFITS);

        printBeforeBenefit(orderDTO);
        printFreeGiftMenu(benefitResults);
        printAfterBenefit(orderDTO, benefitResults);
    }

    private static void printBeforeBenefit(OrderDTO orderDTO) {
        System.out.println();
        System.out.println(PRINT_ORDER_MENUS);
        System.out.print(menuListToString(orderDTO));
        System.out.println();
        System.out.println(PRINT_BEFORE_ORDER_PRICE);
        System.out.println(getInstance().format(totalOrderPrice(orderDTO)) + WON);
    }

    private static void printFreeGiftMenu(List<BenefitResult> benefitResults) {
        System.out.println();
        System.out.println(PRINT_FREE_GIFT_MENU);
        boolean isNone = true;

        for(BenefitResult benefitResult : benefitResults){
            if(benefitResult.getBenefit().getBenefitType() == BenefitType.PRESENT){
                System.out.println(benefitResult.getBenefit().getFreeGift().getMenu() + BLANK + benefitResult.getBenefit().getFreeGift().getCount());
                isNone = false;
            }
        }

        if(isNone){
            System.out.println(NONE);
        }
    }

    private static void printAfterBenefit(OrderDTO orderDTO, List<BenefitResult> benefitResults) {
        printBenefitDetails(benefitResults);
        printTotalBenefit(benefitResults);
        printAfterDiscount(orderDTO, benefitResults);
        printEventBadge(benefitResults);
    }

    private static void printBenefitDetails(List<BenefitResult> benefitResults) {
        System.out.println();
        System.out.println(PRINT_BENEFIT_DETAILS);
        if(benefitResults.size() == 0){
            System.out.println(NONE);
            return;
        }

        for(BenefitResult discount : benefitResults){
            System.out.println(discount.getBenefit().getName() + ": -" + getInstance().format(discount.getAmount()) + WON);
        }
    }
    private static void printTotalBenefit(List<BenefitResult> benefitResults) {
        System.out.println();
        System.out.println(PRINT_TOTAL_BENEFIT_DETAIL);

        Integer totalBenefit = 0;
        for(BenefitResult discount : benefitResults){
            totalBenefit -= discount.getAmount();
        }
        System.out.println(getInstance().format(totalBenefit) + WON);
    }
    private static void printAfterDiscount(OrderDTO orderDTO, List<BenefitResult> benefitResults) {
        System.out.println();
        System.out.println(PRINT_AFTER_ORDER_PRICE);
        Integer totalBenefit = 0;
        for(BenefitResult benefitResult : benefitResults){
            if(benefitResult.getBenefit().getBenefitType() == BenefitType.PRESENT){
                continue;
            }
            totalBenefit += benefitResult.getAmount();
        }

        System.out.println(getInstance().format(totalOrderPrice(orderDTO) - totalBenefit) + WON);
    }
    private static void printEventBadge(List<BenefitResult> benefitResults) {
        System.out.println();
        System.out.println(PRINT_EVENT_BADGE);
        Integer totalBenefit = 0;
        for(BenefitResult benefitResult : benefitResults){
            totalBenefit += benefitResult.getAmount();
        }

        System.out.println(Badge.getBadge(totalBenefit).getName());
    }

    private static String menuListToString(OrderDTO orderDTO){
        Map<String, Integer> menuList = orderDTO.getMenuList();

        String menuListString = "";
        for(Map.Entry<String, Integer> entry : menuList.entrySet()){
            menuListString += entry.getKey() + BLANK + entry.getValue() + COUNT + NEXT_LINE;
        }
        return menuListString;
    }

    private static Integer totalOrderPrice(OrderDTO orderDTO){
        Map<String, Integer> menuList = orderDTO.getMenuList();
        Integer totalOrderPrice = 0;
        for(Map.Entry<String, Integer> entry : menuList.entrySet()){
            Menu menu = Menu.getMenu(entry.getKey());
            totalOrderPrice += (menu.getPrice() * entry.getValue());
        }

        return totalOrderPrice;
    }
}
