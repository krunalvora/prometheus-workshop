package com.github.krunalvora.prometheus;

import io.prometheus.client.exporter.HTTPServer;
import java.io.IOException;


public class Main {

  static final String host = "localhost";
  static final int dataPort = 8080;
  static final int promPort = 8081;
  static HTTPServer httpServer;
  static SimpleSocketServer socketServer;

  public static void main(String[] args) {

    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
      try {
        socketServer.stopServer();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }));

    // Start prometheus server
    try {
      httpServer = new HTTPServer(promPort);
    } catch (IOException io) {
      System.out.println("IOException while trying to start the prometheus server...\n" + io);
      System.exit(1);
    }

    // Register metrics with the prometheus server
    Metrics.registerMetrics();

    System.out.println("\nOn a separate terminal console, enter request durations using 'nc localhost 8080'");
    System.out.println(String.format("You can view the exposed prometheus metrics using: curl %s:%d", host, promPort));

    // Start the socket server
    socketServer = new SimpleSocketServer(dataPort);
    socketServer.startServer();
  }
}

