package core;


import handler.Direction;
import handler.Handler;
import monster.Monster;
import monster.MonsterCount;
import people.BomberMan;
import scene.GameOverUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import java.util.Timer;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 * @author Guzhz
 * @date 2018年10月29日
 * @time 下午4:08:12
 */

/**
 * 
 * @author Guzhz
 * @date 2018年11月3日
 * @time 下午1:45:44
 */

public class StartGame extends JFrame implements KeyListener{


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
	};
	
	public StartGame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameOverInit();			//结束界面初始化
		manInit();				//人物初始化
		boxInit();				//箱子初始化
		blockInit();			//石头初始化
		setGameFrameUi();		//设置基本窗口
        monsterInit(1, 100); 	//怪物初始化,参数1：怪物数量，参数2：怪物移动速度
        handlerInit();
	}







	private void handlerInit() {
		Handler fireHandler = new Handler(this);
	    new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				fireHandler.check();
			}
		}, 0, 20);
	}

	public MonsterCount mc;	//建立一个专门处理怪物数量的类
	public ConcurrentHashMap<Thread, Monster> monsterMap = new ConcurrentHashMap<>();
	private void monsterInit(int monster_count, long moving_speed) {
		mc = new MonsterCount(monster_count);		
		for (int i = 0; i < monster_count; i++) {
			Monster m = new Monster(this, dates.length-2, dates.length-2, moving_speed, mc, GOUI);		
			Thread t = new Thread(m);
            t.start();
            monsterMap.put(t,m);
		}
	}

	//游戏结束界面初始化
	public GameOverUI GOUI;
	private void gameOverInit() {
		GOUI = new GameOverUI(this);
	}
	
    public BomberMan aBomberMan;
	private void manInit() {
		//初始化人物
//		mx = 6;			//这里直接用坐标*ONE_STEP像素来设置具体位置
//		my = 3;			//坐标放标在数组地图中描述
		aBomberMan = new BomberMan(this, 6, 3);
		aBomberMan.setBloodCount();					//new好人物后，设置血量
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
		this.setTitle("炸弹人 v1.0   #GDPU-5-523爆破组");
		this.setSize(726,754);
		this.setLocation(500, 180);
		this.setResizable(false);
		
		this.getContentPane().setBackground(Color.gray);
		this.getContentPane().setVisible(true);
		
		this.setVisible(true);
		this.addKeyListener(this);
	}
	
	
	public void keyTyped(KeyEvent e) {
		
	} 

	int bomberman_direction_with_bomb = 40;  //记录当前人物的方向,默认为40,因为40位键盘的下键盘，即正面
	int flag2 = 0;  //记录是否投放了炸弹
	boolean exit_flag = false;	//记录结束界面的退出选择是否按下了回车键
	int man_state = 0;	//-1是死亡，0是正常，1是胜利
	//按下监听

	
	public void keyPressed(KeyEvent e) {
		
		int key = e.getKeyCode();
		Victory();					//优先判断是否胜利
		
	//--------------------------人物死后界面监听------------------------------
		if(man_state == -1) {
			GOUI.gameOverUI_Add();
			if(key == KeyEvent.VK_DOWN) {
				GOUI.selectMonster_up();
				exit_flag = true;
			}
			if(key == KeyEvent.VK_UP) {
				GOUI.selectMonster_down();
				exit_flag = false;
			}
			
			if(key == KeyEvent.VK_ENTER) {
				if(exit_flag) {
					this.dispose();			//退出位置监听到回车，关闭窗口
				}
				else {
					this.dispose();			//退出位置监听到回车，关闭窗口
					new StartGame();
				}
			}
		}
		
	//--------------------------人物正常监听------------------------------
		if(man_state == 0) {
		    aBomberMan.keyPress(e);
		}
		
		//--------------------------人物胜利界面监听------------------------------
		if(man_state == 1) {
			GOUI.gameWinUI_Add();
			if(key == KeyEvent.VK_DOWN) {
				GOUI.selectMonster_up();
				exit_flag = true;
			}
			if(key == KeyEvent.VK_UP) {
				GOUI.selectMonster_down();
				exit_flag = false;
			}
			
			if(key == KeyEvent.VK_ENTER) {
				if(exit_flag) {
					this.dispose();			//退出位置监听到回车，关闭窗口
				}
				else {
					this.dispose();			//退出位置监听到回车，关闭窗口
					new StartGame();
				}
			}
		}
		
	}

	public void keyReleased(KeyEvent e) {
		aBomberMan.keyRelease(e);
	    //键盘弹起，判断人物是否处于危险区域
//		isPeopleInDanger();
	}

	
	//判断人物是否胜利，如果胜利状态置为1
	public void Victory() {
		if(mc.getState()) {
			man_state = 1;
		}
	}
	
	void manGoDead() {
	    //如果无敌，则不死
	    if(isManInvincible()) {
	    	return;
		}
		aBomberMan.blood_count--;
		aBomberMan.BC.reduceBloodUI();			//判断人死后，生命值减一
	    aBomberMan.setManInvincible(true);
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				aBomberMan.setManInvincible(false);
			}
		}, 2000);
		if(aBomberMan.blood_count > 0) {
			System.out.println("blood.." + aBomberMan.blood_count);
			return ;
		}
		GOUI.gameOverUI_Add();			//死后弹出结束界面
		System.out.println("Game Over!");
		Icon i = new ImageIcon("src/images/Dead.png");
		aBomberMan.lab_man.setIcon(i);
		man_state = -1;					//人物死亡，状态置为-1;
	}


	boolean isManInvincible() {
		if (aBomberMan.bomber_man_invincible == true) {
			return true;
		}
		return false;
	}
	
	//实时判断是否死亡
	public boolean isPeopleInDanger() {
		if (dates[aBomberMan.lab_man.getY()/ONE_STEP][aBomberMan.lab_man.getX()/ONE_STEP] == FIRE_IN_ROUTE 
				|| isManMeetMonster()) {
		    manGoDead();
			return true;
		}
		return false;
	}
	
	boolean isManMeetMonster () {
		Set<Map.Entry<Thread, Monster>> set = monsterMap.entrySet();
		for (Iterator<Map.Entry<Thread, Monster>> iterator = set.iterator(); iterator.hasNext(); ) {
			Map.Entry<Thread, Monster> monsterEntry = iterator.next();
			if(Direction.isCoordsEqual(monsterEntry.getValue().monster, aBomberMan.lab_man)) {
				return true;
			}
		}
		return false;
	}

}

