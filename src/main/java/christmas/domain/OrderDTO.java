package christmas.domain;

import java.time.LocalDate;
import java.util.Map;

public class OrderDTO {

    private LocalDate visitDate;
    private Map<String, Integer> menuList;

    public OrderDTO(LocalDate visitDate, Map<String, Integer> menuList) {
        this.visitDate = visitDate;
        this.menuList = menuList;
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public Map<String, Integer> getMenuList() {
        return menuList;
    }


}
