package game;

import javax.swing.*;
import java.awt.*;

public class MovingTest extends JFrame {
    public MovingTest() {
        initUI();
    }

    private void initUI() {
        add(new Board());
//        setSize(800, 600);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            MovingTest ex = new MovingTest();
            ex.setVisible(true);
        });
    }

}
