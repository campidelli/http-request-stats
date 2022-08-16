package campidelli.http.request.stats.service;

public class LogService {

    public void log(String log) {
        System.out.println(log);
    }

    public void log(String format, Object... args) {
        log(String.format(format, args));
    }
}
