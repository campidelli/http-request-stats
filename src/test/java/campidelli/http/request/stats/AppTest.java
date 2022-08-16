package campidelli.http.request.stats;

import campidelli.http.request.stats.model.Stats;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class AppTest {

    private App app = new App();

    @Test
    public void test_loadAndShowHttpRequestStats_successful() {
        // GIVEN that there is a valid log file
        String filePath = "programming-task-test-data.log";
        // WHEN the statistics are loaded
        Stats stats = app.loadHttpRequestStats(filePath);
        // THEN it has calculated the correct values
        assertNotNull(stats);
        assertEquals(11, stats.getNumberOfUniqueIPAddresses());
        assertEquals(3, stats.getTopThreeMostVisitedURLs().size());
        assertContains(stats.getTopThreeMostVisitedURLs(),
                "/docs/manage-websites/", "/blog/2018/08/survey-your-opinion-matters/", "/newsletter/");
        assertEquals(3, stats.getTopThreeMostVisitedURLs().size());
        assertContains(stats.getTopThreeMostActiveIPs(),
                "168.41.191.40", "50.112.00.11", "177.71.128.21");
    }

    private static void assertContains(List<String> list, String... values) {
        for (int i = 0; i < values.length; i++) {
            assertTrue(list.contains(values[i]));
        }
    }
}
