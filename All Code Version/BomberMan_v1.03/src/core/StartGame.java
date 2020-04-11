package core;


import handler.Direction;
import monster.Monster;
import people.BomberMan;
import scene.GameOverUI;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author Guzhz
 * @date 2018��10��29��
 * @time ����4:08:12
 */

/**
 * 
 * @author Guzhz
 * @date 2018��11��3��
 * @time ����1:45:44
 */

public class StartGame extends JFrame implements KeyListener {


	public static final int ONE_STEP = 48;
	public static final int FIRE_IN_ROUTE = 9;
	public static final int NORMAL_ROUTE = 4;
	public static final int BOX_IN_ROUTE = 1;
	public static final int ROCK_IN_ROUTE = 0;
	public static final int BOMB_IN_ROUTE = 8;

	//0����ʯͷ��1�������ӣ�4����½��
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
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}				//��������
			
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
//			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}				//��������
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
//			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}			ģ�±��˵�ը���˵�����
	};
	
	GameOverUI GOUI;
	public StartGame() {
		GOUI = new GameOverUI(this);
		manInit();				//�����ʼ��
		boxInit();				//���ӳ�ʼ��
		blockInit();			//ʯͷ��ʼ��
//		backgroundInit();		//������ʼ��
		setGameFrameUi();		//���û�������
        monsterInit(3, 500); //�����ʼ��,����1����������������2�������ƶ��ٶ�
	}

	HashMap<Thread, Monster> monsterMap = new HashMap<Thread, Monster>();
	private void monsterInit(int monster_count, long moving_speed) {
		for (int i = 0; i < monster_count; i++) {
			Monster m = new Monster(this, dates.length-2, dates.length-2, moving_speed);
			Thread t = new Thread(m);
            t.start();
            monsterMap.put(t,m);
		}
	}


	

    BomberMan aBomberMan;
	private void manInit() {
		//��ʼ������
//		mx = 6;			//����ֱ��������*ONE_STEP���������þ���λ��
//		my = 3;			//����ű��������ͼ������
		aBomberMan = new BomberMan(this, 6, 3);
	}
	
	private void blockInit() {
		//��ʼ��ʯͷ0
		Icon ic = new ImageIcon("src/images/block.png");

		for(int i = 0; i<dates.length; i++) {				//������ͼ��������ʯͷ
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
		//��ʼ������1		ͬ����������
		for(int i = 0; i<dates.length; i++) {
			for (int j = 0; j < dates[i].length; j++) {
				if(dates[i][j] == 1)
				{
					JLabel lab_case = new JLabel(new ImageIcon("src/images/case.png"));
					lab_case.setBounds(ONE_STEP*j, ONE_STEP*i, ONE_STEP, ONE_STEP);
					this.add(lab_case);
					//ÿ�����Ӷ�������������㱬ը���Ƴ�,����box���
                    lab_case.setText("box");
					boxMap.put(String.valueOf(j) + String.valueOf(i),lab_case);
				}
			}
		}
		this.validate();
		this.repaint();
		this.revalidate();
	}


	public void setGameFrameUi() {
		
		//���û�������
    	this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("ը����  v1.0");
		this.setSize(726,754);
		this.setLocation(500, 180);
		this.setResizable(false);
		this.setVisible(true);
		this.addKeyListener(this);
	}
	
	public void backgroundInit() {		
		
		//��ɫ������ʼ��
//		Icon i = new ImageIcon("src/images/startGame.png");
//        JLabel lab_startGame = new JLabel(i);
//        lab_startGame.setBounds(0, 0, 720, 720);
//        super.add(lab_startGame);
//        lab_startGame.setIcon(i);
	}

	public void keyTyped(KeyEvent e) {
		
	} 

	int bomberman_direction_with_bomb = 40;  //��¼��ǰ����ķ���,Ĭ��Ϊ40,��Ϊ40λ���̵��¼��̣�������
	int flag2 = 0;  //��¼�Ƿ�Ͷ����ը��
	boolean exit_flag = false;	//��¼����������˳�ѡ���Ƿ����˻س���
	boolean man_dead = false;
	//���¼���

	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(man_dead) {
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
					this.dispose();			//�˳�λ�ü������س����رմ���
				}
				else {
					this.dispose();			//�˳�λ�ü������س����رմ���
					new StartGame();
				}
			}
			
		}
		
		if(man_dead == false) {
			
			//32Ϊ�ո�
			int x = aBomberMan.lab_man.getX();	//��ȡ�����xy
			int y = aBomberMan.lab_man.getY();	//�����ո�Ͷ��ը��
			if(key == 32) {
	            aBomberMan.setBomb(x, y);
			}
	
			//37��   38��  39 ��   40��
			//0����ʯͷ��1�������ӣ�4����½��
	
			System.out.println(key);
			//��
			//�ϰ��ж������Ӻ�ʯͷ����Ӧ
			if(key == KeyEvent.VK_LEFT) {
			    aBomberMan.boomberManMove(Direction.getArrayCoords(aBomberMan.lab_man, Direction.LEFT));
			}
	
			//��
			if(key == KeyEvent.VK_UP) {
				aBomberMan.boomberManMove(Direction.getArrayCoords(aBomberMan.lab_man, Direction.UP));
			}
			//��
			if(key == KeyEvent.VK_RIGHT) {
				aBomberMan.boomberManMove(Direction.getArrayCoords(aBomberMan.lab_man, Direction.RIGHT));
			}
			//��
			if(key == KeyEvent.VK_DOWN) {
				aBomberMan.boomberManMove(Direction.getArrayCoords(aBomberMan.lab_man, Direction.DOWN));
			}
		}
	}

	public void keyReleased(KeyEvent e) {
	    //���̵����ж������Ƿ���Σ������
		isPeopleInDanger();
	}


	void manGoDead() {
		GOUI.gameOverUI_Add();
		System.out.println("Game Over!");
		Icon i = new ImageIcon("src/images/Dead.png");
		aBomberMan.lab_man.setIcon(i);
		man_dead = true;
	}
	public boolean isPeopleInDanger() {
		if (dates[aBomberMan.lab_man.getY()/ONE_STEP][aBomberMan.lab_man.getX()/ONE_STEP] == FIRE_IN_ROUTE || isManMeetMonster()) {
		    manGoDead();
			return true;
		}
		return false;
	}
	boolean isManMeetMonster () {
//		System.out.println("lab_man:" + Direction.getArrayCoords(aBomberMan.lab_man, Direction.MIDDLE));
		Set<Map.Entry<Thread, Monster>> set = monsterMap.entrySet();

		for (Iterator<Map.Entry<Thread, Monster>> iterator = set.iterator(); iterator.hasNext(); ) {
			Map.Entry<Thread, Monster> monsterEntry = iterator.next();
//			System.out.println("monster:" + Direction.getArrayCoords(monsterEntry.getValue().monster, Direction.MIDDLE));
			if(Direction.isCoordsEqual(monsterEntry.getValue().monster, aBomberMan.lab_man)) {
				System.out.println("Monster and bomberMan are in same place!!");
				return true;
			}
		}
		return false;
	}

}

