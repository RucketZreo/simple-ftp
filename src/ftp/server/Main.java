package ftp.server;

import java.io.IOException;
import java.net.UnknownHostException;

import ftp.server.modules.Server;


public class Main {
	public final static int PrivilegedPorNum = 1024;

	public static void main(String [] args) {
		try {
			System.out.printf("current pid: %d\n", Thread.currentThread().getId());
			Server server = new Server("localhost", PrivilegedPorNum + 21);
			server.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
