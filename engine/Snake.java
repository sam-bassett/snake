package engine;

public class Snake {
	public static int MAX_SIZE = 100;
	
	int[][] snake;
	int size;
	
	boolean willGrow;
	private boolean direction[];
	private int boardSize;
	
	public Snake(int boardSize) {
		this.boardSize = boardSize;
		assert (boardSize/2 > 3);
		MAX_SIZE = boardSize*boardSize;
		snake = new int[MAX_SIZE][2];
		reset();
	}
	
	public int[][] getSnake() {
		return snake;
	}
	
	public int getSize() {
		return size;
	}
	
	public void updateScore() {
		willGrow = true;
	}
	
	public void stepSnake() {
		if (findDirection() == Defaults.UP) {
			stepBlocks(0,1);
		} else if (findDirection() == Defaults.DOWN) {
			stepBlocks(0,-1);
		} else if (findDirection() == Defaults.LEFT) {
			stepBlocks(-1,0);
		} else if (findDirection() == Defaults.RIGHT) {
			stepBlocks(1,0);
		}
	}
	
	// this does growing as well as stepping
	private void stepBlocks(int deltaX, int deltaY) {
		// remember blocks, step to new position, make old new.
		// unless about to grow, in which case clone the last one.
		// head is the only one which changes direction, all the rest
		// follow.
		int[] prevBlock = snake[0].clone();
		snake[0][Defaults.X] += deltaX;
		snake[0][Defaults.Y] += deltaY;
		for (int i = 1; i < size; i++) {
			int x, y;
			x = snake[i][Defaults.X];
			y = snake[i][Defaults.Y];
			snake[i][Defaults.X] = prevBlock[Defaults.X];
			snake[i][Defaults.Y] = prevBlock[Defaults.Y];
			prevBlock[Defaults.X] = x;
			prevBlock[Defaults.Y] = y;
		}
		if (willGrow) {
			snake[size] = prevBlock;
			size++;
			willGrow = false;
		}
	}
	
	private int findDirection() {
		int direction = -1;
		for (int i = 0; i < Defaults.NUM_DIRECTIONS; i++) {
		
			if (this.direction[i]) {
				direction = i;
			}
		}
		return direction;
	}
	
	public void setDirection (int to) {
		if (to == Defaults.UP && !direction[Defaults.DOWN]) {
			zeroDirection();
			direction[Defaults.UP] = true;
		} else if (to == Defaults.DOWN && !direction[Defaults.UP]){
			zeroDirection();
			direction[Defaults.DOWN] = true;
		} else if (to == Defaults.LEFT && !direction[Defaults.RIGHT]){
			zeroDirection();
			direction[Defaults.LEFT] = true;
		} else if (to == Defaults.RIGHT && !direction[Defaults.LEFT]) {
			zeroDirection();
			direction[Defaults.RIGHT] = true;
		}
	}
	
	private void zeroDirection() {
		for (int i = 0; i < Defaults.NUM_DIRECTIONS; i++) {
			direction[i] = false;
		}
	}

	public void reset() {
		size = 3;
		int x = boardSize/2; // TODO This may crash if the board isn't big 
		int y = boardSize/2; // enough for 3 dots. what a shame.
		for (int i = 0; i < size; i++) {
			snake[i][Defaults.X] = x-i;
			snake[i][Defaults.Y] = y;
		}
		direction = new boolean[4];
		setDirection(Defaults.RIGHT);
	}

}
