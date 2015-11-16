package com.redmintie.nac;

public class Main {
	public static void main(String[] args) {
		Input.init();
		boolean debug = Input.getBoolInput("Do you want to enabled debug?");
		Game.init(createPlayer("Player 1", 1, debug), createPlayer("Player 2", 2, debug));
		Game.run();
	}
	public static Player createPlayer(String name, int piece, boolean debug) {
		if (Input.getBoolInput("Do you want " + name + " to be a computer?")) {
			return new Computer(name, piece, debug);
		}
		return new Player(name, piece);
	}
}