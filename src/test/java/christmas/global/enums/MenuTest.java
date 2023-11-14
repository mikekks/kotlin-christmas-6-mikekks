package christmas.global.enums;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class MenuTest {

    @Test
    void getSpecifiedMenuTest() {
        Menu 샴페인 = Menu.getSpecifiedMenu("샴페인", MenuType.BEVERAGE);
        Assertions.assertThat(샴페인).isEqualTo(Menu.CHAMPAGNE);

        Menu 샴페인2 = Menu.getSpecifiedMenu("샴페인", MenuType.DESSERT);
        Assertions.assertThat(샴페인2).isEqualTo(null);

    }

}