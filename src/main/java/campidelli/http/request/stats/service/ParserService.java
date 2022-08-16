package campidelli.http.request.stats.service;

import campidelli.http.request.stats.model.HttpRequest;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ParserService {

    private static final int IP_GROUP = 1;
    private static final int URL_GROUP = 2;

    private final LogService logService;
    private final Pattern pattern = Pattern.compile("(\\d+\\.\\d+\\.\\d+\\.\\d+).+GET\\s(.*)\\sHTTP");

    public ParserService(LogService logService) {
        this.logService = logService;
    }

    public ParserService() {
        this.logService = new LogService();
    }

    public List<HttpRequest> parse(List<String> httpRequestLogs) {
        logService.log("Parsing %s logs.", httpRequestLogs.size());
        return httpRequestLogs
                .stream()
                .map(log -> parse(log))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    public Optional<HttpRequest> parse(String httpRequestLog) {
        Matcher matcher = pattern.matcher(httpRequestLog);
        if (matcher.find()) {
            String ip = matcher.group(IP_GROUP);
            String url = matcher.group(URL_GROUP);
            logService.log("Log parsed successfully for IP %s and URL %s", ip, url);
            return Optional.of(new HttpRequest().setIp(ip).setUrl(url));
        }
        logService.log("Line could not be parsed, ignoring. %s.", httpRequestLog);
        return Optional.empty();
    }
}
