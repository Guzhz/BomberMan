package handler;

import core.StartGame;
import goods.Bomb;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Handler {
    StartGame gameframe = null;

    public Handler(StartGame gameframe) {
        this.gameframe = gameframe;
    }

    public void checkFire() {
        Set<Map.Entry<String, Bomb>> set = gameframe.aBomberMan.bombMap.entrySet();
        for (Iterator<Map.Entry<String, Bomb>> iterator = set.iterator(); iterator.hasNext(); ) {
            try {
                Map.Entry<String, Bomb> next = iterator.next();
                if (next.getValue().isFirePlace()) {
                    next.getValue().bombRightNow();
                }
            } catch (ConcurrentModificationException e) {
                System.out.println("Fire Handler:异步修改出错" + e);
                break;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Fire Handler:空指针"+ e);
            }
        }
    }
}
