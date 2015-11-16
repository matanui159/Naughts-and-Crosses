package com.redmintie.nac;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Input {
	private static BufferedReader reader;
	public static void init() {
		try {
			reader = new BufferedReader(new InputStreamReader(System.in));
		} catch (Exception ex) {
			error("Could not initialise input.");
		}
	}
	private static void error(String msg) {
		System.out.println(msg);
		System.exit(1);
	}
	public static String getStringInput(String prompt) {
		if (reader == null) {
			error("Input has not been initialised.");
		}
		System.out.print(prompt.trim() + " ");
		try {
			return reader.readLine().toLowerCase().trim();
		} catch (Exception ex) {
			error("Could not read input.");
		}
		return null;
	}
	public static int getIntInput(String prompt, int min, int max) {
		while (true) {
			try {
				int result = Integer.parseInt(getStringInput(prompt));
				if (result >= min && result <= max) {
					return result;
				}
				System.out.println("The number must be inbetween " + min + " and " + max + " inclusive.");
			} catch (Exception ex) {
				System.out.println("Please enter a number.");
			}
		}
	}
	public static boolean getBoolInput(String prompt) {
		while (true) {
			String result = getStringInput(prompt);
			if (result.startsWith("y") || result.startsWith("n")) {
				return result.startsWith("y");
			}
			System.out.println("Please enter either yes or no.");
		}
	}
}