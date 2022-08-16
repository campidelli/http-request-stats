package campidelli.http.request.stats.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class StatsTest {

    @Test
    public void test_getNumberOfUniqueIPAddresses() {
        // GIVEN two unique IP addresses in a list of four
        List<HttpRequest> httpRequests = Arrays.asList(
                new HttpRequest().setIp("168.41.191.40"),
                new HttpRequest().setIp("79.125.00.21"),
                new HttpRequest().setIp("168.41.191.40"),
                new HttpRequest().setIp("79.125.00.21")
        );
        // WHEN this statistic is requested
        int numberOfUniqueIPAddresses = new Stats(httpRequests).getNumberOfUniqueIPAddresses();
        // THEN the value is 2
        assertEquals(2, numberOfUniqueIPAddresses);
    }

    @Test
    public void test_getTopThreeMostVisitedURLs() {
        // GIVEN one URL is visited two times and the others only one
        List<HttpRequest> httpRequests = Arrays.asList(
                new HttpRequest().setUrl("/about"),
                new HttpRequest().setUrl("/home"),
                new HttpRequest().setUrl("/careers"),
                new HttpRequest().setUrl("/home"),
                new HttpRequest().setUrl("/contact")
        );
        // WHEN this statistic is requested
        List<String> topThreeMostVisitedURLs = new Stats(httpRequests).getTopThreeMostVisitedURLs();
        // THEN the values are /home, /about and /careers
        assertEquals(3, topThreeMostVisitedURLs.size());
        assertEquals("/home", topThreeMostVisitedURLs.get(0));
        assertEquals("/about", topThreeMostVisitedURLs.get(1));
        assertEquals("/careers", topThreeMostVisitedURLs.get(2));
    }

    @Test
    public void test_getTopThreeMostActiveIPs() {
        // GIVEN one IP is visited three times, another two times and the others only once
        List<HttpRequest> httpRequests = Arrays.asList(
                new HttpRequest().setIp("50.112.00.11"),
                new HttpRequest().setIp("168.41.191.40"),
                new HttpRequest().setIp("79.125.00.21"),
                new HttpRequest().setIp("168.41.191.40"),
                new HttpRequest().setIp("168.41.191.40"),
                new HttpRequest().setIp("50.112.00.11")
        );
        // WHEN this statistic is requested
        List<String> topThreeMostActiveIPs = new Stats(httpRequests).getTopThreeMostActiveIPs();
        // THEN the values are 168.41.191.40, 50.112.00.11 and 79.125.00.21
        assertEquals(3, topThreeMostActiveIPs.size());
        assertEquals("168.41.191.40", topThreeMostActiveIPs.get(0));
        assertEquals("50.112.00.11", topThreeMostActiveIPs.get(1));
        assertEquals("79.125.00.21", topThreeMostActiveIPs.get(2));
    }
}
