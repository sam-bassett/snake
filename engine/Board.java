package engine;

import java.util.Random;

public class Board {
	public static final int X = 0;
	public static final int Y = 1;
	// data structure for board, snake should be separate class.
	int[][] board;
	int boardSize;

	int[] food;

	boolean gameOver;

	public Board (int boardSize) {
		this.boardSize = boardSize;
		board = new int[boardSize][boardSize];
		reset();
		gameOver = false;
		food = new int[2];
		updateFood();
	}

	private void updateFood() {
		if (foodExists()) {
			return;
		}
		Random gen = new Random();
		int x = gen.nextInt(boardSize);
		int y = gen.nextInt(boardSize);
		board[x][y] = Defaults.FOOD;
		food[X] = x;
		food[Y] = y;
	}

	private boolean foodExists() {
		boolean exists = false;
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				if (board[i][j] == Defaults.FOOD) {
					exists = true;
				}
			}
		}
		return exists;
	}

	public int[] getFood() {
		return food;
	}

	public int[][] dumpBoard() {
		return board;
	}

	public void updateSnake(Snake snake) {
		resetSnake();
		int pos[][] = snake.getSnake();
		int x, y;
		// do end-of-game check here. If any block tries to occupy a block
		// which is outside the boundary/already occupied by snake, game over!
		for (int i = 0; i < snake.getSize(); i++) {
			x = pos[i][X];
			y = pos[i][Y];
			// if any piece of snake intersects with food,
			// eat food.
			// try..catch to end if outside array bounds
			try {
				if (board[x][y] == Defaults.FOOD) {
					snake.updateScore();
					updateFood();
				} else if (board[x][y] == Defaults.SNAKE || // this is dubious.
						board[x][y] == Defaults.SNAKE_HEAD) { // TODO test
					gameOver = true;
				}

				// make first point SNAKE_HEAD for colour purposes
				if (i == 0) {
					board[x][y] = Defaults.SNAKE_HEAD;
				} else {
					board[x][y] = Defaults.SNAKE;
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				gameOver = true;
			}
		}
	}

	private void resetSnake() {
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				if (board[i][j] == Defaults.SNAKE ||
						board[i][j] == Defaults.SNAKE_HEAD) {
					board[i][j] = Defaults.BLANK;
				}
			}
		}
		updateFood();

	}
	
	public boolean gameOver() {
		return gameOver;
	}

	public void reset() {
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				board[i][j] = Defaults.BLANK;
			}
		}
		gameOver = false;
	}

}
