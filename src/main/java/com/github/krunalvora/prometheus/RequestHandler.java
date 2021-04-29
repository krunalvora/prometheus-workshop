package com.github.krunalvora.prometheus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class RequestHandler extends Thread{

  Socket socket;
  InputStreamReader inputStreamReader;
  BufferedReader bufferedReader;

  RequestHandler(Socket socket) {
    this.socket = socket;
  }

  @Override
  public void run() {
    String userInput = null;
    Double duration;

    try {
      inputStreamReader = new InputStreamReader(socket.getInputStream());
      bufferedReader = new BufferedReader(inputStreamReader);

      while ((userInput = bufferedReader.readLine()) != null) {

        if (Util.isNumeric(userInput)) {
          duration = Double.parseDouble(userInput);
          Metrics.requestsTotal.inc();
          Metrics.requestsDuration.observe(duration);
        } else {
          System.out.println("Invalid duration input. Please enter request duration in seconds.");
        }

      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void cleanup() throws IOException {
    inputStreamReader.close();
    bufferedReader.close();
    socket.close();
  }
}
