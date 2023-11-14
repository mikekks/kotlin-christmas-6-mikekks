package christmas.global.enums;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class BadgeTest {

    @Test
    void getBadgeTest(){
        Badge badge = Badge.getBadge(10000);
        Assertions.assertThat(badge.getName()).isEqualTo("트리");

        Badge badge2 = Badge.getBadge(0);
        Assertions.assertThat(badge2.getName()).isEqualTo("없음");

    }
}