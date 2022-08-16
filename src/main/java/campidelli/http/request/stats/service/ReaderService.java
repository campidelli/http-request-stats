package campidelli.http.request.stats.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ReaderService {

    private final LogService logService;

    public ReaderService(LogService logService) {
        this.logService = logService;
    }

    public ReaderService() {
        this.logService = new LogService();
    }

    public List<String> readAllLines(String filePath) {
        logService.log("Reading all lines in %s", filePath);
        try {
            Path path = Paths.get(filePath);
            if (!path.toFile().exists()) {
                path = Paths.get(getClass().getClassLoader().getResource(filePath).toURI());
            }
            return Files.readAllLines(path);
        } catch (Exception e) {
            logService.log("An error occurred when reading %s: %s", filePath, e);
            throw new RuntimeException(filePath + " can't be read.", e);
        }
    }
}
