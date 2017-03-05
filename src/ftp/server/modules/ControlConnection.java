package ftp.server.modules;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ControlConnection extends Thread {
	
	private Socket connection;
	private BufferedReader reader;
	private PrintWriter writer;
	private boolean running = true;
	
	public ControlConnection(Socket connection) throws IOException, InterruptedException {
		this.connection = connection;
		this.reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		this.writer = new PrintWriter(connection.getOutputStream(), true);
		System.out.println("Creating new control connection");
		System.out.println(this.getState());
	}
	
	public void run() {
		try {
			while(!Thread.currentThread().isInterrupted() && running) {
				String command = reader.readLine();
				switch(command) {
					case "hello":
						System.out.println("Hello Command Invoke");
						writer.println("Hello Command Invoke Done");
						writer.flush();
					break;
					case "exit":
						System.out.println("exiting ...");
						writer.println("close connection");
						writer.flush();
						connection.close();
						running = false;
					break;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.printf("done pid: %d\n", this.getId());
	}
}
