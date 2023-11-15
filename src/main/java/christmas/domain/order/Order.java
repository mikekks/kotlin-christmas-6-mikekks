package christmas.domain.order;

import static christmas.global.GuildMessage.BLANK;
import static christmas.global.GuildMessage.COUNT;
import static christmas.global.GuildMessage.NEXT_LINE;
import static christmas.global.GuildMessage.NONE;
import static christmas.global.GuildMessage.WON;
import static java.text.NumberFormat.getInstance;

import christmas.domain.benefits.BenefitResult;
import christmas.domain.benefits.discount.Discount;
import christmas.domain.benefits.present.Present;
import christmas.global.enums.Badge;
import java.util.List;
import java.util.Map;

public class Order {
    private List<Discount> discountList;  // 무조건 해당 할인은 적용되는게 맞는 것들의 모음
    private OrderDTO orderDTO;

    public Order(List<Discount> discountList, OrderDTO orderDTO) {
        this.discountList = discountList;
        this.orderDTO = orderDTO;
    }

    public String getOrderListToString(){
        String menuListString = "";
        for(Map.Entry<String, Integer> entry : orderDTO.menuList().entrySet()){
            menuListString += entry.getKey() + BLANK + entry.getValue() + COUNT + NEXT_LINE;
        }
        return menuListString;
    }

    public String getTotalPriceToString(){
        return getInstance().format(orderDTO.getTotalPrice()) + WON;
    }

    public String getFreeGiftListToString(){
        String freeGifts = "";
        for(Discount discount : discountList){
            if(!(discount instanceof Present)){
                continue;
            }
            BenefitResult presentAmount = ((Present) discount).getPresentAmount();
            freeGifts += presentAmount.getName() + presentAmount.getAmount() + COUNT + NEXT_LINE;
        }

        if(freeGifts == ""){
            freeGifts = NONE;
        }

        return freeGifts;
    }

    public String getBenefitDetailToString(){
        String discountDetail = "";

        for(Discount discount : discountList){
            BenefitResult benefitResult = discount.getDiscountAmount(orderDTO);
            discountDetail += benefitResult.getName() + ": -" + benefitResult.getAmount() + WON + NEXT_LINE;
        }

        if(discountDetail == ""){
            discountDetail = NONE;
        }
        return discountDetail;
    }
    public String getTotalBenefitToString(){
        return getInstance().format(getTotalBenefit()) + WON;
    }

    public String getAfterDiscountTotalPriceToString(){
        int totalPrice = orderDTO.getTotalPrice();
        int discount = getTotalDiscount();

        return getInstance().format(totalPrice - discount) + WON;
    }

    public String getBadgeToString(){
        return Badge.getBadge(getTotalBenefit()).getName();
    }


    private int getTotalBenefit() {
        int ret = 0;
        for(Discount discount : discountList){
            BenefitResult benefitResult = discount.getDiscountAmount(orderDTO);
            ret += benefitResult.getAmount();
        }
        return ret;
    }

    private int getTotalDiscount() {
        int ret = 0;
        for(Discount discount : discountList){
            if(discount instanceof Present){
                continue;
            }
            BenefitResult benefitResult = discount.getDiscountAmount(orderDTO);
            ret += benefitResult.getAmount();
        }
        return ret;
    }

}
