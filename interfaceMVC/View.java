package interfaceMVC;

import engine.Board;

public interface View {
	
	public void showTitleScreen(Controller c);
	public void gameOver(int score);
	public void updateBoard(Board b);
	public void promptBoardSize();
	public void setBoardSize(int boardSize);
	public void promptReplay();

}
