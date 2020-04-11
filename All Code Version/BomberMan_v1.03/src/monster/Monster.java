package monster;

import core.StartGame;

import javax.swing.*;
import java.util.LinkedList;
import java.util.Random;

import static core.StartGame.FIRE_IN_ROUTE;
import static core.StartGame.NORMAL_ROUTE;

public class Monster implements  Runnable{
    private static final int ONE_STEP = 48;
    public JLabel monster;
    int init_x;
    int init_y;
    StartGame gameframe = null;
    long MOVING_SPEED;


    public Monster(StartGame gameframe , int init_x, int init_y,long MOVING_SPEED) {
        this.gameframe = gameframe;
        this.init_x = init_x;
        this.init_y = init_y;
        this.MOVING_SPEED = MOVING_SPEED;
        monsterInit();
    }

    private void monsterInit() {
        //初始化人物
//        init_x = 6;			//这里直接用坐标*ONE_STEP像素来设置具体位置
//        init_y = 3;			//坐标放标在数组地图中描述
        Icon ic = new ImageIcon("src/images/monster.png");
        monster = new JLabel(ic);
        monster.setBounds(init_x*ONE_STEP, init_y*ONE_STEP, ONE_STEP, ONE_STEP);
        gameframe.add(monster);
    }

    boolean is_monster_die = false;
    @Override
    public void run() {
        try {
            while (true) {
                new Thread().sleep(MOVING_SPEED);
                monsterMove();
            }
        } catch (Exception e) {
            System.out.println("Moving ..fail" + e);
        }
    }

    public void monsterMove() {
        int[] coords = null;
        coords = checkRoute();
        if (coords == null) {
            System.out.println("No Route!");
            return;
        }

        int coordX = coords[0];
        int coordY = coords[1];
        int map_x = coordY/ONE_STEP;
        int map_y = coordX/ONE_STEP;

        gameframe.isPeopleInDanger();
        monster.setLocation(coordX, coordY);
        gameframe.repaint();


    }


    /**
     * 返回一个可行的方向数组
     * @return
     */
    public int[] checkRoute() {
        //创建一个包含四个数组的容器，由于需要经常增删，故用链表
        LinkedList<Integer> set = new LinkedList<Integer>();
        for (int i = 0; i < 4; i++) {
            set.add(i);
        }

        int direction = -1;

        int checkingX;
        int checkingY;

        while(set.size() > 0) {
            checkingX = monster.getX();
            checkingY = monster.getY();
            int r = new Random().nextInt(set.size());
            direction = set.remove(r) + 1; //从四个方向中取一个，直到为空

            //根据方向生成临时坐标
            switch (direction) {
                case 1:
                    checkingY = monster.getY() + ONE_STEP;
                    break;
                case 2:
                    checkingY = monster.getY() - ONE_STEP;
                    break;
                case 3:
                    checkingX = monster.getX() - ONE_STEP;
                    break;
                case 4:
                    checkingX = monster.getX() + ONE_STEP;
                    break;
            }

            //判读路径是否有障碍
            int map_x = checkingY/ONE_STEP;  //地图X轴
            int map_y = checkingX/ONE_STEP;  //地图Y轴
            if (gameframe.dates[map_x][map_y] == FIRE_IN_ROUTE) {
                Thread t = new Thread().currentThread();
                System.out.println("Danger!!!");
                is_monster_die = true;
                gameframe.remove(this.monster);
                gameframe.repaint();
                t.stop();
            }

            if (gameframe.dates[map_x][map_y] == NORMAL_ROUTE) {
                return new int[]{checkingX, checkingY};
            }
        }
        return null;
    }

}


