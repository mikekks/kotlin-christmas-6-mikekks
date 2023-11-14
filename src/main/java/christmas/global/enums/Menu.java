package christmas.global.enums;

import java.util.Arrays;

public enum Menu {
    BUTTON_MUSHROOM_SOUP("양송이수프", 6_000, MenuType.APPETIZER),
    TAPAS("타파스", 5_500, MenuType.APPETIZER),
    CAESAR_SALAD("시저샐러드", 8_000, MenuType.APPETIZER),

    T_BONE_STEAK("티본스테이크", 55_000, MenuType.MAIN),
    BARBECUE_RIBS("바비큐립", 54_000, MenuType.MAIN),
    SEAFOOD_PASTA("해산물파스타", 35_000, MenuType.MAIN),
    CHRISTMAS_PASTA("크리스마스파스타", 25_000, MenuType.MAIN),

    CHOCO_CAKE("초코케이크", 15_000, MenuType.DESSERT),
    ICE_CREAM("아이스크림", 5_000, MenuType.DESSERT),

    ZERO_COKE("제로콜라", 3_000, MenuType.BEVERAGE),
    RED_WINE("레드와인", 60_000, MenuType.BEVERAGE),
    CHAMPAGNE("샴페인", 25_000, MenuType.BEVERAGE);

    private final String name;
    private final int price;
    private final MenuType menuType;

    Menu(String name, int price, MenuType menuType) {
        this.name = name;
        this.price = price;
        this.menuType = menuType;
    }

    public static Menu getMenu(String menu){
        return Arrays.stream(Menu.values())
                .filter(x -> x.name.equals(menu)).findAny()
                .orElse(null);
    }

    public static Menu getSpecifiedMenu(String menu, MenuType type){
        return Arrays.stream(Menu.values())
                .filter(x -> x.name.equals(menu) && x.menuType.equals(type)).findAny()
                .orElse(null);
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
