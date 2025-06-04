package cleancode.studycafe.tobe.model;

import cleancode.studycafe.tobe.pass.Pass;

public class StudyCafePass {

    private final StudyCafePassType passType;
    private final Pass pass;
    private final double discountRate;

    private StudyCafePass(StudyCafePassType passType, Pass pass, double discountRate) {
        this.passType = passType;
        this.pass = pass;
        this.discountRate = discountRate;
    }

    public static StudyCafePass of(StudyCafePassType passType, Pass pass, double discountRate) {
        return new StudyCafePass(passType, pass, discountRate);
    }

    public boolean hasEventDiscount() {
        return discountRate > 0;
    }

    public int getDiscountPrice() {
        return (int) (pass.getPrice() * discountRate);
    }

    public int getTotalPrice() {
        return pass.getPrice() - getDiscountPrice();
    }

    public StudyCafePassType getPassType() {
        return passType;
    }

    public int getDuration() {
        return pass.getDuration();
    }

    public String display() {
        return pass.display();
    }

}
