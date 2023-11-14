package christmas.global.enums;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import christmas.domain.Benefits.BenefitResult;
import christmas.domain.OrderDTO;
import java.time.LocalDate;
import java.util.HashMap;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BenefitTest {
    HashMap<String, Integer> map = new HashMap<>();
    HashMap<String, Integer> map2 = new HashMap<>();

    OrderDTO orderDTO1;
    OrderDTO orderDTO_NOT_CHRISTMAS;
    OrderDTO orderDTO_NOT_SPECIAL;
    OrderDTO orderDTO_NOT_DESSERT;


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
        BenefitResult benefitResult = Benefit.크리스마스_디데이_할인.checkDiscount(orderDTO1);
        assertThat(benefitResult).isNotNull();

        BenefitResult benefitResult2 = Benefit.크리스마스_디데이_할인.checkDiscount(orderDTO_NOT_CHRISTMAS);
        assertThat(benefitResult2).isNull();
    }

    @Test
    void 특별_할인Test(){
        BenefitResult benefitResult = Benefit.특별_할인.checkDiscount(orderDTO1);
        assertThat(benefitResult).isNotNull();
        assertThat(benefitResult.getAmount()).isEqualTo(1000);

        BenefitResult benefitResult2 = Benefit.특별_할인.checkDiscount(orderDTO_NOT_SPECIAL);
        assertThat(benefitResult2).isNull();
    }
    @Test
    void 평일_할인Test(){
        BenefitResult benefitResult = Benefit.평일_할인.checkDiscount(orderDTO1);
        assertThat(benefitResult).isNotNull();
        assertThat(benefitResult.getAmount()).isEqualTo(2023);

        BenefitResult benefitResult2 = Benefit.평일_할인.checkDiscount(orderDTO_NOT_SPECIAL);
        assertThat(benefitResult2).isNull();

    }

    @Test
    void 평일_할인_디저트없음Test(){
        BenefitResult benefitResult3 = Benefit.평일_할인.checkDiscount(orderDTO_NOT_DESSERT);
        assertThat(benefitResult3).isNull();

    }

    @Test
    void 주말_할인Test(){
        BenefitResult benefitResult = Benefit.주말_할인.checkDiscount(orderDTO1);
        assertThat(benefitResult).isNull();

        BenefitResult benefitResult2 = Benefit.주말_할인.checkDiscount(orderDTO_NOT_SPECIAL);
        assertThat(benefitResult2).isNotNull();
        assertThat(benefitResult2.getAmount()).isEqualTo(2023);
    }

    @Test
    void 증정_이벤트Test(){
        BenefitResult benefitResult = Benefit.증정_이벤트.checkDiscount(orderDTO1);
        assertThat(benefitResult).isNotNull();
        assertThat(benefitResult.getAmount()).isEqualTo(25000);
        assertThat(benefitResult.getBenefit().getFreeGift().getMenu()).isEqualTo(Menu.CHAMPAGNE.getName());

        BenefitResult benefitResult2 = Benefit.증정_이벤트.checkDiscount(orderDTO_NOT_DESSERT);
        assertThat(benefitResult2).isNull();

    }



}