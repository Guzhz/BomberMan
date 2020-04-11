package scene;

import core.StartGame;

import java.io.File;

/**
 * 
 * @author Guzhz
 * @date 2018年10月27日
 * @time 上午9:36:46
 */

public class App {
	public static void main(String[] args) {
	    File f = new File("src/images/ExGame.png");
		System.out.println(f.exists());

//		new MainFrame().MainFrame();
		new StartGame();
	}
}
