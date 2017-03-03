package ftp.server.modules;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Map;

public class Server {
	
	private Integer port;
	private String inetAddr;
	private ServerSocket listener;
	
	public Server(String addr, Integer port) {
		this.port = port;
		this.inetAddr = addr;
	}
	
	public Integer getPort() {
		return this.port;
	}
	
	public String getInetAddr() {
		return this.inetAddr;
	}
	
	private void controlConnectionLoop() throws UnknownHostException, IOException {
		while (true) {
			Socket socket = listener.accept();
			try {
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				out.println(new Date().toString());
			} finally {
				socket.close();
			}
		}
	}
	
	public void start() throws UnknownHostException, IOException {
		try {
			this.listener = new ServerSocket();
			listener.setReuseAddress(true);
			listener.bind(new InetSocketAddress(port));
			System.out.printf("Server listening on port %d\n", port);
			controlConnectionLoop();
		}
		finally {
			listener.close();
		}
	}
}
