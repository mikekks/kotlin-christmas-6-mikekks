package christmas.global.enums;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.benefits.BenefitFactory;
import christmas.domain.benefits.discount.Discount;
import christmas.domain.benefits.discount.PresentEvent;
import christmas.domain.benefits.discount.SpecialDiscount;
import christmas.domain.benefits.discount.WeekDayDiscount;
import christmas.domain.benefits.discount.WeekEndDiscount;
import christmas.domain.benefits.present.Present;
import christmas.domain.order.OrderDTO;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BenefitTest {
    HashMap<String, Integer> map = new HashMap<>();
    HashMap<String, Integer> map2 = new HashMap<>();

    OrderDTO orderDTO1;
    OrderDTO orderDTO_NOT_CHRISTMAS;
    OrderDTO orderDTO_NOT_SPECIAL;
    OrderDTO orderDTO_NOT_DESSERT;
    List<Discount> discountList;

    @BeforeEach
    void beforeEach() {
        map.put("샴페인", 1);
        map.put("양송이수프", 2);
        map.put("티본스테이크", 2);
        map.put("아이스크림", 1);

        map2.put("샴페인", 1);
        map2.put("양송이수프", 2);

        orderDTO_NOT_DESSERT = new OrderDTO(LocalDate.of(2023,12,13), map2);
        orderDTO1 = new OrderDTO(LocalDate.of(2023,12,24), map);
        orderDTO_NOT_CHRISTMAS = new OrderDTO(LocalDate.of(2023,12,29), map);
        orderDTO_NOT_SPECIAL = new OrderDTO(LocalDate.of(2023,12,16), map);
    }

    @Test
    void 크리스마스_디데이_할인Test(){
        List<Discount> discountList1 = BenefitFactory.getDiscountList(orderDTO1);
        Discount discount = discountList1.stream().filter(x -> x.getDiscountAmount(orderDTO1).getAmount() == 3300)
                .findAny().orElse(null);

        assertThat(discount).isNotNull();

        List<Discount> discountList2 = BenefitFactory.getDiscountList(orderDTO_NOT_CHRISTMAS);
        Discount discount2 = discountList2.stream().filter(x -> x.getDiscountAmount(orderDTO_NOT_CHRISTMAS).getAmount() == 3300)
                .findAny().orElse(null);

        assertThat(discount2).isNull();
    }

    @Test
    void 특별_할인Test(){
        Discount specialDiscount = new SpecialDiscount();
        Discount discount = specialDiscount.checkDiscount(orderDTO1);
        assertThat(discount).isNotNull();
        assertThat(discount.getDiscountAmount(orderDTO1).getAmount()).isEqualTo(1000);

        SpecialDiscount specialDiscount2 = new SpecialDiscount();
        Discount discount2 = specialDiscount2.checkDiscount(orderDTO_NOT_SPECIAL);
        assertThat(discount2).isNull();
    }
    @Test
    void 평일_할인Test(){
        Discount discount = new WeekDayDiscount();
        discount = discount.checkDiscount(orderDTO1);
        assertThat(discount).isNotNull();
        assertThat(discount.getDiscountAmount(orderDTO1).getAmount()).isEqualTo(2023);

        discount = discount.checkDiscount(orderDTO_NOT_SPECIAL);
        assertThat(discount).isNull();

    }

    @Test
    void 평일_할인_디저트없음Test(){
        Discount discount = new WeekDayDiscount();
        discount = discount.checkDiscount(orderDTO_NOT_DESSERT);
        assertThat(discount).isNull();

    }

    @Test
    void 주말_할인Test(){
        Discount discount = new WeekEndDiscount();
        discount = discount.checkDiscount(orderDTO1);
        assertThat(discount).isNull();

        Discount discount2 = new WeekEndDiscount();
        discount2 = discount2.checkDiscount(orderDTO_NOT_SPECIAL);
        assertThat(discount2).isNotNull();
        assertThat(discount2.getDiscountAmount(orderDTO_NOT_SPECIAL).getAmount()).isEqualTo(4046);
    }

    @Test
    void 증정_이벤트Test(){
        PresentEvent discount = new PresentEvent();
        Discount discount1 = discount.checkDiscount(orderDTO1);
        assertThat(discount1).isNotNull();
        assertThat(discount).isInstanceOf(Present.class);
        assertThat(discount.getDiscountAmount(orderDTO1).getAmount()).isEqualTo(25000);
        assertThat(discount.getPresentAmount().getName()).isEqualTo(Menu.CHAMPAGNE.getName());

        PresentEvent discount2 = new PresentEvent();
        Discount discount3 = discount2.checkDiscount(orderDTO_NOT_DESSERT);
        assertThat(discount3).isNull();

    }



}