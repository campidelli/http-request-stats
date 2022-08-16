package campidelli.http.request.stats;

import campidelli.http.request.stats.model.HttpRequest;
import campidelli.http.request.stats.model.Stats;
import campidelli.http.request.stats.service.LogService;
import campidelli.http.request.stats.service.ParserService;
import campidelli.http.request.stats.service.ReaderService;

import java.util.List;

public class App {

    private final ReaderService readerService;
    private final ParserService parserService;
    private final LogService logService;

    public App(ReaderService readerService, ParserService parserService, LogService logService) {
        this.readerService = readerService;
        this.parserService = parserService;
        this.logService = logService;
    }

    public App() {
        this.readerService = new ReaderService();
        this.parserService = new ParserService();
        this.logService = new LogService();
    }

    public Stats loadHttpRequestStats(String filePath) {
        List<String> allLines = readerService.readAllLines(filePath);
        List<HttpRequest> httpRequests = parserService.parse(allLines);
        return new Stats(httpRequests);
    }

    public void showHttpRequestStats(Stats stats) {
        logService.log("Statistics:");
        logService.log("The number of unique IP addresses.: %s", stats.getNumberOfUniqueIPAddresses());
        logService.log("The top 3 most visited URLs.......: %s", stats.getTopThreeMostVisitedURLs());
        logService.log("The top 3 most active IP addresses: %s", stats.getTopThreeMostActiveIPs());
    }

    public static void main(String[] args) {
        if (args == null || args.length == 0) {
            throw new IllegalArgumentException("The first argument must be the file path. None has been informed.");
        }
        App app = new App();
        Stats stats = app.loadHttpRequestStats(args[0]);
        app.showHttpRequestStats(stats);
    }
}
