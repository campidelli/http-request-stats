package campidelli.http.request.stats.model;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Stats {

    private List<HttpRequest> httpRequests;

    public Stats(List<HttpRequest> httpRequests) {
        this.httpRequests = httpRequests;
    }

    public int getNumberOfUniqueIPAddresses() {
        return httpRequests.stream()
                .collect(Collectors.groupingBy(HttpRequest::getIp, Collectors.counting()))
                .size();
    }

    public List<String> getTopThreeMostVisitedURLs() {
        return httpRequests.stream()
                .collect(Collectors.groupingBy(HttpRequest::getUrl, Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(3)
                .map(urlAndAmountEntry -> urlAndAmountEntry.getKey())
                .collect(Collectors.toList());
    }

    public List<String> getTopThreeMostActiveIPs() {
        return httpRequests.stream()
                .collect(Collectors.groupingBy(HttpRequest::getIp, Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(3)
                .map(ipAndAmountEntry -> ipAndAmountEntry.getKey())
                .collect(Collectors.toList());
    }
}
