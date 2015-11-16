package com.redmintie.nac;

import java.util.Arrays;

public class Game {
	private static final int[] GRID = new int[9];
	public static final int[][] WINS = new int[][] {
		new int[] {
				1, 1, 1,
				0, 0, 0,
				0, 0, 0
		},
		new int[] {
				0, 0, 0,
				1, 1, 1,
				0, 0, 0
		},
		new int[] {
				0, 0, 0,
				0, 0, 0,
				1, 1, 1
		},
		new int[] {
				1, 0, 0,
				1, 0, 0,
				1, 0, 0
		},
		new int[] {
				0, 1, 0,
				0, 1, 0,
				0, 1, 0
		},
		new int[] {
				0, 0, 1,
				0, 0, 1,
				0, 0, 1
		},
		new int[] {
				1, 0, 0,
				0, 1, 0,
				0, 0, 1
		},
		new int[] {
				0, 0, 1,
				0, 1, 0,
				1, 0, 0
		}
	};
	private static Player p1;
	private static Player p2;
	private static final int[] CLONE = new int[9];
	public static void init(Player p1, Player p2) {
		Game.p1 = p1;
		Game.p2 = p2;
		Arrays.fill(GRID, 0);
	}
	public static void run() {
		while (true) {
			haveTurn(p1, 1);
			haveTurn(p2, 2);
		}
	}
	public static void haveTurn(Player player, int piece) {
		System.out.println();
		System.out.println(player.getName() + "'s turn.");
		displayGrid(GRID);
		
		for (int i = 0; i < 9; i++) {
			CLONE[i] = GRID[i];
		}
		GRID[player.chooseSquare(CLONE)] = piece;
		
		if (getWinner(GRID) == player) {
			System.out.println(player.getName() + " wins!");
			System.out.println();
			displayGrid(GRID);
			System.exit(0);
		} else if (isTied(GRID)) {
			System.out.println("Its a tie!");
			System.out.println();
			displayGrid(GRID);
			System.exit(0);
		}
	}
	public static void displayGrid(int[] grid) {
		String dash = "\u2014\u2014\u2014";
		boolean first = true;
		for (int i = 0; i < 9; i++) {
			if (i % 3 == 0) {
				if (!first) {
					System.out.println();
					System.out.println(dash + '+' + dash + '+' + dash);
				}
				first = false;
				System.out.print(" ");
			}
			displayCell(i, grid[i]);
			if ((i + 1) % 3 != 0) {
				System.out.print(" | ");
			}
		}
		System.out.println();
	}
	public static void displayCell(int i, int g) {
		switch (g) {
		case 0:
			System.out.print(i + 1);
			break;
		case 1:
			System.out.print("O");
			break;
		case 2:
			System.out.print("X");
		}
	}
	public static Player getWinner(int[] grid) {
		for (int[] win : WINS) {
			boolean p1 = true;
			boolean p2 = true;
			for (int i = 0; i < 9; i++) {
				if (win[i] == 1 && grid[i] != 1) {
					p1 = false;
				}
				if (win[i] == 1 && grid[i] != 2) {
					p2 = false;
				}
				if (!p1 && !p2) {
					break;
				}
			}
			if (p1) {
				return Game.p1;
			}
			if (p2) {
				return Game.p2;
			}
		}
		return null;
	}
	public static boolean isTied(int[] grid) {
		boolean tie = getWinner(grid) == null;
		if (tie) {
			for (int g : grid) {
				if(g == 0) {
					tie = false;
					break;
				}
			}
		}
		return tie;
	}
}