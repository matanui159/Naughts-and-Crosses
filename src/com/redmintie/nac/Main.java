package com.redmintie.nac;

public class Main {
	public static void main(String[] args) {
		Input.init();
		Game.init(createPlayer("Player 1", 1), createPlayer("Player 2", 2));
		Game.run();
	}
	public static Player createPlayer(String name, int piece) {
		if (Input.getBoolInput("Do you want " + name + " to be a computer?")) {
			return new Computer(name, piece);
		}
		return new Player(name, piece);
	}
}