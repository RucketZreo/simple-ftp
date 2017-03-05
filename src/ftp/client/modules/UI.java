package ftp.client.modules;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UI {
	public static String version = "0.0.1";

	public static boolean running = true;
	
	public static void welcome() {
		System.out.println("Welcome to simple-ftp.");
		System.out.println("It's still lab tony");
	}
	
	public static void init() {
		welcome();
		menu();
	}
	
	private static void menu() {
		Scanner inputSrc = new Scanner(System.in);
		String command;
		System.out.printf(">>> ");
		while (running && (command = inputSrc.nextLine()) != null) {
			switch(command.toLowerCase()) {
			  case "ls": ls(); break;
			  case "quit": quit(); break;
			  case "env": env(); break;
			  default: commandMissing(); break;
			}
			System.out.printf(">>> ");
		}
	}
	
	private static void ftp(String command) {
		System.out.println(command);
	}
	
	private static void ls() {
		System.out.println("Invoke ls");
	}
	
	private static void quit() {
		running = false;
		System.out.println("Invoke quit");
	}
	
	private static void env() {
		System.out.println("Invoke env");
	}
	
	private static void commandMissing() {
		System.out.println("Command Missing");
	}
}
