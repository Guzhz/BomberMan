package images;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class test {
	private Frame f;
	private Button bt;
	private TextField tf;
	
	test() {
		madeFrame();
	}

	private void madeFrame() {
		// TODO Auto-generated method stub
		f = new Frame("ne");
		f.setBounds(300, 100, 600, 500);
		f.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));
		bt = new Button("my bt");
		tf = new TextField(20);
		f.add(tf);
		f.add(bt);
		
		myEvent();
		
		f.setVisible(true);
	}

	private void myEvent() {
		f.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				System.out.println("gbi");
				System.exit(0);
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		bt.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ESCAPE)
					System.exit(0);
				else if(e.isControlDown()&&e.getKeyCode()==KeyEvent.VK_ENTER)
					System.exit(0);
				else System.out.println(e.getKeyChar()+"..."+KeyEvent.getKeyText(e.getKeyCode()));
			}
		});
		
		bt.addKeyListener(new KeyAdapter() {
			 public void keyPressed(KeyEvent e)
			 {
				 int code = e.getKeyCode();
				 if(!(code>=KeyEvent.VK_0&&code<=KeyEvent.VK_9))
				 {
					 System.out.println(code+"..."+"是非法的");
					 e.consume();
				 }
			 }
		});
	}
	public static void main(String[] args) {
		new test();
	}
}
