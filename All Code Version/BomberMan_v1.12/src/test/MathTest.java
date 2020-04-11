package test;


import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

class KeyTest extends JFrame implements KeyListener {

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            flag = -1;
            showNum();
        }
        if (key == KeyEvent.VK_RIGHT) {
            flag = 1;
            showNum();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            flag = 0;
        }
        if (key == KeyEvent.VK_RIGHT) {
            flag = -0;
        }
    }
    public KeyTest() {
        this.addKeyListener(this);
        threadTest();
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                threadTest();
            }
        }, 0, 20);

        setSize(100, 100);
        setFocusable(true);
        setVisible(true);
    }

    int num = 0;
    public void showNum(){
        double transNum = Math.round(num / 48);
        double rightNum = (transNum+1) * 48;
        double leftNum = (transNum-1) * 48;
        double lpass = Math.abs(num - leftNum);
        double rpass = Math.abs(num - rightNum);
        System.out.println(num + ".." + transNum + "rightNum:" + rightNum + "  leftNum" + leftNum + " lpass:" + lpass + "  rpass" +  rpass);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    int flag = 0;
    public void threadTest() {
        num += flag;
    }

}

public class MathTest {
    private static final int ONE = 48;
    public static void main(String[] args) {
        double num = 47/(double)ONE;
        double a = Math.round(num);

        System.out.println("num=" + num + " a=" + (int)a);

//        new KeyTest();
    }
}
