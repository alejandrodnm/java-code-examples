package codeexamples.tcpserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
  public static String makeRequest() throws IOException {
    String response;
    try (var socket = new Socket("localhost", 6789);
        final var bufferReader =
            new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
      final var out = new PrintWriter(socket.getOutputStream(), true);
      out.println("PUT 1 stan is awesome");
      response = bufferReader.readLine();
    }
    return response;
  }
}
