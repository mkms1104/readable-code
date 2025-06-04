package cleancode.studycafe.tobe.model;

import cleancode.studycafe.tobe.pass.PassDurationType;

public enum StudyCafePassType {

    HOURLY("시간 단위 이용권", PassDurationType.HOUR),
    WEEKLY("주 단위 이용권", PassDurationType.WEEK),
    FIXED("1인 고정석", PassDurationType.WEEK);

    private final String description;
    private final PassDurationType passDurationType;

    StudyCafePassType(String description, PassDurationType passDurationType) {
        this.description = description;
        this.passDurationType = passDurationType;
    }

    public PassDurationType getPassDurationType() {
        return passDurationType;
    }
}
