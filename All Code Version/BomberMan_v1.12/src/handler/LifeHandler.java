package handler;

import core.StartGame;

import javax.swing.*;
import java.awt.*;

public class LifeHandler {
    //TODO 背景线程实时监控各物品的移动以及是否处于危险


    StartGame gameframe = null;

    public LifeHandler(StartGame gameframe) {
        this.gameframe = gameframe;
    }


    public boolean checkCoords(JLabel lab1, JLabel lab2) {
        Rectangle r1 =  lab1.getBounds();
        Rectangle r2 =  lab2.getBounds();
        if (r1.intersects(r2)) {
            return true;
        } else {
            return false;
        }
    }

    public void checkCollecions() {
        checkFire();
    }

    private void checkFire() {

    }


}
