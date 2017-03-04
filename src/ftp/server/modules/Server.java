package ftp.server.modules;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
	
	private void controlConnectionLoop() throws UnknownHostException, IOException, InterruptedException {
		ExecutorService pool = Executors.newFixedThreadPool(100);
		while (!Thread.currentThread().isInterrupted()) {
			pool.submit(new ControlConnection(listener.accept()));
		}
		pool.shutdown();
	}
	
	public void start() throws UnknownHostException, IOException, InterruptedException {
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
