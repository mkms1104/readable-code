package cleancode.studycafe.tobe.model;

import java.util.Objects;

import cleancode.studycafe.tobe.pass.Pass;

public class StudyCafeLockerPass {

    private final StudyCafePassType passType;
    private final Pass pass;

    private StudyCafeLockerPass(StudyCafePassType passType, Pass pass) {
        this.passType = passType;
        this.pass = pass;
    }

    public static StudyCafeLockerPass of(StudyCafePassType passType, Pass pass) {
        return new StudyCafeLockerPass(passType, pass);
    }

    public StudyCafePassType getPassType() {
        return passType;
    }

    public int getDuration() {
        return pass.getDuration();
    }

    public int getPrice() {
        return pass.getPrice();
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
        StudyCafeLockerPass that = (StudyCafeLockerPass) obj;
        return Objects.equals(passType, that.passType) && Objects.equals(pass.getDuration(), that.pass.getDuration());
    }

    @Override
    public int hashCode() {
        return Objects.hash(passType, pass.getDuration());
    }
}
