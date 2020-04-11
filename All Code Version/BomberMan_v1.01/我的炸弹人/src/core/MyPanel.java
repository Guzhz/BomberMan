package core;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

//public class test extends JFrame{
//	MyPanel mp = null;
//	public test() {
//		mp = new MyPanel();
//		this.add(mp);
//		this.setSize(550, 400);
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		this.setVisible(true);
//	}
//	public static void main(String[] args) {
//		new test();
//	}
//}

public class MyPanel extends JPanel{
	Image image = null;
	
	public void paint(Graphics g) {
		
			try {
				image = ImageIO.read(new File("src\\images\\StartGame.png"));
				g.drawImage(image,0,0,720,720,null);
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
}
