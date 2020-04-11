package people;

import core.StartGame;
import goods.Bomb;
import handler.Direction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

import static core.StartGame.*;

public class BomberMan {
    public boolean bomber_man_invincible = false;
    public int bomb_count = 3;
    public int blood_count = 6;			//设置人物生命数量
	public JLabel lab_man;
    //存放炸弹对象
    public ConcurrentHashMap<String, Bomb> bombMap = new ConcurrentHashMap<>();

    int mx;
    int my;
    int real_x;
    int real_y;
    StartGame gameframe = null;
    public BloodCount BC;				//new一个血量类
    Image image;

    int dx = 0;
    int dy = 0;
    private int moving_speed = 4;

    public BomberMan(StartGame gameframe, int man_x, int man_y) {
        //初始化人物
        this.mx = man_x;			//这里直接用坐标*ONE_STEP像素来设置具体位置
        this.my = man_y;			//坐标放标在数组地图中描述
        this.real_x = man_x *ONE_STEP;
        this.real_y = man_y *ONE_STEP;
        ImageIcon ic = new ImageIcon("src/images/man3.png");		//开始为正面的人物，所以为man3
        image = ic.getImage();
        this.lab_man = new JLabel(ic);
        lab_man.setBounds(real_x, real_y, ONE_STEP, ONE_STEP);
        this.gameframe = gameframe;
        gameframe.add(lab_man);
        moveAnimateThread();
    }
    
    public void setBloodCount() {		//设置血的数量UI
    	BC = new BloodCount(this.gameframe,gameframe.aBomberMan,blood_count);
    }
    
    public void setManInvincible(boolean b) {
        if(b){
            bomber_man_invincible = true;
        }
        else{
            bomber_man_invincible= false;
        }
    }
    private boolean canWalk(int map_x, int map_y) {
        JLabel box = gameframe.boxMap.get(map_x + "" + map_y);
        if (box != null) {
            System.out.println(box);
        }
        if (gameframe.dates[map_x][map_y] == ROCK_IN_ROUTE ||
                gameframe.dates[map_x][map_y] == BOX_IN_ROUTE ||
                gameframe.dates[map_x][map_y] == BOMB_IN_ROUTE) {
            return false;
        }
        return true;
    }




    //检查对应方向的位置是否可以走
    public boolean checkRoute(int direction) {
        int srcX = lab_man.getX();
        int srcY = lab_man.getY();

        int checkingX;
        int checkingY;

        checkingX = lab_man.getX();
        checkingY = lab_man.getY();

        //根据方向生成临时坐标
        switch (direction) {
            case Direction.LEFT:
                checkingX = srcX - ONE_STEP;
                break;
            case Direction.RIGHT:
                checkingX = srcX + ONE_STEP;
                break;
            case Direction.UP:
                checkingY = srcY - ONE_STEP;
                break;
            case Direction.DOWN:
                checkingY = srcY + ONE_STEP;
                break;
        }
        System.out.println("direction :"+ direction + "Coords:" +checkingX + ":" + checkingY + "map:"+gameframe.dates[checkingX/ONE_STEP][checkingY/ONE_STEP]);
        System.out.println("Coords:" + checkingX / ONE_STEP + ":" + checkingY / ONE_STEP);
        //判读路径是否有障碍
        int map_x = checkingX / ONE_STEP;  //地图X轴
        int map_y = checkingY / ONE_STEP;  //地图Y轴
        if (gameframe.dates[map_x][map_y] == FIRE_IN_ROUTE) {
        }
        //如果有路，返回该路的坐标
        if (gameframe.dates[map_x][map_y] == NORMAL_ROUTE) {
            return true;
        }
        System.out.println("can not walk");
        return false;
    }
    void setBoomberIcon(int direction) {
    	if(bombMap.size() >= bomb_count && direction > 10) {
    		return;
    	}
        Icon i;
        String iconPath = "src/images/man" + direction + ".png";
        i  = new ImageIcon(iconPath);
        lab_man.setIcon(i);
    }
    int bomberman_direction_with_bomb;

