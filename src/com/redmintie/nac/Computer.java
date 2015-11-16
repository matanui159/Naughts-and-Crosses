package com.redmintie.nac;

import java.util.Arrays;

public class Computer extends Player {
	private boolean debug;
	public Computer(String name, int piece, boolean debug) {
		super(name, piece);
		this.debug = debug;
	}
	@Override
	public String getName() {
		return name + " (CPU)";
	}
	@Override
	public int chooseSquare(int[] grid) {
		boolean[] squares = chooseSquares(grid, piece, debug);
		int square = -1;
		int score = 0;
		for (int i = 0; i < 9; i++) {
			if (squares[i]) {
				int s = getScore(grid, i, piece);
				if (debug) {
					System.out.println("[DEBUG] SCORE OF " + (i + 1) + " IS " + s);
				}
				if (square == -1 || s > score) {
					square = i;
					score = s;
				}
			}
		}
		try {
			Thread.sleep(1000);
		} catch (Exception ex) {}
		System.out.println(getName() + " placed a " + getPiece() + " at square " + (square + 1) + ".");
		return square;
	}
	private int getScore(int[] grid, int square, int piece) {
		int score = 0;
		grid[square] = piece;
		if (Game.getWinner(grid) != null) {
			if (piece == this.piece) {
				score++;
			} else {
				score--;
			}
		} else if (!Game.isTied(grid)) {
			if (piece == 1) {
				piece = 2;
			} else {
				piece = 1;
			}
			boolean[] squares = chooseSquares(grid, piece, false);
			for (int i = 0; i < 9; i++) {
				if (squares[i]) {
					score += getScore(grid, i, piece);
				}
			}
		}
		grid[square] = 0;
		return score;
	}
	private boolean[] chooseSquares(int[] grid, int piece, boolean debug) {
		boolean[] squares = new boolean[9];
		Arrays.fill(squares, false);
		boolean found = false;
		
//		Looks for instant wins
		for (int[] win : Game.WINS) {
			int s = -1;
			for (int i = 0; i < 9; i++) {
				if (win[i] == 1 && grid[i] == 0) {
					if (s == -1) {
						s = i;
					} else {
						s = -1;
						break;
					}
				} else if (win[i] == 1 && grid[i] != piece) {
					s = -1;
					break;
				}
			}
			if (s != -1) {
				squares[s] = true;
				found = true;
			}
		}
		if (found) {
			if (debug) {
				System.out.println("[DEBUG] FOUND INSTANT WIN");
			}
			return squares;
		}
		
//		Looks for instant loses
		for (int[] win : Game.WINS) {
			int s = -1;
			for (int i = 0; i < 9; i++) {
				if (win[i] == 1 && grid[i] == 0) {
					if (s == -1) {
						s = i;
					} else {
						s = -1;
						break;
					}
				} else if (win[i] == 1 && grid[i] == piece) {
					s = -1;
					break;
				}
			}
			if (s != -1) {
				squares[s] = true;
				found = true;
			}
		}
		if (found) {
			if (debug) {
				System.out.println("[DEBUG] FOUND INSTANT LOSS");
			}
			return squares;
		}
		
//		Takes center spot
		if (grid[4] == 0) {
			squares[4] = true;
			found = true;
		}
		if (found) {
			if (debug) {
				System.out.println("[DEBUG] FOUND CENTER SPOT");
			}
			return squares;
		}
		
//		Advances possible wins
		for (int[] win : Game.WINS) {
			boolean blocked = false;
			boolean used = false;
			for (int i = 0; i < 9; i++) {
				if (win[i] == 1 && grid[i] == piece) {
					used = true;
				} else if (win[i] == 1 && grid[i] != 0) {
					blocked = true;
					break;
				}
			}
			if (used && !blocked) {
				for (int i = 0; i < 9; i++) {
					if (win[i] == 1 && grid[i] == 0) {
						squares[i] = true;
					}
				}
				found = true;
			}
		}
		if (found) {
			if (debug) {
				System.out.println("[DEBUG] FOUND POSSIBLE ADVANCES");
			}
			return squares;
		}
		
//		Chooses all possible squares
		for (int i = 0; i < 9; i++) {
			if (grid[i] == 0) {
				squares[i] = true;
			}
		}
		if (debug) {
			System.out.println("[DEBUG] FOUND ALL POSSIBLE SQUARES");
		}
		return squares;
	}
}