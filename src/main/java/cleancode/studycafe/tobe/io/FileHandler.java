package cleancode.studycafe.tobe.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public final class FileHandler {

    private FileHandler() {
    }

    public static List<String> readAllLines(Path path) {
        try {
            List<String> lines = Files.readAllLines(path);
            return lines;
        } catch (IOException e) {
            throw new RuntimeException("파일을 읽는데 실패했습니다.", e);
        }
    }

    public static List<String> readAllLines(String pathString) {
        return readAllLines(Paths.get(pathString));
    }
}
