package christmas.domain.order;

import christmas.global.enums.Menu;
import java.time.LocalDate;
import java.util.Map;

public record OrderDTO(
        LocalDate visitDate,
        Map<String, Integer> menuList
) {
    public Integer getTotalPrice(){
        Integer totalPrice = 0;
        for(Map.Entry<String, Integer> entry : menuList.entrySet()){
            totalPrice += Menu.getMenu(entry.getKey()).getPrice() * entry.getValue();
        }
        return totalPrice;
    }
}
