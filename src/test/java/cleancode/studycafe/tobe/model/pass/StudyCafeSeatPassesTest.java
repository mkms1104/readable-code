package cleancode.studycafe.tobe.model.pass;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static cleancode.studycafe.tobe.model.pass.StudyCafePassType.*;

class StudyCafeSeatPassesTest {
    @DisplayName("특정 좌석 타입의 이용권이 주어지면, 해당 좌석 타입과 동일한 좌석 이용권 목록을 반환한다.")
    @Test
    void findPassBy() {
        // Given
        List<StudyCafeSeatPass> seatPasses = List.of(
            StudyCafeSeatPassFixture.customSeatPass(WEEKLY),
            StudyCafeSeatPassFixture.customSeatPass(WEEKLY),
            StudyCafeSeatPassFixture.customSeatPass(WEEKLY),
            StudyCafeSeatPassFixture.customSeatPass(HOURLY),
            StudyCafeSeatPassFixture.customSeatPass(FIXED)
        );

        StudyCafeSeatPasses studyCafeSeatPasses = StudyCafeSeatPasses.of(seatPasses);

        // When
        List<StudyCafeSeatPass> passCandidates = studyCafeSeatPasses.findPassBy(WEEKLY);

        // Then
        Assertions.assertThatList(passCandidates).hasSize(3);
    }
}