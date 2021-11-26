package codeexamples.http;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.mockserver.model.JsonBody.json;
import static org.mockserver.model.MediaType.APPLICATION_JSON;

import codeexamples.http.Client.BadStatusCodeException;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockserver.client.MockServerClient;
import org.mockserver.junit.jupiter.MockServerExtension;
import org.mockserver.junit.jupiter.MockServerSettings;
import org.mockserver.matchers.MatchType;
import org.mockserver.verify.VerificationTimes;

/** ClientTest */
@ExtendWith(MockServerExtension.class)
@MockServerSettings(ports = {8080})
public class ClientTest {

  @Test
  void testSomething(MockServerClient mockServerClient)
      throws IOException, InterruptedException, BadStatusCodeException {
    final var request =
        request()
            .withContentType(APPLICATION_JSON)
            .withMethod("POST")
            .withPath("/app")
            .withBody(json("{username: 'ainara'}", MatchType.STRICT));
    mockServerClient.when(request).respond(response().withStatusCode(201).withBody("{}"));

    final var response = Client.makeRequest();
    assertEquals("{}", response);

    mockServerClient.verify(request, VerificationTimes.once());
  }
}
