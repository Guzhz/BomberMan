package game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

// 继承父类
public class Bad1 extends Sprite {
    //这四个方向根据原图从上到下而定，不可修改
    private static final int DOWN = 0;
    private static final int LEFT= 1;
    private static final int RIGHT = 2;
    private static final int UP = 3;
    private int dx;
    private int dy;
    public int currentFrame = 0;
    //横排
    private final int IMG_COLUMN = 3;
    //纵列
    private final int IMG_ROW = 4;
    public int width;
    private int height;
    //方向
    private int directon;
    int moving_speed = 4;


    public Bad1(int x, int y) {
        super(x, y);
        initBad1();
    }

    //初始化
    private void initBad1() {
        //载入图片
        loadImage("src/resources/characteristic/bad1.png");
        //
        getImageDimensions();
        movingAnimate();
    }

    @Override
    protected void getImageDimensions() {
        super.getImageDimensions();
        //图片的总宽度除于单行人物数量就是单张人物的宽度
        width = image.getWidth(null) / IMG_COLUMN;
        height = image.getHeight(null) / IMG_ROW;
    }

    @Override
    public Image getImage() {
        updateImage();
        //裁剪所需要的图片
        image = bufimg.getSubimage(srcX, srcY, width, height);
        return image;
    }

    int srcX;
    int srcY;
    private void updateImage() {
        //原图片是X轴方向是同个方向的人物移动分解图，需要在该方向上进行来回切换以达到动画效果
        srcX = currentFrame * width;

        //Y轴方向是不同方向的图
        srcY = getDirection() * height;
    }

    private int getDirection() {
        return directon;
    }

    Timer t = new Timer();
    int frame = 0;
    public void movingAnimate(){
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                //动画的关键，横排位置切换, frame作为开关, 当frame = 0;currentFrame不变，当frame = 1, currentFrame不断从0 - 3三个帧之间切换
                currentFrame =  (currentFrame + frame)  % IMG_COLUMN;
            }
        }, 0, 100); //100 是(三张图)动画切换速度,单位毫秒,该动画由上面的Timer线程不断执行
    }


    //后台线程不断执行该函数，当按钮没有按下时，dx 和dy 均为0，所以是没有移动的，当按下时做出相应的改变,比如当按下左键,dx = 2，则 x = x + 2;则不断左移动
    public void move() {
        x += dx;
        y += dy;
    }

    private void setDirection(int directon) {
        this.directon = directon;
        isKeyPress[directon] = true;
        switch (directon) {
            case LEFT:
                dx = -moving_speed;
                break;
            case RIGHT:
                dx = moving_speed;
                break;
            case UP:
                dy = -moving_speed;
                break;
            case DOWN:
                dy = moving_speed;
                break;
        }
    }


    private void releaseDiretion(int directon) {
        isKeyPress[directon] = false;
        switch (directon) {
            case LEFT:
                dx = 0;
                if (isKeyPress[RIGHT]) {
                    setDirection(RIGHT);
                }
                break;
            case RIGHT:
                dx = 0;
                if (isKeyPress[LEFT]) {
                    dx = -moving_speed;
                    setDirection(LEFT);
                }
                break;
            case UP:
                dy = 0;
                if (isKeyPress[DOWN]) {
                    dy = -moving_speed;
                    setDirection(DOWN);
                }
                break;
            case DOWN:
                dy = 0;
                //防止当多个按钮按下时，不走动
                if (isKeyPress[UP]) {
                    dy = moving_speed;
                    setDirection(UP);
                }
                break;
        }

        //此段代码遍历是否有其它按键按下，防止滑翔
        for (int i = 0; i < isKeyPress.length; i++) {
            if (isKeyPress[i]) {
                frame = 1;
                break;
            }
            if (i >= isKeyPress.length - 1) {
                frame = 0;
            }
        }
    }


    //四个方向,用于储存该方向是否被按下，按下则true, 松开则false
    boolean[] isKeyPress = new boolean[4];
    public void keyPressed(KeyEvent event) {
        frame = 1;
        int key = event.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            setDirection(LEFT);
        }
        if (key == KeyEvent.VK_RIGHT) {
            setDirection(RIGHT);
        }
        if (key == KeyEvent.VK_UP) {
            setDirection(UP);
        }
        if (key == KeyEvent.VK_DOWN) {
            setDirection(DOWN);
        }
    }


    public void keyReleased(KeyEvent event){
        frame = 0;
        int key = event.getKeyCode();
        if (key == KeyEvent.VK_LEFT){
            releaseDiretion(LEFT);
        }
        if (key == KeyEvent.VK_RIGHT) {
            releaseDiretion(RIGHT);
        }
        if (key == KeyEvent.VK_UP) {
            releaseDiretion(UP);
        }
        if (key == KeyEvent.VK_DOWN ){
            releaseDiretion(DOWN);
        }
    }
}
