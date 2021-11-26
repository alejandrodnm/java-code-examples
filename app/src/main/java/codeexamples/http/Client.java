package codeexamples.http;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class Client {

  private static final HttpClient httpClient = HttpClient.newHttpClient();

  public static String makeRequest()
      throws IOException, InterruptedException, BadStatusCodeException {
    final var payload = "{\"username\":\"ainara\"}";
    final var requestBuilder =
        HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:8080/app"))
            .timeout(Duration.ofSeconds(10))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(payload));

    final var response =
        httpClient.send(requestBuilder.build(), HttpResponse.BodyHandlers.ofString());

    if (response.statusCode() > 300) {
      throw new BadStatusCodeException(response.statusCode(), response.body());
    }
    return response.body();
  }

  public static class BadStatusCodeException extends Exception {
    public BadStatusCodeException(int statusCode, String body) {
      super(String.format("status code: '%d' - response: '%s'", statusCode, body));
    }
  }
}
