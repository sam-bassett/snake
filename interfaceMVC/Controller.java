package interfaceMVC;

import java.awt.event.KeyListener;

import engine.Snake;

public interface Controller extends KeyListener {
	
	public void updateDirection(Snake s);
	public int getBoardSize(View v);
	public void startGame();
	public boolean replay(View view);

}
