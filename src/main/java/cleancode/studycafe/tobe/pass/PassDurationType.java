package cleancode.studycafe.tobe.pass;

public enum PassDurationType {
    HOUR("시간"),
    WEEK("주"),
    MONTH("개월");

    private final String description;

    PassDurationType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
