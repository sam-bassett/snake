package engine;
import java.awt.event.KeyEvent;

import interfaceMVC.Controller;
import interfaceMVC.View;

import java.util.Scanner;


public class KeyController implements Controller {
	
	Scanner sc = new Scanner(System.in);
	int key = KeyEvent.VK_RIGHT;
	boolean startGame = false;
	
	public KeyController() {
		// Does nothing
	}

	@Override
	public void updateDirection(Snake s) {
		if (key == KeyEvent.VK_UP) {
			s.setDirection(Defaults.DOWN); // this is backwards. it works. wtf.
		} else if (key == KeyEvent.VK_DOWN) {
			s.setDirection(Defaults.UP);
		} else if (key == KeyEvent.VK_LEFT) {
			s.setDirection(Defaults.LEFT);
		} else if (key == KeyEvent.VK_RIGHT) {
			s.setDirection(Defaults.RIGHT);
		}
	}

	@Override
	public int getBoardSize(View v) {
		v.promptBoardSize();
		return sc.nextInt();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			startGame = true;
		} else {
			key = e.getKeyCode();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startGame() {
		// TODO Auto-generated method stub
		System.out.println("Starting game..");
	}

	@Override
	public boolean replay(View view) {
		// TODO Auto-generated method stub
		view.promptReplay();
		if (sc.next().equalsIgnoreCase("y")) {
			return true;
		} else {
			return false;
		}
	}

}
