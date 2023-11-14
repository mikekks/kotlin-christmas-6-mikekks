package christmas.domain.Benefits;

import christmas.global.enums.Benefit;

public class BenefitResult {
    private Benefit benefit;
    private Integer amount;

    public BenefitResult(Benefit benefit, Integer amount) {
        this.benefit = benefit;
        this.amount = amount;
    }

    public Benefit getBenefit() {
        return benefit;
    }

    public Integer getAmount() {
        return amount;
    }
}
