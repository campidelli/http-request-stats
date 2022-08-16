package campidelli.http.request.stats.model;

public class HttpRequest {

    private String ip;
    private String url;

    public String getIp() {
        return ip;
    }

    public HttpRequest setIp(String ip) {
        this.ip = ip;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public HttpRequest setUrl(String url) {
        this.url = url;
        return this;
    }
}