    public void keyPress(KeyEvent e) {
        int key = e.getKeyCode();
        //32为空格
        int x = lab_man.getX();	//获取人物的xy
        int y = lab_man.getY();	//遇到空格投放炸弹
        if(key == 32) {
            setBomb(x, y);
        }
        //左
        System.out.println(KeyEvent.getKeyText(key));
        if(key == KeyEvent.VK_LEFT) {
            if (!checkRoute(Direction.LEFT)) {
                return;
            }
            dx = -moving_speed;
        }
        //上
        if(key == KeyEvent.VK_UP) {
            if (!checkRoute(Direction.UP)) {
                return;
            }
            dy = -moving_speed;
        }
        //右
        if(key == KeyEvent.VK_RIGHT) {
            if (!checkRoute(Direction.RIGHT)) {
                return;
            }
            dx = moving_speed;
        }
        //下
        if(key == KeyEvent.VK_DOWN) {
            if (!checkRoute(Direction.DOWN)) {
                return;
            }
            dy = moving_speed;
        }
    }

    public void keyRelease(KeyEvent event) {
        int key = event.getKeyCode();
        if(key == KeyEvent.VK_LEFT) {
            dx = 0;
        }
        //上
        if(key == KeyEvent.VK_UP) {
            dy = 0;
        }
        //右
        if(key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }
        //下
        if(key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }


    public void moveSloly(JLabel monster, int direction) {
        int step = ONE_STEP;
        switch (direction) {
            case Direction.RIGHT:
                while (step > 0) {
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            gameframe.repaint();
                            monster.setLocation(monster.getX() + 1, monster.getY());
                        }
                    }, 10);
                    step--;
                }
                break;
            case Direction.LEFT:
                while (step > 0) {
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            gameframe.repaint();
                            monster.setLocation(monster.getX() - 1, monster.getY());
                        }
                    }, 10);
                    step--;
                }
                break;
            case Direction.UP:
                while (step > 0) {
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            gameframe.repaint();
                            monster.setLocation(monster.getX(), monster.getY() - 1);
                        }
                    }, 10);
                    step--;
                }
                break;
            case Direction.DOWN:
                while (step > 0) {
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            gameframe.repaint();
                            monster.setLocation(monster.getX(), monster.getY() + 1);
                        }
                    }, 10);
                    step--;
                }
                break;
        }
    }

    public void moveAnimateThread() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                gameframe.repaint();
                move();
            }
        }, 0, 30);
    }

    public void move()  {
        real_x += dx;
        real_y += dy;
        lab_man.setLocation(real_x, real_y);
    }

    public void boomberManMove(Direction.Coords array_coords) {
        if (!canWalk(array_coords.X, array_coords.Y)){
//            setBoomberIcon(array_coords.direction);
            System.out.println("=====can not walk===");
            return;
        }
        //4为空地，改变坐标重写
//        Icon i = null;
        switch (array_coords.direction) {
            case Direction.DOWN:
                moveSloly(lab_man,array_coords.direction);
                break;
            case Direction.UP:
                moveSloly(lab_man,array_coords.direction);
                break;
            case Direction.LEFT:
                moveSloly(lab_man,array_coords.direction);
                break;
            case Direction.RIGHT:
                moveSloly(lab_man,array_coords.direction);
                break;
        }
        //爆炸时专用图片
        setBoomberIcon(array_coords.direction);
        bomberman_direction_with_bomb = array_coords.direction;
    }


    //设置炸弹
    public void setBomb(int map_x, int map_y) {

//        JLabel lab_boomman = new JLabel();			//这里就是切换了夹带炸弹的人物的图片
//        lab_boomman.setLocation(map_x,map_y);		//新图片就是本来人物的xy左边，所以就直接获取人物的xy
//        Icon i;

        //按下空格后判断人物方向，再切换本方向的对应夹着炸弹的人的图片
//        if(bomberman_direction_with_bomb == LEFT) {
//            setBoomberIcon(LEFT * 10 + LEFT);
//        }
//        else if(bomberman_direction_with_bomb == Direction.UP) {
//            setBoomberIcon(Direction.UP * 10 + Direction.UP);
//        }
//        else if(bomberman_direction_with_bomb == Direction.RIGHT) {
//            setBoomberIcon(Direction.RIGHT * 10 + Direction.RIGHT);
//        }
//        else {
//            setBoomberIcon(Direction.DOWN * 10 + Direction.DOWN);
//        }

//        if(bomb_count > 0) {

        if(bombMap.size()  < bomb_count && gameframe.dates[map_y / ONE_STEP][map_x/ONE_STEP] != BOMB_IN_ROUTE) {
            Bomb b = new Bomb(gameframe, map_x, map_y);
            bombMap.put(String.valueOf(map_x) + String.valueOf(map_y), b);
            gameframe.add(b.bomb_obj);
//            bomb_count--;
        }

    }

}
