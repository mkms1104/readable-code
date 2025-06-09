package cleancode.studycafe.tobe.io;

import cleancode.studycafe.tobe.exception.AppException;
import cleancode.studycafe.tobe.model.pass.StudyCafePassType;
import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPass;
import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPassFixture;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Scanner;

import static cleancode.studycafe.tobe.model.pass.StudyCafePassType.*;
import static org.assertj.core.api.Assertions.*;

class InputHandlerTest {
    @DisplayName("사용자가 입력한 번호에 따라 알맞은 이용권 타입을 반환한다.")
    @ParameterizedTest
    @CsvSource({
        "1, HOURLY",
        "2, WEEKLY",
        "3, FIXED"
    })
    void getPassTypeSelectingUserAction01(String userInput, StudyCafePassType expectedPassType) {
        // Given
        Scanner sc = new Scanner(new ByteArrayInputStream(userInput.getBytes()));

        // When
        InputHandler inputHandler = new InputHandler(sc);
        StudyCafePassType passType = inputHandler.getPassTypeSelectingUserAction();

        // Then
        Assertions.assertThat(passType).isEqualTo(expectedPassType);
    }

    @DisplayName("사용자가 입력한 번호가 1~3 사이의 값이 아니라면, AppException 예외를 발생시킨다.")
    @Test
    void getPassTypeSelectingUserAction02() {
        // Given
        String userInput = "4";
        Scanner sc = new Scanner(new ByteArrayInputStream(userInput.getBytes()));

        // When
        InputHandler inputHandler = new InputHandler(sc);

        // Then
        assertThatThrownBy(inputHandler::getPassTypeSelectingUserAction)
                .isInstanceOf(AppException.class)
                .hasMessage("잘못된 입력입니다.");
    }

    @DisplayName("사용자가 입력한 번호에 맞는 이용권을 반환한다.")
    @Test
    void getSelectPass() {
        // Given
        StudyCafeSeatPass targetSeatPass = StudyCafeSeatPassFixture.customSeatPass(FIXED);
        List<StudyCafeSeatPass> seatPasses = List.of(
            StudyCafeSeatPassFixture.customSeatPass(WEEKLY),
            StudyCafeSeatPassFixture.customSeatPass(HOURLY),
            targetSeatPass
        );

        String userInput = "3";
        Scanner sc = new Scanner(new ByteArrayInputStream(userInput.getBytes()));

        // When
        InputHandler inputHandler = new InputHandler(sc);
        StudyCafeSeatPass selectPass = inputHandler.getSelectPass(seatPasses);

        // Then
        assertThat(selectPass).isEqualTo(targetSeatPass);
    }

    @DisplayName("사용자가 입력한 번호에 따라 락커 사용 여부를 반환한다.")
    @ParameterizedTest
    @CsvSource({
        "1, true",
        "2, false",
    })
    void getLockerSelection(String userInput, boolean expectedLockerSelection) {
        // Given
        Scanner sc = new Scanner(new ByteArrayInputStream(userInput.getBytes()));

        // When
        InputHandler inputHandler = new InputHandler(sc);
        boolean lockerSelection = inputHandler.getLockerSelection();

        // Then
        assertThat(lockerSelection).isEqualTo(expectedLockerSelection);
    }

}