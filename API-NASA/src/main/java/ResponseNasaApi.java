import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseNasaApi {
    private String date;
    private String explanation;
    private String hdurl;
    private String mediaType;
    private String serviceVersion;
    private String title;
    private String url;

    public ResponseNasaApi(@JsonProperty("date") String date,
                           @JsonProperty("explanation")String explanation,
                           @JsonProperty("hdurl")String hdurl,
                           @JsonProperty("media_type")String mediaType,
                           @JsonProperty("service_version")String serviceVersion,
                           @JsonProperty("title")String title,
                           @JsonProperty("url")String url) {
        this.date = date;
        this.explanation = explanation;
        this.hdurl = hdurl;
        this.mediaType = mediaType;
        this.serviceVersion = serviceVersion;
        this.title = title;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "ResponseNasaApi{" +
                "date='" + date + '\'' +
                ", explanation='" + explanation + '\'' +
                ", hdurl='" + hdurl + '\'' +
                ", mediaType='" + mediaType + '\'' +
                ", serviceVersion='" + serviceVersion + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
