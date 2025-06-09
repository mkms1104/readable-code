package cleancode.studycafe.tobe.model.pass;

public class StudyCafeSeatPassFixture {
    public static StudyCafeSeatPass emptySeatPass() {
        return StudyCafeSeatPass.of(null, 0, 0, 0);
    }

    public static StudyCafeSeatPass customSeatPass(StudyCafePassType passType) {
        return StudyCafeSeatPass.of(passType, 0, 0, 0);
    }
}
