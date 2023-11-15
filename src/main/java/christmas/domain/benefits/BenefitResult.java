package christmas.domain.benefits;

public class BenefitResult {
    String name;
    Integer amount;

    public BenefitResult(String name, Integer amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public Integer getAmount() {
        return amount;
    }
}
