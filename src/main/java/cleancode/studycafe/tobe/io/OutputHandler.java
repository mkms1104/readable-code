package cleancode.studycafe.tobe.io;

import cleancode.studycafe.tobe.model.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.StudyCafePass;

import java.util.List;

public class OutputHandler {

    public void showWelcomeMessage() {
        showSimpleMessage("*** 프리미엄 스터디카페 ***");
    }

    public void showAnnouncement() {
        showSimpleMessage("* 사물함은 고정석 선택 시 이용 가능합니다. (추가 결제)");
        showSimpleMessage("* !오픈 이벤트! 2주권 이상 결제 시 10% 할인, 12주권 결제 시 15% 할인! (결제 시 적용)");
        System.out.println();
    }

    public void askPassTypeSelection() {
        showSimpleMessage("사용하실 이용권을 선택해 주세요.");
        showSimpleMessage("1. 시간 이용권(자유석) | 2. 주단위 이용권(자유석) | 3. 1인 고정석");
    }

    public void showPassListForSelection(List<StudyCafePass> passes) {
        System.out.println();
        showSimpleMessage("이용권 목록");
        for (int index = 0; index < passes.size(); index++) {
            StudyCafePass pass = passes.get(index);
            showSimpleMessage(String.format("%s. ", index + 1) + pass.display());
        }
    }

    public void askLockerPass(StudyCafeLockerPass lockerPass) {
        System.out.println();
        String askMessage = String.format(
            "사물함을 이용하시겠습니까? (%s)",
            lockerPass.display()
        );

        showSimpleMessage(askMessage);
        showSimpleMessage("1. 예 | 2. 아니오");
    }

    public void showPassOrderSummary(StudyCafePass selectedPass, StudyCafeLockerPass lockerPass) {
        System.out.println();
        showSimpleMessage("이용 내역");
        showSimpleMessage("이용권: " + selectedPass.display());
        showSimpleMessage("사물함: " + lockerPass.display());

        if (selectedPass.hasEventDiscount()) {
            showSimpleMessage("이벤트 할인 금액: " + selectedPass.getDiscountPrice() + "원");
        }

        int totalPrice = selectedPass.getTotalPrice() + lockerPass.getPrice();
        showSimpleMessage("총 결제 금액: " + totalPrice + "원");
        System.out.println();
    }

    public void showPassOrderSummary(StudyCafePass selectedPass) {
        System.out.println();
        showSimpleMessage("이용 내역");
        showSimpleMessage("이용권: " + selectedPass.display());

        if (selectedPass.hasEventDiscount()) {
            showSimpleMessage("이벤트 할인 금액: " + selectedPass.getDiscountPrice() + "원");
        }

        int totalPrice = selectedPass.getTotalPrice();
        showSimpleMessage("총 결제 금액: " + totalPrice + "원");
        System.out.println();
    }

    public void showSimpleMessage(String message) {
        System.out.println(message);
    }

}
