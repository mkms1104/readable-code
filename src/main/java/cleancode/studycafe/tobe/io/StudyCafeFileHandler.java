package cleancode.studycafe.tobe.io;

import cleancode.studycafe.tobe.model.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.StudyCafePass;
import cleancode.studycafe.tobe.model.StudyCafePassType;
import cleancode.studycafe.tobe.pass.Pass;

import java.util.List;

public class StudyCafeFileHandler {

    public List<StudyCafePass> readStudyCafePasses() {
        List<String> lines = FileHandler.readAllLines("src/main/resources/cleancode/studycafe/pass-list.csv");

        return lines.stream()
                .map(line -> line.split(","))
                .map(values -> {
                    StudyCafePassType passType = StudyCafePassType.valueOf(values[0]);
                    int duration = Integer.parseInt(values[1]);
                    int price = Integer.parseInt(values[2]);
                    Pass pass = Pass.of(passType.getPassDurationType(), duration, price);

                    double discountRate = Double.parseDouble(values[3]);
                    
                    return StudyCafePass.of(passType, pass, discountRate);
                })
                .toList();
    }

    public List<StudyCafeLockerPass> readLockerPasses() {
        List<String> lines = FileHandler.readAllLines("src/main/resources/cleancode/studycafe/locker.csv");

        return lines.stream()
                .map(line -> line.split(","))
                .map(values -> {
                    StudyCafePassType passType = StudyCafePassType.valueOf(values[0]);
                    int duration = Integer.parseInt(values[1]);
                    int price = Integer.parseInt(values[2]);
                    Pass pass = Pass.of(passType.getPassDurationType(), duration, price);

                    return StudyCafeLockerPass.of(passType, pass);
                })
                .toList();
    }
}
