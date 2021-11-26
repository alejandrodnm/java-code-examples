package codeexamples.tcpserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;

public class Server {
  public void start() throws IOException {
    try (final var ss = new ServerSocket(6789);
        final var socket = ss.accept()) {
      System.out.println("Server Started...");
      while (true) {
        try (final var bufferReader =
            new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
          final var out = new PrintWriter(socket.getOutputStream(), true);
          final var request = bufferReader.readLine();
          final var response = handleRequest(request);
          out.println(response);
        }
      }
    }
  }

  private static String handleRequest(final String request) {
    return "response to request: " + request;
  }
}
