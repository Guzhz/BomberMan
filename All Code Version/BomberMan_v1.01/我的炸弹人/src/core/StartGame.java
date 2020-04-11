package core;


import handler.Direction;
import monster.Monster;
import people.BomberMan;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

/**
 * 
 * @author Guzhz
 * @date 2018年10月29日
 * @time 下午4:08:12
 */

public class StartGame extends JFrame implements KeyListener {


	public static final int ONE_STEP = 48;
	public static final int FIRE_IN_ROUTE = 9;
	public static final int NORMAL_ROUTE = 4;
	public static final int BOX_IN_ROUTE = 1;
	public static final int ROCK_IN_ROUTE = 0;
	public static final int BOMB_IN_ROUTE = 8;

	//0代表石头，1代表箱子，4代表陆地
	public int[][] dates = {
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,1,1,4,1,4,1,4,4,1,1,1,1,1,0},
			{0,4,4,4,4,4,4,4,4,4,4,4,4,4,0},
			{0,1,0,0,0,4,4,4,4,4,0,0,0,0,0},
			{0,4,4,4,4,4,4,0,0,4,4,4,4,4,0},
			{0,1,1,1,4,4,1,1,1,1,1,1,1,1,0},
			{0,4,4,4,4,4,4,4,4,4,4,4,4,4,0},
			{0,1,1,0,0,0,0,4,4,1,1,1,0,0,0},
			{0,4,4,4,4,4,4,4,4,4,4,4,4,4,0},
			{0,4,1,4,1,1,4,1,4,4,4,1,4,1,0},
			{0,4,4,4,4,4,4,4,4,4,4,4,4,4,0},
			{0,4,4,0,4,1,0,4,4,0,0,4,0,4,0},
			{0,4,4,4,4,4,4,4,4,4,4,4,4,4,0},
			{0,4,4,4,4,4,4,4,4,4,4,4,4,4,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}				//测试数组
			
//			{4,4,4,4,4,4,4,4,4,4,4,4,4,4,4},
//			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,4},
//			{1,4,4,4,4,4,4,4,4,4,4,4,4,4,4}, 
//			{4,1,0,0,0,4,4,4,4,4,0,0,0,0,0},
//			{4,4,4,4,4,4,4,4,4,4,4,4,4,4,4},
//			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,4},
//			{4,4,4,4,4,4,4,4,4,4,4,4,4,4,4},
//			{4,1,1,0,0,0,0,0,1,1,1,1,0,0,0},
//			{4,4,4,4,4,4,4,4,4,4,4,4,4,4,4},
//			{1,4,1,4,1,1,4,1,4,1,4,1,4,1,1},
//			{4,4,4,4,4,4,4,4,4,4,4,4,4,4,4},
//			{1,4,4,0,4,1,0,0,4,0,0,4,0,4,1},
//			{4,4,4,4,4,4,4,4,4,4,4,4,4,4,4},
//			{4,4,4,4,4,4,4,4,4,4,4,4,4,4,4},
//			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}				//测试数组
//			
//			{4,4,4,4,4,4,4,4,4,4,4,4,4,4,4},
//			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,4},
//			{1,4,8,4,4,4,4,4,4,4,4,4,4,4,4}, 
//			{4,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
//			{4,4,4,4,4,4,4,4,4,4,4,4,4,4,4},
//			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,4},
//			{4,4,4,4,4,4,4,4,4,4,4,4,4,4,4},
//			{4,1,1,0,0,0,0,0,1,1,1,1,0,0,0},
//			{4,4,4,4,4,4,4,4,4,4,4,4,4,4,4},
//			{1,4,1,4,1,1,4,1,4,1,4,1,4,1,1},
//			{4,4,4,4,4,4,4,4,4,4,4,4,4,4,4},
//			{1,4,4,0,4,1,0,0,4,0,0,4,0,4,1},
//			{4,4,4,4,4,4,4,4,4,4,4,4,4,4,4},
//			{4,4,4,4,4,4,4,4,4,4,4,4,4,4,4},
//			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}			模仿别人的炸弹人的数组
	};
	

	
	public StartGame() {
		manInit();				//人物初始化
		boxInit();				//箱子初始化
		blockInit();			//石头初始化
//		backgroundInit();		//背景初始化
		setGameFrameUi();		//设置基本窗口
        monsterInit(3, 500); //怪物初始化,参数1：怪物数量，参数2：怪物移动速度
	}

	HashMap<Thread, Monster> monsterMap = new HashMap<Thread, Monster>();
	private void monsterInit(int monster_count, long moving_speed) {
		for (int i = 0; i < monster_count; i++) {
			Monster m = new Monster(this, 6, 4, moving_speed);
			Thread t = new Thread(m);
            t.start();
            monsterMap.put(t,m);
		}
	}




    BomberMan aBomberMan;
	private void manInit() {
		//初始化人物
//		mx = 6;			//这里直接用坐标*ONE_STEP像素来设置具体位置
//		my = 3;			//坐标放标在数组地图中描述
		aBomberMan = new BomberMan(this, 6, 3);
	}
	
	private void blockInit() {
		//初始化石头0
		Icon ic = new ImageIcon("src/images/block.png");

		for(int i = 0; i<dates.length; i++) {				//遍历地图数组设置石头
			for (int j = 0; j < dates[i].length; j++) {
				if(dates[i][j] == 0)
				{
					JLabel lab_block = new JLabel(ic);
					lab_block.setBounds(ONE_STEP*j, ONE_STEP*i, ONE_STEP, ONE_STEP);
					this.add(lab_block);
				}
			}
		}
	}

	public HashMap<String, JLabel> boxMap = new HashMap<String, JLabel>();
	public void boxInit() {
		//初始化箱子1		同理设置箱子
		for(int i = 0; i<dates.length; i++) {
			for (int j = 0; j < dates[i].length; j++) {
				if(dates[i][j] == 1)
				{
					JLabel lab_case = new JLabel(new ImageIcon("src/images/case.png"));
					lab_case.setBounds(ONE_STEP*j, ONE_STEP*i, ONE_STEP, ONE_STEP);
					JLabel box = boxMap.get(String.valueOf(j) + String.valueOf(i));

					this.add(lab_case);
					boxMap.put(String.valueOf(j) + String.valueOf(i),lab_case);
				}
			}
		}
		this.validate();
		this.repaint();
		this.revalidate();
	}


	public void setGameFrameUi() {
		
		//设置基本窗体
    	this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("炸弹人  v1.0");
		this.setSize(726,754);
		this.setLocation(500, 180);
		this.setResizable(false);
		this.setVisible(true);
		this.addKeyListener(this);
	}
	
	public void backgroundInit() {		
		
		//黑色背景初始化
//		Icon i = new ImageIcon("src/images/startGame.png");
//        JLabel lab_startGame = new JLabel(i);
//        lab_startGame.setBounds(0, 0, 720, 720);
//        super.add(lab_startGame);
//        lab_startGame.setIcon(i);
	}

	public void keyTyped(KeyEvent e) {
		
	} 

	int bomberman_direction_with_bomb = 40;  //记录当前人物的方向,默认为40,因为40位键盘的下键盘，即正面
	int flag2 = 0;  //记录是否投放了炸弹
	
	//按下监听


	public void keyPressed(KeyEvent e) {
		if(isPeopleInDanger())
			return;
		int key = e.getKeyCode();
		//32为空格
		int x = aBomberMan.lab_man.getX();	//获取人物的xy
		int y = aBomberMan.lab_man.getY();	//遇到空格投放炸弹
		if(key == 32) {
            aBomberMan.setBomb(x, y);
		}

		//37左   38上  39 右   40下
		//0代表石头，1代表箱子，4代表陆地

		System.out.println(key);
		//左
		//障碍判定，箱子和石头不反应
		if(key == KeyEvent.VK_LEFT) {
		    aBomberMan.boomberManMove(Direction.getArrayCoords(aBomberMan.lab_man, Direction.LEFT));
		}

		//上
		if(key == KeyEvent.VK_UP) {
			aBomberMan.boomberManMove(Direction.getArrayCoords(aBomberMan.lab_man, Direction.UP));
		}
		//右
		if(key == KeyEvent.VK_RIGHT) {
			aBomberMan.boomberManMove(Direction.getArrayCoords(aBomberMan.lab_man, Direction.RIGHT));
		}
		//下
		if(key == KeyEvent.VK_DOWN) {
			aBomberMan.boomberManMove(Direction.getArrayCoords(aBomberMan.lab_man, Direction.DOWN));
		}
	}

	public void keyReleased(KeyEvent e) {
	    //键盘弹起，判断人物是否处于危险区域
		isPeopleInDanger();
	}
	public boolean isPeopleInDanger() {
		if (dates[aBomberMan.lab_man.getY()/ONE_STEP][aBomberMan.lab_man.getX()/ONE_STEP] == FIRE_IN_ROUTE) {
			System.out.println("Game Over!");
			Icon i = new ImageIcon("src/images/Dead.png");
			aBomberMan.lab_man.setIcon(i);
			return true;
		}
		return false;
	}
}

