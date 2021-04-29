package com.github.krunalvora.prometheus;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleSocketServer extends Thread{
  public ServerSocket serverSocket;
  public int port;
  public RequestHandler requestHandler;
  public boolean running = false;

  public SimpleSocketServer(int port) {
    this.port = port;
  }

  public void startServer() {
    try {
      serverSocket = new ServerSocket(port);
      this.start();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void stopServer() throws IOException {
    System.out.println("\nStopping SocketServer and cleaning up resources...\n");
    serverSocket.close();
  }

  @Override
  public void run() {
    running = true;
    while (running) {
      try {

        Socket socket = serverSocket.accept();
        requestHandler = new RequestHandler(socket);
        requestHandler.start();

      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
