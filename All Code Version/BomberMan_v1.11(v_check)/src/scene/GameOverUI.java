package scene;

import static core.StartGame.ONE_STEP;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import core.StartGame;

public class GameOverUI {
	
	public JLabel lab_over;
	public JLabel lab_win;
	public JLabel lab_monster;	
	Icon ic = new ImageIcon("src/images/monster.png");
	
    StartGame gameframe = null;
    
    public GameOverUI(StartGame gameframe) {
    	
    	this.gameframe = gameframe;
    	
    	ic = new ImageIcon("src/images/monster.png");
    	this.lab_monster = new JLabel(ic);
    	lab_monster.setBounds(227, 280, 48, 48);			
    	gameframe.add(lab_monster);
    	lab_monster.setVisible(false);
    	
        Icon i = new ImageIcon("src/images/gameOver.png");		
        this.lab_over = new JLabel(i);
        lab_over.setBounds(216, 264, 288, 192);				
        gameframe.add(lab_over);
        lab_over.setVisible(false);
        
        Icon icon = new ImageIcon("src/images/gameWin.png");		
        this.lab_win = new JLabel(icon);
        lab_win.setBounds(216, 264, 288, 192);				
        gameframe.add(lab_win);
        lab_win.setVisible(false);
    }
    
    public void gameOverUI_Add(){
        lab_over.setVisible(true);
    	lab_monster.setVisible(true);
    }
    
    public void gameWinUI_Add() {
    	System.out.println("win！");
    	lab_win.setVisible(true);
    	lab_monster.setVisible(true);
    }
    	
    public void selectMonster_down() {
    	
    	lab_monster.setBounds(227, 280, 48, 48);
    }
    
    public void selectMonster_up() {

    	lab_monster.setBounds(227, 390, 48, 48);

    }
}
