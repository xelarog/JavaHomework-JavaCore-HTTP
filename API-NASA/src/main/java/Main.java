import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import java.io.*;

public class Main {
    public static final String REMOTE_SERVICE_URI = "https://api.nasa.gov/planetary/apod?api_key=AGNZpx8xqBCU0njr7sBQkjoWjPdBHfQcqbVNkWqa";
    public static final ObjectMapper MAPPER = new ObjectMapper();

    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)
                        .setSocketTimeout(30000)
                        .setRedirectsEnabled(false)
                        .build())
                .build();

        HttpGet request = new HttpGet(REMOTE_SERVICE_URI);
        CloseableHttpResponse response = httpClient.execute(request);
        ResponseNasaApi responseNasaApi = MAPPER.readValue(response.getEntity().getContent(), new TypeReference<>() {
        });

        String url = responseNasaApi.getUrl();
        HttpGet request2 = new HttpGet(url);
        CloseableHttpResponse response2 = httpClient.execute(request2);

        String fileName = url.substring(url.lastIndexOf('/') + 1);
        writeFile(response2, fileName);


    }

    private static void writeFile(CloseableHttpResponse response, String fileName) throws IOException {

        byte[] buffer = response.getEntity().getContent().readAllBytes();
        try (FileOutputStream out = new FileOutputStream(fileName);
             BufferedOutputStream bos = new BufferedOutputStream(out)) {
            bos.write(buffer, 0, buffer.length);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
