package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.exception.AppException;
import cleancode.studycafe.tobe.io.InputHandler;
import cleancode.studycafe.tobe.io.OutputHandler;
import cleancode.studycafe.tobe.io.StudyCafeFileHandler;
import cleancode.studycafe.tobe.model.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.StudyCafeLockerPasses;
import cleancode.studycafe.tobe.model.StudyCafePass;
import cleancode.studycafe.tobe.model.StudyCafePassType;
import cleancode.studycafe.tobe.model.StudyCafePasses;

import java.util.List;

public class StudyCafePassMachine {

    private final InputHandler inputHandler = new InputHandler();
    private final OutputHandler outputHandler = new OutputHandler();
    private final StudyCafeFileHandler studyCafeFileHandler = new StudyCafeFileHandler();

    public void run() {
        try {
            outputHandler.showWelcomeMessage();
            outputHandler.showAnnouncement();

            outputHandler.askPassTypeSelection();
            StudyCafePassType studyCafePassType = inputHandler.getPassTypeSelectingUserAction();

            StudyCafePasses studyCafePasses = StudyCafePasses.of(studyCafeFileHandler.readStudyCafePasses());

            if (studyCafePassType == StudyCafePassType.HOURLY) {
                List<StudyCafePass> hourlyPasses = studyCafePasses.getStudyCafePasses(StudyCafePassType.HOURLY);
                outputHandler.showPassListForSelection(hourlyPasses);

                StudyCafePass selectedPass = inputHandler.getSelectPass(hourlyPasses);
                outputHandler.showPassOrderSummary(selectedPass);
            } else if (studyCafePassType == StudyCafePassType.WEEKLY) {
                List<StudyCafePass> weeklyPasses = studyCafePasses.getStudyCafePasses(StudyCafePassType.WEEKLY);
                outputHandler.showPassListForSelection(weeklyPasses);

                StudyCafePass selectedPass = inputHandler.getSelectPass(weeklyPasses);
                outputHandler.showPassOrderSummary(selectedPass);
            } else if (studyCafePassType == StudyCafePassType.FIXED) {
                List<StudyCafePass> fixedPasses = studyCafePasses.getStudyCafePasses(StudyCafePassType.FIXED);
                outputHandler.showPassListForSelection(fixedPasses);

                StudyCafePass selectedPass = inputHandler.getSelectPass(fixedPasses);

                StudyCafeLockerPasses studyCafeLockerPasses = StudyCafeLockerPasses.of(studyCafeFileHandler.readLockerPasses());
                StudyCafeLockerPass lockerPass = studyCafeLockerPasses.findLockerPass(selectedPass.getPassType(), selectedPass.getDuration());

                outputHandler.askLockerPass(lockerPass);
                boolean lockerSelection = inputHandler.getLockerSelection();

                if (lockerSelection) {
                    outputHandler.showPassOrderSummary(selectedPass, lockerPass);
                } else {
                    outputHandler.showPassOrderSummary(selectedPass);
                }
            }
        } catch (AppException e) {
            outputHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

}
