package christmas.Prompt;

import static christmas.global.GuildMessage.*;
import christmas.domain.order.Order;

public class OutputView {
    public static void printEventBenefits(Order order) {
        System.out.println(PRINT_EVENT_BENEFITS);

        printBeforeBenefit(order);
        printFreeGiftMenu(order);
        printAfterBenefit(order);
    }

    private static void printBeforeBenefit(Order order) {
        System.out.println();
        System.out.println(PRINT_ORDER_MENUS);
        System.out.println(order.getOrderListToString());

        System.out.println(PRINT_BEFORE_ORDER_PRICE);
        System.out.println(order.getTotalPriceToString());
    }

    private static void printFreeGiftMenu(Order order) {
        System.out.println();
        System.out.println(PRINT_FREE_GIFT_MENU);
        System.out.println(order.getFreeGiftListToString());
    }

    private static void printAfterBenefit(Order order) {
        System.out.println();
        System.out.println(PRINT_BENEFIT_DETAILS);
        System.out.println(order.getBenefitDetailToString());

        System.out.println();
        System.out.println(PRINT_TOTAL_BENEFIT_DETAIL);
        System.out.println(order.getTotalBenefitToString());

        System.out.println();
        System.out.println(PRINT_AFTER_ORDER_PRICE);
        System.out.println(order.getAfterDiscountTotalPriceToString());

        System.out.println();
        System.out.println(PRINT_EVENT_BADGE);
        System.out.println(order.getBadgeToString());
    }
}
