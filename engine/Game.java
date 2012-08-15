package engine;

import interfaceMVC.Controller;
import interfaceMVC.View;

public class Game {
	View view;
	Controller controller;
	
	Board board;
	Snake snake;
	
	public Game(View v, Controller c) {
		view = v; 
		controller = c;
		int boardSize = 50; //controller.getBoardSize(view);
		board = new Board(boardSize);
		snake = new Snake(boardSize);
		view.setBoardSize(boardSize);
		view.showTitleScreen(controller);
	}
	
	public void runGame() {
		int score = 0;
		while (!board.gameOver()) {
			// first update snake direction.
			controller.updateDirection(snake);
			// then update snake location.
			snake.stepSnake();
			// then update board and check for game over.
			board.updateSnake(snake);
			// then update view.
			view.updateBoard(board);
			// give the poor player a break!
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		score = snake.getSize();
		view.gameOver(score);
		board.reset();
		snake.reset();
	}

}
