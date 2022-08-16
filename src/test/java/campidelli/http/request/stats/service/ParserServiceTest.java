package campidelli.http.request.stats.service;

import campidelli.http.request.stats.model.HttpRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ParserServiceTest {

    private static final List<String> LOG_LINES = Arrays.asList(
        "177.71.128.21 - - [10/Jul/2018:22:21:28 +0200] \"GET /intranet-analytics/ HTTP/1.1\" 200 3574 \"-\" \"Mozilla/5.0 (X11; U; Linux x86_64; fr-FR) AppleWebKit/534.7 (KHTML, like Gecko) Epiphany/2.30.6 Safari/534.7\"\n",
        "168.41.191.40 - - [09/Jul/2018:10:11:30 +0200] \"GET http://example.net/faq/ HTTP/1.1\" 200 3574 \"-\" \"Mozilla/5.0 (Linux; U; Android 2.3.5; en-us; HTC Vision Build/GRI40) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1\""
    );

    @Captor
    private ArgumentCaptor<String> messageArgCaptor;
    @Captor
    private ArgumentCaptor<String> paramArgCaptor;

    @Mock
    private LogService logService;

    @InjectMocks
    private ParserService parserService;

    @Test
    public void test_parseSingleLine_successful() {
        // GIVEN that the log line is valid
        String line = LOG_LINES.get(0);
        // WHEN parsing is called
        Optional<HttpRequest> httpRequest = parserService.parse(line);
        // THEN it is valid and contains the correct values
        assertTrue(httpRequest.isPresent());
        assertEquals("177.71.128.21", httpRequest.get().getIp());
        assertEquals("/intranet-analytics/", httpRequest.get().getUrl());
    }

    @Test
    public void test_parseMultipleLines_successful() {
        // GIVEN that the log lines are valid
        // WHEN parsing is called
        List<HttpRequest> httpRequests = parserService.parse(LOG_LINES);
        // THEN they are valid and contain the correct values
        assertNotNull(httpRequests);
        assertEquals(2, httpRequests.size());
        assertEquals("177.71.128.21", httpRequests.get(0).getIp());
        assertEquals("/intranet-analytics/", httpRequests.get(0).getUrl());
        assertEquals("168.41.191.40", httpRequests.get(1).getIp());
        assertEquals("http://example.net/faq/", httpRequests.get(1).getUrl());
    }

    @Test
    public void test_parseSingleLine_unsuccessful() {
        // GIVEN that the log line is invalid
        String line = "ABC 123";
        // WHEN parsing is called
        Optional<HttpRequest> httpRequest = parserService.parse(line);
        // THEN it is empty
        assertFalse(httpRequest.isPresent());
        // AND it has logged the correct message
        verify(logService).log(messageArgCaptor.capture(), paramArgCaptor.capture());
        assertEquals("Line could not be parsed, ignoring. %s.", messageArgCaptor.getValue());
        assertEquals(line, paramArgCaptor.getValue());
    }
}
