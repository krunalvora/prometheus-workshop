package com.github.krunalvora.prometheus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import io.prometheus.client.exporter.HTTPServer;

public class Main {

  static final String host = "localhost";
  static final int socketPort = 8080;
  static final int promPort = 8081;
  static HTTPServer httpServer;
  static Socket socket;
  static InputStreamReader inputStreamReader;
  static BufferedReader bufferedReader;

  public static void main(String[] args) {

    // Setting up Socket to read input from to test prometheus metrics
    try {
      socket = new Socket(host, socketPort);
      inputStreamReader = new InputStreamReader(socket.getInputStream());
      bufferedReader = new BufferedReader(inputStreamReader);
    } catch (UnknownHostException e) {
      System.err.println("Don't know about host: " + host);
      System.exit(1);
    } catch (IOException io) {
      System.out.println(String.format("No server listening on :%s, %d", host, socketPort));
      System.out.println(String.format("Initiate a listener using: nc -l %d", socketPort));
      System.exit(1);
    }

    // Start prometheus server
    try {
      httpServer = new HTTPServer(promPort);
    } catch (IOException io) {
      System.out.println("IOException while trying to start the prometheus server...\n" + io);
      System.exit(1);
    }

    // Register metrics with the prometheus server
    Metrics.registerMetrics();

    System.out.println("View the exposed prometheus metrics using: ");
    System.out.println(String.format("curl %s:%d", host, promPort));

    // Instrument requests through input socket
    instrumentRequests();
  }



  public static void instrumentRequests() {
    String userInput = null;
    Double duration;
    try {
      while ((userInput = bufferedReader.readLine()) != null) {
        if (Util.isNumeric(userInput)) {
          duration = Double.parseDouble(userInput);
          // System.out.println("User Input: " + userInput);
          Metrics.requestsTotal.inc();
          Metrics.requestsDuration.observe(duration);
        } else {
          System.out.println("Invalid duration input...\n");
        }
      }
    } catch (IOException io) {
      System.out.println("IOException while testing Histogram metrics...\n" + io);
    } finally {
      close();
    }
  }


  public static void close() {
    try {
      inputStreamReader.close();
      bufferedReader.close();
      socket.close();
      httpServer.stop();
    } catch (IOException io) {
      System.out.println("Exception while trying to close: \n" + io);
    }

  }

}

