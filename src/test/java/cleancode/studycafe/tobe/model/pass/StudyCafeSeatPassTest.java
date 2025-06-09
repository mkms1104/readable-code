package cleancode.studycafe.tobe.model.pass;

import cleancode.studycafe.tobe.model.pass.locker.StudyCafeLockerPass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StudyCafeSeatPassTest {

    @DisplayName("좌석 이용권과 락커 이용권의 타입과 기간이 동일하면, true 값을 반환한다.")
    @Test
    void isSameDurationType() {
        // Given
        StudyCafePassType passType = StudyCafePassType.WEEKLY;
        int duration = 2;

        StudyCafeSeatPass studyCafeSeatPass = StudyCafeSeatPass.of(passType, duration, 0, 0);
        StudyCafeLockerPass studyCafeLockerPass = StudyCafeLockerPass.of(passType, duration, 0);

        // When
        boolean isSameDurationType = studyCafeSeatPass.isSameDurationType(studyCafeLockerPass);

        // Then
        assertThat(isSameDurationType).isTrue();
    }


    @DisplayName("좌석 이용권의 가격과 할인율이 주어지면, {가격 * 할인율} 공식으로 할인 가격을 계산한다.")
    @Test
    void getDiscountPrice() {
        // Given
        // When
        StudyCafeSeatPass studyCafeSeatPass = StudyCafeSeatPass.of(StudyCafePassType.WEEKLY, 2, 100000, 0.1);

        // Then
        assertThat(studyCafeSeatPass.getDiscountPrice()).isEqualTo(10000);
    }
}
