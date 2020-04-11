package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Board extends JPanel implements ActionListener {
    private Timer timer;
    private Bad1 bad1;

    private final int BAD_X = 0;
    private final int BAD_Y = 0;
    private final int B_WIDTH = 800;
    private final int B_HEIGHT = 600;
    private final int DELAY = 15;

    public Board() {
        initBoard();
    }

    private void initBoard() {
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);

        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));

        bad1 = new Bad1(BAD_X,BAD_Y);

        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBAD(g);
    }

    private void drawBAD(Graphics g) {
        g.drawImage(bad1.getImage(), bad1.getX(), bad1.getY(), 100, 100,    this);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        updateBAD();
        repaint();
    }

    private void updateBAD() {
        bad1.move();
    }

    private class TAdapter implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            bad1.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            bad1.keyReleased(e);
        }
    }
}
