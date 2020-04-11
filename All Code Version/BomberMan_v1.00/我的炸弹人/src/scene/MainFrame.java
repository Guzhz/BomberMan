package scene;			//����

import core.StartGame;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * 
 * @author Guzhz
 * @date 2018��10��29��
 * @time ����11:27:31
 */


//�̳�JFrame  ʵ��KeyListener
public class MainFrame extends JFrame implements KeyListener{
	public void MainFrame() { 
		setSelect();					//ѡ�񷽷�
		StartInit();					//��ʼ�����ʼ��
		setMainFrameUi();				//����������
		this.addKeyListener(this);		//����
	}
	
	JLabel lab_monster;						//��ʼ�����ƶ���Ϊ����������������Ϊȫ�ֱ���
	public static final int MOS_X = 220;	//����ѡ�񰴼��Ĺ���x����
	public static final int MOS_Y = 540;	//����ѡ�񰴼��Ĺ���y����
	
	//ѡ�񰴼��Ĺ����ƶ�����
	private void setSelect() {			
		Icon i = new ImageIcon("src/images/monster.png");	//�������ͼƬ
		lab_monster = new JLabel(i);						//�����ǩ��
		lab_monster.setBounds(MOS_X, MOS_Y, 48, 48);		//��������
		this.add(lab_monster);								//��ӵ�������
		
		i = new ImageIcon("src/images/StGame.png");			//��ʼ��ϷΪһ��ͼƬ
		JLabel lab_st = new JLabel(i);
		lab_st.setBounds(275, 540, 192, 48);
		this.add(lab_st);
		
		i = new ImageIcon("src/images/ExGame.png");			//������ϷҲΪһ��ͼƬ
		JLabel lab_ex = new JLabel(i);
		lab_ex.setBounds(275, 588, 192, 48);
		this.add(lab_ex);
		
	}

	//���ÿ�ʼ�����ͼƬ
	private void StartInit() {
		Icon i = new ImageIcon("src/images/start.png");
		JLabel lab_start = new JLabel(i);
		lab_start.setBounds(0, 0, 720, 720);
		this.add(lab_start);		
		
	}
	
	//����������
	public void setMainFrameUi() {
    	this.setLayout(null);			//����ΪnullΪ���ɲ���
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//�����رհ�����رմ���
		this.setTitle("ը����  v1.0");
		this.setSize(738,765);			//�����С
		this.setLocation(500, 180);		//������ʾ�ڵ����е�λ��
		this.setVisible(true);			//���ڿɼ�
	}

	//ʵ�ֵļ����ӿ��е�  type���Ϳ��Բ�ͬ��
	public void keyTyped(KeyEvent e) {		
		
	}
	
	//ʵ�ּ��̼����еİ���ȥ���� press
	public void keyPressed(KeyEvent e) {		
		int key = e.getKeyCode();		//��ȡ����ֵ
		
		//38Ϊ����� ���� �ļ���ֵ
		if(key == 38) {
			int x = (int)lab_monster.getLocation().getX();		//��ȡ�����ǩ��x���귽��
			int y = (int)lab_monster.getLocation().getY();		//��ȡ�����ǩ��y���귽��
			
			if(y == MOS_Y)	//�����ϰ������ڿ�ʼ��Ϸλ������������ϰ�ť����ֱ��return(����������)								
				return;
			else {			//��������ƶ���������������˳���ťλ�ã������ϣ�����д��ǩλ��
				lab_monster.setLocation(x,y-48);
				Icon i = new ImageIcon("src/images/monster.png");
				lab_monster.setIcon(i);
			}
		}
		
		//40Ϊ����� ���� �ļ���ֵ
		if(key == 40) {			//�������෴�ĵ���
			int x = (int)lab_monster.getLocation().getX();
			int y = (int)lab_monster.getLocation().getY();
			
			if(y==(MOS_Y+48))	//�ж�С�����λ��
				return;
			else {
				lab_monster.setLocation(x,y+48);
				Icon i = new ImageIcon("src/images/monster.png");
				lab_monster.setIcon(i);	
			}
		}
		
		//����ֱ���ж�y��λ�ã�������������س�������������Ӧ�Ĵ���
		//10Ϊ�س�����
		int y = (int)lab_monster.getLocation().getY();
		if(y==MOS_Y && key==10 ) {
			this.dispose();			//y�պ��ڿ�ʼλ�ô������Ҽ������س�
			new StartGame();		//�͹ر�������ڣ������µĴ��ڣ��´��ڵ����Ժͱ����ڵ�����һ��
		}
		
		if(y==MOS_Y+48 && key==10  ) {
			this.dispose();			//������˳���ť��λ�ã��������س���ֱ�ӹرմ���
		}
	
	}

	//����û�����ü��̵ĵ������
	public void keyReleased(KeyEvent e) {
	
	}	
}
