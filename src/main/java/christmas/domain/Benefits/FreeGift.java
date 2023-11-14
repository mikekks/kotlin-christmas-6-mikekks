package christmas.domain.Benefits;

import christmas.global.enums.Menu;

public class FreeGift {
    String menu;
    Integer count;

    public FreeGift(String menu, Integer count) {
        this.menu = menu;
        this.count = count;
    }

    public String getMenu() {
        return menu;
    }

    public Integer getCount() {
        return count;
    }
}
