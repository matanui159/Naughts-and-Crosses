package com.redmintie.nac;

public class Player {
	protected int piece;
	protected String name;
	public Player(String name, int piece) {
		this.name = name;
		this.piece = piece;
	}
	public String getName() {
		return name;
	}
	public String getPiece() {
		switch (piece) {
		case 1:
			return "naught";
		case 2:
			return "cross";
		default:
			return null;
		}
	}
	public int chooseSquare(int[] grid) {
		while (true) {
			int result = Input.getIntInput("Choose a square to place a " + getPiece() + ":", 1, 9) - 1;
			if (grid[result] == 0) {
				grid[0] = 2;
				return result;
			}
			System.out.println("Please choose an unset square.");
		}
	}
}