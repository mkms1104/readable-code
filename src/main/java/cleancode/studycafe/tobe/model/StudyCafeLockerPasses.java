package cleancode.studycafe.tobe.model;

import java.util.List;

public class StudyCafeLockerPasses {
    private final List<StudyCafeLockerPass> studyCafeLockerPasses;

    private StudyCafeLockerPasses(List<StudyCafeLockerPass> studyCafeLockerPasses) {
        this.studyCafeLockerPasses = studyCafeLockerPasses;
    }

    public static StudyCafeLockerPasses of(List<StudyCafeLockerPass> studyCafeLockerPasses) {
        return new StudyCafeLockerPasses(studyCafeLockerPasses);
    }

    public StudyCafeLockerPass findLockerPass(StudyCafePassType studyCafePassType, int duration) {
        return studyCafeLockerPasses.stream()
            .filter(lockerPass ->
                lockerPass.getPassType() == studyCafePassType
                    && lockerPass.getDuration() == duration)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 이용권입니다."));
    }
}
