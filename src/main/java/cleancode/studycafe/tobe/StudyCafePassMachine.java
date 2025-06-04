package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.exception.AppException;
import cleancode.studycafe.tobe.io.InputHandler;
import cleancode.studycafe.tobe.io.OutputHandler;
import cleancode.studycafe.tobe.io.StudyCafeFileHandler;
import cleancode.studycafe.tobe.model.*;

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

            List<StudyCafePass> passes = getStudyCafePasses(studyCafePassType);
            outputHandler.showPassListForSelection(passes);

            StudyCafePass selectedPass = inputHandler.getSelectPass(passes);
            outputHandler.showPassOrderSummary(selectedPass);

            if(studyCafePassType.isUsableLocker()) {
                StudyCafeLockerPass lockerPass = getLockerPass(selectedPass.getPassType(), selectedPass.getDuration());

                outputHandler.askLockerPass(lockerPass);
                boolean lockerSelection = inputHandler.getLockerSelection();

                if (lockerSelection) {
                    outputHandler.showPassOrderSummary(selectedPass, lockerPass);
                    return;
                }
            }

            outputHandler.showPassOrderSummary(selectedPass);
        } catch (AppException e) {
            outputHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

    private List<StudyCafePass> getStudyCafePasses(StudyCafePassType passType) {
        StudyCafePasses studyCafePasses = StudyCafePasses.of(studyCafeFileHandler.readStudyCafePasses());
        return studyCafePasses.findStudyCafePassesBy(passType);
    }

    private StudyCafeLockerPass getLockerPass(StudyCafePassType passType, int duration) {
        StudyCafeLockerPasses studyCafeLockerPasses = StudyCafeLockerPasses.of(studyCafeFileHandler.readLockerPasses());
        return studyCafeLockerPasses.findLockerPassBy(passType, duration);
    }
}
