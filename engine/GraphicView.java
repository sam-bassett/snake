package engine;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.*;

import interfaceMVC.Controller;
import interfaceMVC.View;


public class GraphicView implements View {
	private int BOARD_SIZE = 1;
	private int FRAME_SIZE = 400;

	private JFrame frame;
	private BoardPanel boardPanel;

	private Board board;

	public GraphicView(Controller c) {
		board = null;
		// this GUI needs an area to display the board and an area to 
		// display the score. 
		frame = new JFrame("Snake");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// here you should add the board, which is a SIZE*SIZE image.
		frame.setSize(FRAME_SIZE+100, FRAME_SIZE+100);
		boardPanel = new BoardPanel();
		frame.add(boardPanel);
		frame.addKeyListener(c);
		frame.setVisible(true);

	}

	@Override
	public void setBoardSize(int size) {
		this.BOARD_SIZE = size;
	}

	@Override
	public void showTitleScreen(Controller c) {
		/*titleScreen = new JPanel();
		titleScreen.setBackground(Color.BLACK);
		JButton start = new JButton("Press ENTER button to START");
		start.setBackground(Color.blue);
		titleScreen.add(start);
		frame.add(titleScreen);
		
		c.startGame();
		frame.remove(titleScreen);
*/
	}

	@Override
	public void gameOver(int score) {
		// TODO make this graphicsy
		System.out.println("Game over! Score: " + score);
		while(true) {
			JPanel gameOver = new JPanel();
			gameOver.setBackground(Color.BLACK);
			JLabel text = new JLabel("Game Over! Score: " + score);
			gameOver.add(text);
			frame.remove(boardPanel);
			gameOver.setVisible(true);
			text.setVisible(true);
			frame.add(gameOver);
			frame.setTitle("Game Over! Score: " + score);
			return;
		}
	}

	@Override
	public void updateBoard(Board b) {
		// TODO Auto-generated method stub
		this.board = b;
		boardPanel.repaint();
	}

	@Override
	public void promptBoardSize() {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("serial")
	public class BoardPanel extends JPanel {
		public void paintComponent(Graphics g) {
			// when iterating through board, if the given location is Defaults.BLANK
			// display a black square. If Defaults.SNAKE_HEAD, display a blue square.
			// if Defaults.SNAKE, display a lighter blue square. If Defaults.FOOD, 
			// make it a green square. 
			super.paintComponent(g);
			if (board != null) {
				int cellWidth = (FRAME_SIZE)/(BOARD_SIZE+BOARD_SIZE/FRAME_SIZE);
				int cellHeight = (FRAME_SIZE)/(BOARD_SIZE+BOARD_SIZE/FRAME_SIZE);

				for (int i = 0; i < board.boardSize; i++) {
					for (int j = 0; j < board.boardSize; j++) {
						if (board.board[i][j] == Defaults.BLANK) {
							g.setColor(Color.BLACK);
							g.drawRect(i*cellWidth, j*cellHeight, cellWidth, cellHeight);
							g.fillRect(i*cellWidth, j*cellHeight, cellWidth, cellHeight);
						} else if (board.board[i][j] == Defaults.SNAKE_HEAD) {
							g.setColor(Color.CYAN);
							g.drawRect(i*cellWidth, j*cellHeight, cellWidth, cellHeight);
							g.fillRect(i*cellWidth, j*cellHeight, cellWidth, cellHeight);
						} else if (board.board[i][j] == Defaults.SNAKE) {
							g.setColor(Color.BLUE);
							g.drawRect(i*cellWidth, j*cellHeight, cellWidth, cellHeight);
							g.fillRect(i*cellWidth, j*cellHeight, cellWidth, cellHeight);
						} else if (board.board[i][j] == Defaults.FOOD) {
							g.setColor(Color.GREEN);
							g.drawRect(i*cellWidth, j*cellHeight, cellWidth, cellHeight);
							g.fillRect(i*cellWidth, j*cellHeight, cellWidth, cellHeight);
						}
					}
				}
			}	
		}
	}

	@Override
	public void promptReplay() {
		// TODO Auto-generated method stub
		
	}


}
