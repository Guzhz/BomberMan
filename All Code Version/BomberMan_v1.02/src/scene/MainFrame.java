package scene;			//导包

import core.StartGame;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * 
 * @author Guzhz
 * @date 2018年10月29日
 * @time 上午11:27:31
 */


//继承JFrame  实现KeyListener
public class MainFrame extends JFrame implements KeyListener{
	public void MainFrame() { 
		setSelect();					//选择方法
		StartInit();					//开始界面初始化
		setMainFrameUi();				//设置主窗口
		this.addKeyListener(this);		//监听
	}
	
	JLabel lab_monster;						//开始界面移动的为怪物，故设置这个变量为全局变量
	public static final int MOS_X = 220;	//定义选择按键的怪物x坐标
	public static final int MOS_Y = 540;	//定义选择按键的怪物y坐标
	
	//选择按键的怪物移动方法
	private void setSelect() {			
		Icon i = new ImageIcon("src/images/monster.png");	//定义怪物图片
		lab_monster = new JLabel(i);						//放入标签中
		lab_monster.setBounds(MOS_X, MOS_Y, 48, 48);		//设置坐标
		this.add(lab_monster);								//添加到窗体中
		
		i = new ImageIcon("src/images/StGame.png");			//开始游戏为一张图片
		JLabel lab_st = new JLabel(i);
		lab_st.setBounds(275, 540, 192, 48);
		this.add(lab_st);
		
		i = new ImageIcon("src/images/ExGame.png");			//结束游戏也为一张图片
		JLabel lab_ex = new JLabel(i);
		lab_ex.setBounds(275, 588, 192, 48);
		this.add(lab_ex);
		
	}

	//设置开始界面的图片
	private void StartInit() {
		Icon i = new ImageIcon("src/images/start.png");
		JLabel lab_start = new JLabel(i);
		lab_start.setBounds(0, 0, 720, 720);
		this.add(lab_start);		
		
	}
	
	//设置主窗体
	public void setMainFrameUi() {
    	this.setLayout(null);			//设置为null为自由布局
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//遇到关闭按键则关闭窗口
		this.setTitle("炸弹人  v1.0");
		this.setSize(738,765);			//窗体大小
		this.setLocation(500, 180);		//窗体显示在电脑中的位置
		this.setVisible(true);			//窗口可见
	}

	//实现的监听接口中的  type类型可以不同理
	public void keyTyped(KeyEvent e) {		
		
	}
	
	//实现键盘监听中的按下去监听 press
	public void keyPressed(KeyEvent e) {		
		int key = e.getKeyCode();		//获取键码值
		
		//38为方向键 向上 的键码值
		if(key == 38) {
			int x = (int)lab_monster.getLocation().getX();		//获取怪物标签的x坐标方法
			int y = (int)lab_monster.getLocation().getY();		//获取怪物标签的y坐标方法
			
			if(y == MOS_Y)	//设置障碍，即在开始游戏位置如果按了向上按钮，则直接return(即不做处理)								
				return;
			else {			//否则怪物移动，即怪物如果在退出按钮位置，按向上，则重写标签位置
				lab_monster.setLocation(x,y-48);
				Icon i = new ImageIcon("src/images/monster.png");
				lab_monster.setIcon(i);
			}
		}
		
		//40为方向键 向下 的键码值
		if(key == 40) {			//和上面相反的道理
			int x = (int)lab_monster.getLocation().getX();
			int y = (int)lab_monster.getLocation().getY();
			
			if(y==(MOS_Y+48))	//判断小怪物的位置
				return;
			else {
				lab_monster.setLocation(x,y+48);
				Icon i = new ImageIcon("src/images/monster.png");
				lab_monster.setIcon(i);	
			}
		}
		
		//这里直接判断y的位置，并如果监听到回车按键，就做相应的处理
		//10为回车按键
		int y = (int)lab_monster.getLocation().getY();
		if(y==MOS_Y && key==10 ) {
			this.dispose();			//y刚好在开始位置处，并且监听到回车
			new StartGame();		//就关闭这个窗口，设置新的窗口，新窗口的属性和本窗口的属性一致
		}
		
		if(y==MOS_Y+48 && key==10  ) {
			this.dispose();			//如果在退出按钮的位置，监听到回车，直接关闭窗口
		}
	
	}

	//这里没有设置键盘的弹起监听
	public void keyReleased(KeyEvent e) {
	
	}	
}
