package goods;

import core.StartGame;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

import static core.StartGame.*;


public class Bomb extends TimerTask {

    private static final int FIRE_EXIST_TIME = 2000;
    private Icon ic = new ImageIcon("src/images/boom.png");
    public JLabel bomb_obj = new JLabel(ic);
    private StartGame gameframe;
    int bomb_x, bomb_y;

    //炸弹初始化
    public Bomb(StartGame gameframe, int bomb_x, int bomb_y) {
        init(bomb_obj);
        this.bomb_x = bomb_x;
        this.bomb_y = bomb_y;
        this.gameframe = gameframe;
        gameframe.add(bomb_obj);
        setCoord(bomb_x, bomb_y);
        countDown(); //倒计时爆炸
    }

    /**
     * 物件初始化大小
     * @param obj
     */
    public static void init(JLabel obj) {
        obj.setBounds(ONE_STEP, ONE_STEP, ONE_STEP, ONE_STEP);
    }


    public void setCoord(int x, int y) {
        this.bomb_obj.setLocation(x, y);
    }

    private void countDown() {
        Timer t1 = new Timer();
        t1.schedule(this, 2000); //定时器，2秒后执行run的内容
    }


    @Override
    public void run() {
        bomb_obj.setVisible(false);
        gameframe.remove(bomb_obj);
        new Fire();
    }


    class Fire extends TimerTask{
        public ArrayList<JLabel> fire_set = new ArrayList<JLabel>();

        public Fire() {
            //火焰一旦初始化，就倒计时火焰消失
            countFireDown();
        }

        private void countFireDown() {
            calculateExplodeArea();
            new Timer().schedule(this, FIRE_EXIST_TIME); //2火焰秒后火焰消失
        }

        @Override
        public void run() {
            cleanFire(); //火焰消失

        }

        /**
         * 清除火焰
         */
        void cleanFire() {
                for (Iterator<JLabel> iterator = fire_set.iterator(); iterator.hasNext(); ) { //遍历火焰集合
                    JLabel fire = iterator.next();
                    fire.setVisible(false);
                    setPlaceSafe(fire.getY()/ONE_STEP, fire.getX()/ONE_STEP);
                    gameframe.remove(fire);
                }
        }


        private void setPlaceSafe(int map_x, int map_y) {
            if (gameframe.dates[map_x][map_y] == FIRE_IN_ROUTE) {
                gameframe.dates[map_x][map_y] = NORMAL_ROUTE;
            }
        }

        private void setPlaceDanger(int map_x, int map_y) {
            gameframe.dates[map_x][map_y] = FIRE_IN_ROUTE;
        }


        //用于防止火焰穿墙
        boolean flag_left = true;
        boolean flag_right= true;
        boolean flag_up = true;
        boolean flag_down = true;


        /**
         * 计算火焰范围
         */
        private void calculateExplodeArea() {
            int fire_area = 5 * ONE_STEP; //fire_area为火焰偏移量

            explode(0, 0);

            for (int i = ONE_STEP; i <= fire_area; i+=ONE_STEP) {
                if (flag_up) {
                    explode(0, -i);
                }
                if (flag_left) {
                    explode(-i, 0);
                }
                if (flag_right) {
                    explode(i, 0);
                }
                if (flag_down) {
                    explode(0, i);
                }
            }

        }

        void getMap(int type) {
            System.out.println("=============================");
            for (int i = 0; i < gameframe.dates.length; i++) {
                for (int j = 0; j < gameframe.dates[i].length; j++) {
                    if (gameframe.dates[i][j]==9) {
                        switch (type) {
                            case 2:
                                System.out.print("|" + gameframe.dates[i][j]);
                                break;
                            case FIRE_IN_ROUTE:
                                System.out.print("|*");
                                break;
                            case BOX_IN_ROUTE:
                                System.out.print("|+");
                                break;
                            case ROCK_IN_ROUTE:
                                System.out.print("|#");
                                break;
                        }
                    } else
                        System.out.print("| ");
                }
                System.out.println();
            }
        }



        private void explode(int offsetX, int offsetY) {
            int coordX = bomb_x + offsetX ;
            int coordY = bomb_y + offsetY;
            int map_y = coordX / ONE_STEP;
            int map_x = coordY / ONE_STEP;
            //如果是平地，设置该位置为危险，用FIRE_IN_ROUTE表示
            if (gameframe.dates[map_x][map_y] == NORMAL_ROUTE) {
                setPlaceDanger(map_x, map_y);
            }

            //炸到箱子或者石头，该方向停止
            if(gameframe.dates[map_x][map_y]== 0 || gameframe.dates[map_x][map_y]==1) {
                if (offsetX > 0) {
                    flag_right = false;
                } else if(offsetX < 0) {
                    flag_left = false;
                }
                if (offsetY > 0) {
                    flag_down = false;
                } else if(offsetY < 0) {
                    flag_up = false;
                }
                if (gameframe.dates[map_x][map_y] == BOX_IN_ROUTE) {
                    gameframe.dates[map_x][map_y] = NORMAL_ROUTE; //如果是箱子，转换成平地
                    //重绘
                    JLabel abox = gameframe.boxMap.get(String.valueOf(map_y) + String.valueOf(map_x));
                    gameframe.remove(abox);
                }
            }

            JLabel fire = new JLabel(new ImageIcon("src/images/fire.png"));
            init(fire); //初始化火焰的大小
            fire.setLocation(coordX, coordY); //放置火焰到地图
            gameframe.add(fire);
            fire_set.add(fire);  //把火焰存进集合
            //对主框架进行重绘
            gameframe.repaint();
            gameframe.revalidate();
            getMap(2);
        }

    }
}

