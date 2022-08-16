package campidelli.http.request.stats.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

@RunWith(JUnit4.class)
public class ReaderServiceTest {

    private static final String DEFAULT_FILE_PATH = "programming-task-test-data.log";

    @InjectMocks
    private ReaderService readerService = new ReaderService();

    @Test
    public void test_readAllLines_successful() {
        // GIVEN that the log file is available and readable
        String filePath = DEFAULT_FILE_PATH;
        // WHEN reading all the lines is called
        List<String> allLines = readerService.readAllLines(filePath);
        // THEN it contains 23 lines
        assertNotNull(allLines);
        assertEquals(23, allLines.size());
    }

    @Test(expected = RuntimeException.class)
    public void test_readAllLines_error() {
        // GIVEN that the log file is not available and readable
        String filePath = "invalidFile123.log";
        // WHEN reading all the lines is called
        readerService.readAllLines(filePath);
        // THEN it throws a RuntimeException
        fail("Should have thrown a RuntimeException.");
    }
}
