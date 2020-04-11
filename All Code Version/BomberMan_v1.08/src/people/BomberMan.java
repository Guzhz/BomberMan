package people;

import core.StartGame;
import goods.Bomb;
import handler.Direction;

import javax.swing.*;
import java.util.LinkedHashMap;

import static core.StartGame.*;

public class BomberMan {
    public boolean bomber_man_invincible = false;
    public int bomb_count = 3;
    public int blood_count = 6;			//设置人物生命数量
	public JLabel lab_man;
    //存放炸弹对象
    public LinkedHashMap<String, Bomb> bombMap = new LinkedHashMap<String, Bomb>();

    int mx;
    int my;
    StartGame gameframe = null;
    public BloodCount BC;				//new一个血量类

    public BomberMan(StartGame gameframe, int man_x, int man_y) {
        //初始化人物
        this.mx = man_x;			//这里直接用坐标*ONE_STEP像素来设置具体位置
        this.my = man_y;			//坐标放标在数组地图中描述
        Icon ic = new ImageIcon("src/images/man3.png");		//开始为正面的人物，所以为man3
        this.lab_man = new JLabel(ic);
        lab_man.setBounds(mx*ONE_STEP, my*ONE_STEP, ONE_STEP, ONE_STEP);
        this.gameframe = gameframe;
        gameframe.add(lab_man);
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
        if (gameframe.dates[map_x][map_y] == ROCK_IN_ROUTE ||
                gameframe.dates[map_x][map_y] == BOX_IN_ROUTE ||
                gameframe.dates[map_x][map_y] == BOMB_IN_ROUTE) {
            return false;
        }
        return true;
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
    public void boomberManMove(Direction.Coords array_coords) {
        if (!canWalk(array_coords.X, array_coords.Y)){
            setBoomberIcon(array_coords.direction);
            System.out.println("=====can not walk===");
            return;
        }
        //4为空地，改变坐标重写
        Icon i = null;
        switch (array_coords.direction) {
            case Direction.DOWN:
                Direction.go(lab_man, Direction.DOWN);
                break;
            case Direction.UP:
                Direction.go(lab_man, Direction.UP);
                break;
            case Direction.LEFT:
                Direction.go(lab_man, Direction.LEFT);
                break;
            case Direction.RIGHT:
                Direction.go(lab_man, Direction.RIGHT);
                break;
        }
        //爆炸时专用图片
        setBoomberIcon(array_coords.direction);
        bomberman_direction_with_bomb = array_coords.direction;
    }


    //设置炸弹
    public void setBomb(int map_x, int map_y) {

        JLabel lab_boomman = new JLabel();			//这里就是切换了夹带炸弹的人物的图片
        lab_boomman.setLocation(map_x,map_y);		//新图片就是本来人物的xy左边，所以就直接获取人物的xy
        Icon i;

        //按下空格后判断人物方向，再切换本方向的对应夹着炸弹的人的图片
        if(bomberman_direction_with_bomb ==Direction.LEFT) {
            setBoomberIcon(Direction.LEFT * 10 + Direction.LEFT);
        }
        else if(bomberman_direction_with_bomb == Direction.UP) {
            setBoomberIcon(Direction.UP * 10 + Direction.UP);
        }
        else if(bomberman_direction_with_bomb == Direction.RIGHT) {
            setBoomberIcon(Direction.RIGHT * 10 + Direction.RIGHT);
        }
        else {
            setBoomberIcon(Direction.DOWN * 10 + Direction.DOWN);
        } 

//        if(bomb_count > 0) {

        if(bombMap.size()  < bomb_count && gameframe.dates[map_y / ONE_STEP][map_x/ONE_STEP] != BOMB_IN_ROUTE) {
            Bomb b = new Bomb(gameframe, map_x, map_y);
            gameframe.add(b.bomb_obj);
//            bomb_count--;
            bombMap.put(String.valueOf(map_x) + String.valueOf(map_y), b);
        }

    }

}
