package cleancode.studycafe.tobe.model.order;

import cleancode.studycafe.tobe.model.pass.StudyCafePassType;
import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPass;
import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPassFixture;
import cleancode.studycafe.tobe.model.pass.locker.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.pass.locker.StudyCafeLockerPassFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StudyCafePassOrderTest {
    @DisplayName("좌석 이용권의 가격과 할인율, 락커 이용권의 가격이 주어지면, {좌석 이용권 가격 + 락커 이용권 가격 - 좌석 이용권 할인 가격} 공식으로 최종 가격을 계산한다.")
    @Test
    void getTotalPrice() {
        // Given
        StudyCafeSeatPass studyCafeSeatPass = StudyCafeSeatPass.of(StudyCafePassType.WEEKLY, 2, 100000, 0.1);
        StudyCafeLockerPass studyCafeLockerPass = StudyCafeLockerPass.of(StudyCafePassType.FIXED, 4, 10000);

        // When
        StudyCafePassOrder passOrderWithLockerPass = StudyCafePassOrder.of(studyCafeSeatPass, studyCafeLockerPass);
        StudyCafePassOrder passOrderWithoutLockerPass = StudyCafePassOrder.of(studyCafeSeatPass, null);

        // Then
        assertThat(passOrderWithLockerPass.getTotalPrice()).isEqualTo(100000);
        assertThat(passOrderWithoutLockerPass.getTotalPrice()).isEqualTo(90000);
    }

    @DisplayName("좌석 주문 객체(studyCafePassOrder) 생성 시 주어진 좌석 이용권 객체(studyCafeSeatPass)는 조회 시 동일한 객체를 반환한다.")
    @Test
    void getSeatPass() {
        // Given
        // [mockito 대체?: mock(StudyCafeSeatPass.class)]: 좌석 이용권의 세부 값들은 현재 테스트의 관심사가 아님
        StudyCafeSeatPass studyCafeSeatPass = StudyCafeSeatPassFixture.emptySeatPass();

        // When
        StudyCafePassOrder studyCafePassOrder = StudyCafePassOrder.of(studyCafeSeatPass, null);

        // Then
        assertThat(studyCafePassOrder.getSeatPass()).isEqualTo(studyCafeSeatPass);
    }

    @DisplayName("좌석 주문 객체(studyCafePassOrder) 생성 시 주어진 널러블한 락커 이용권 객체(StudyCafeLockerPass)에 따라 적절한 Optional 값을 반환한다.")
    @Test
    void getLockerPass() {
        // Given
        StudyCafeLockerPass studyCafeLockerPass = StudyCafeLockerPassFixture.emptyLockerPass();

        // When
        StudyCafePassOrder passOrderWithLockerPass = StudyCafePassOrder.of(null, studyCafeLockerPass);
        StudyCafePassOrder passOrderWithoutLockerPass = StudyCafePassOrder.of(null, null);

        // Then
        assertThat(passOrderWithLockerPass.getLockerPass().isPresent()).isTrue();
        assertThat(passOrderWithoutLockerPass.getLockerPass().isEmpty()).isTrue();
    }

//    // getLockerPass 테스트 (ParameterizedTest 활용)
//    @DisplayName("좌석 주문 객체(studyCafePassOrder) 생성 시 주어진 널러블한 락커 이용권 객체(StudyCafeLockerPass)에 따라 적절한 Optional 값을 반환한다.")
//    @ParameterizedTest
//    @MethodSource("provideGetLockerPassArgument")
//    void getLockerPass02(StudyCafeLockerPass lockerPass, boolean expectedOptionalResult) {
//        // Given
//        // When
//        StudyCafePassOrder passOrderWithLockerPass = StudyCafePassOrder.of(null, lockerPass);
//
//        // Then
//        assertThat(passOrderWithLockerPass.getLockerPass().isPresent()).isEqualTo(expectedOptionalResult);
//    }
//
//    static Stream<Arguments> provideGetLockerPassArgument() {
//        StudyCafeLockerPass studyCafeLockerPass = StudyCafeLockerPassFixture.emptyLockerPass();
//
//        return Stream.of(
//            Arguments.of(studyCafeLockerPass, true),
//            Arguments.of(null, false)
//        );
//    }
}