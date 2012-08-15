package engine;

import interfaceMVC.Controller;
import interfaceMVC.View;

public class Main {

	public static void main(String[] args) {

		Controller c = new KeyController();
		View v = new GraphicView(c);
		Game g = new Game(v,c);
		g.runGame();


	}

}
