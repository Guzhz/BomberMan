package scene;

import static core.StartGame.ONE_STEP;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import core.StartGame;

public class GameOverUI {
	
	public JLabel lab_over;
	public JLabel lab_monster;	//定义个选择UI_怪物
	Icon ic = new ImageIcon("src/images/monster.png");
	
    StartGame gameframe = null;
    
    public GameOverUI(StartGame gameframe) {
        //初始化结束界面
    	
    	this.gameframe = gameframe;
    	
    	ic = new ImageIcon("src/images/monster.png");
    	this.lab_monster = new JLabel(ic);
    	lab_monster.setBounds(227, 280, 48, 48);			//怪物位置默认在重新开始的左边
    	gameframe.add(lab_monster);
    	lab_monster.setVisible(false);
    	
        Icon i = new ImageIcon("src/images/gameOver.png");		//生成结束标签
        this.lab_over = new JLabel(i);
        lab_over.setBounds(216, 264, 288, 192);				//固定这个坐标了
        gameframe.add(lab_over);
        lab_over.setVisible(false);
        
    }
    
    public void gameOverUI_Add()
    {
        lab_over.setVisible(true);
    	lab_monster.setVisible(true);

    }
    	
    public void selectMonster_down() {
    	
    	lab_monster.setBounds(227, 280, 48, 48);
    }
    
    public void selectMonster_up() {

    	lab_monster.setBounds(227, 390, 48, 48);

    }
}
