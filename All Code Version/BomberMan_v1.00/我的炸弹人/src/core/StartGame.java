package core;


import goods.Bomb;
import monster.Monster;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * @author Guzhz
 * @date 2018��10��29��
 * @time ����4:08:12
 */

public class StartGame extends JFrame implements KeyListener {

	public static final int ONE_STEP = 48;
	public static final int FIRE_IN_ROUTE = 9;
	public static final int NORMAL_ROUTE = 4;
	public static final int BOX_IN_ROUTE = 1;
	public static final int ROCK_IN_ROUTE = 0;

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
	

	
	public StartGame() {
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
			Monster m = new Monster(this, 6, 4, moving_speed);
			Thread t = new Thread(m);
            t.start();
            monsterMap.put(t,m);
		}
	}


	//��������
	public void lifeHandler(JLabel jlabel) {
	   this.remove(jlabel);
	   this.repaint();
	}

	//0����ʯͷ��1�������ӣ�4����½�أ�8����ը��
	JLabel lab_man;
	int mx;
	int my;
	
	private void manInit() {
		//��ʼ������
		mx = 6;			//����ֱ��������*ONE_STEP���������þ���λ��
		my = 3;			//����ű��������ͼ������
		Icon ic = new ImageIcon("src/images/manB.png");		//��ʼΪ������������ΪmanB
		lab_man = new JLabel(ic);
		lab_man.setBounds(mx*ONE_STEP, my*ONE_STEP, ONE_STEP, ONE_STEP);
		this.add(lab_man);
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
		
		//���û�������
    	this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("ը����  v1.0");
		this.setSize(738,765);
		this.setLocation(500, 180);
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

	int flag = 40;  //��¼��ǰ����ķ���,Ĭ��Ϊ40,��Ϊ40λ���̵��¼��̣�������
	int flag2 = 0;  //��¼�Ƿ�Ͷ����ը��
	
	//���¼���
	public void keyPressed(KeyEvent e) {		
		
		int key = e.getKeyCode();
		//32Ϊ�ո�

		int x = (int)lab_man.getLocation().getX();	//��ȡ�����xy
		int y = (int)lab_man.getLocation().getY();	//�����ո�Ͷ��ը��

		if(key == 32) {
			JLabel lab_boomman = new JLabel();			//��������л��˼д�ը���������ͼƬ
			lab_boomman.setLocation(x,y);				//��ͼƬ���Ǳ��������xy��ߣ����Ծ�ֱ�ӻ�ȡ�����xy
			Icon i;										
			
			
			//���¿ո���ж����﷽�����л�������Ķ�Ӧ����ը�����˵�ͼƬ
			if(flag==37) {
				flag2 = 1;		//
				i = new ImageIcon("src/images/manBL.png");

			}
			else if(flag == 38) {
				flag2 = 1;
				i = new ImageIcon("src/images/manBT.png");
			}
			else if(flag == 39) {
				flag2 = 1;
				i = new ImageIcon("src/images/manBR.png");
			}
			else {
				flag2 = 1;
				i = new ImageIcon("src/images/manBB.png");
			}

			ArrayList<Bomb> bombMap = new ArrayList<Bomb>();
			lab_man.setIcon(i);
			Bomb b = new Bomb(this, x, y);
			this.add(b.bomb_obj);

			bombMap.add(b);
		}
	
		//37��   38��  39 ��   40��
		//0����ʯͷ��1�������ӣ�4����½��
		
		System.out.println(key);
		//��
		//�ϰ��ж������Ӻ�ʯͷ����Ӧ
		if(key == 37) {
			if(dates[my][mx-1]==0 || dates[my][mx-1]==1) {
				return;
			}
			if(dates[my][mx-1]==4) {
				//4Ϊ�յأ��ı�������д
					flag = 37;
					dates[my][mx]=4;
					mx = mx-1;
					lab_man.setLocation(x-ONE_STEP,y);
					Icon i = new ImageIcon("src/images/manL.png");
					lab_man.setIcon(i);
			}
		}

		//��	
		if(key == 38) {
			if(dates[my-1][mx]==0 || dates[my-1][mx]==1) {
				return;
			}
			if(dates[my-1][mx]==4) {
				flag = 38;
				dates[my][mx]=4;
				my = my-1;
				lab_man.setLocation(x,y-ONE_STEP);
				Icon i = new ImageIcon("src/images/manT.png");
				lab_man.setIcon(i);
			}		
		}
		//��
		if(key == 39) {
			if(dates[my][mx+1]==0 || dates[my][mx+1]==1 ) {
				return;
			}
			if(dates[my][mx+1]==4) {
				flag = 39;
				dates[my][mx]=4;
				mx = mx+1;
				lab_man.setLocation(x+ONE_STEP,y);
				Icon i = new ImageIcon("src/images/manR.png");
				lab_man.setIcon(i);
			}
		}
		//��
		if(key == 40) {
			if(dates[my+1][mx]==0 || dates[my+1][mx]==1) {
				return;
			}
			if(dates[my+1][mx]==4) {
				flag = 40;
				dates[my][mx]=4;
				my = my+1;
				lab_man.setLocation(x,y+ONE_STEP);
				Icon i = new ImageIcon("src/images/manB.png");
				lab_man.setIcon(i);
			}
		}
		
	}


	public void keyReleased(KeyEvent e) {} 
	
}
