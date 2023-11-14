package christmas.global.enums;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

public enum Badge {
    NONE("없음", 0),
    STAR("별", 5_000),
    TREE("트리", 10_000),
    SANTA("산타", 20_000);

    private final String name;
    private final Integer benefitPrice;

    Badge(String name, Integer benefitPrice) {
        this.name = name;
        this.benefitPrice = benefitPrice;
    }
    public static Badge getBadge(Integer _benefitPrice){
        Comparator<Badge> comparatorByAge = Comparator.comparingInt(Badge::getBenefitPrice);
        return Arrays.stream(Badge.values())
                .filter(x -> x.benefitPrice <= _benefitPrice)
                .max(comparatorByAge)
                .orElse(Badge.NONE);
    }

    public String getName() {
        return name;
    }

    public Integer getBenefitPrice() {
        return benefitPrice;
    }
}
