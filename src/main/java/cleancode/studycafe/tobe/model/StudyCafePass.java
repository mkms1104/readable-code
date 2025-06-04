package cleancode.studycafe.tobe.model;

import cleancode.studycafe.tobe.pass.Pass;

import java.util.Objects;

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

    public StudyCafePassType getPassType() {
        return passType;
    }

    public int getDiscountPrice() {
        return (int) (pass.getPrice() * discountRate);
    }

    public int getTotalPrice() {
        return pass.getPrice() - getDiscountPrice();
    }

    public int getDuration() {
        return pass.getDuration();
    }

    public String display() {
        return pass.display();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        StudyCafePass that = (StudyCafePass) obj;
        return Objects.equals(passType, that.passType) && Objects.equals(pass.getDuration(), that.pass.getDuration());
    }

    @Override
    public int hashCode() {
        return Objects.hash(passType, pass.getDuration());
    }
}
