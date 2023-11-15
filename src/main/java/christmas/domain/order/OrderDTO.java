package christmas.domain.order;

import christmas.global.enums.Menu;
import java.time.LocalDate;
import java.util.Map;

public class OrderDTO {

    private LocalDate visitDate;
    private Map<String, Integer> menuList;

    public OrderDTO(LocalDate visitDate, Map<String, Integer> menuList) {
        this.visitDate = visitDate;
        this.menuList = menuList;
    }

    public Integer getTotalPrice(){
        Integer totalPrice = 0;
        for(Map.Entry<String, Integer> entry : menuList.entrySet()){
            totalPrice += Menu.getMenu(entry.getKey()).getPrice() * entry.getValue();
        }
        return totalPrice;
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public Map<String, Integer> getMenuList() {
        return menuList;
    }


}
