package handler;

import javax.swing.*;

import static core.StartGame.ONE_STEP;

public class Direction {
    public static final int UP = 1;
    public static final int RIGHT = 2;
    public static final int DOWN = 3;
    public static final int LEFT = 4;

    /**
     *
     * @param lab_obj 传递要移动的Jpanel
     * @param DIRECTION  传递方向，分别有Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT
     */
    static public void go(JLabel lab_obj, int DIRECTION) {
        switch (DIRECTION) {
            case LEFT:
                lab_obj.setLocation(lab_obj.getX() - ONE_STEP, lab_obj.getY());
                break;
            case RIGHT:
                lab_obj.setLocation(lab_obj.getX() + ONE_STEP, lab_obj.getY());
                break;
            case UP:
                lab_obj.setLocation(lab_obj.getX(), lab_obj.getY() - ONE_STEP);
                break;
            case DOWN:
                lab_obj.setLocation(lab_obj.getX(), lab_obj.getY() + ONE_STEP);
                break;
        }
    }

    public class Coords {
        public int X;
        public int Y;
        public int direction;
        Coords (int x, int y, int direction) {
            this.X = x;
            this.Y = y;
            this.direction = direction;
        }
    }


    static public Coords getRealCoords(JLabel lab_obj, int DIRECTION) {
        switch (DIRECTION) {
            case LEFT:
					return new Direction().new Coords(lab_obj.getX()-ONE_STEP, lab_obj.getY(), LEFT);
				case RIGHT:
                    return new Direction().new Coords(lab_obj.getX() + ONE_STEP, lab_obj.getY(), RIGHT);
				case UP:
                    return new Direction().new Coords(lab_obj.getX(), lab_obj.getY() - ONE_STEP, UP);
				case DOWN:
                    return new Direction().new Coords(lab_obj.getX(), lab_obj.getY() + ONE_STEP, DOWN);
        }
        return null;
    }
    
    static public Coords getArrayCoords(JLabel lab_obj, int DIRECTION) {
        switch (DIRECTION) {
            case LEFT:
                return new Direction().new Coords((lab_obj.getY()) / ONE_STEP, (lab_obj.getX() - ONE_STEP) / ONE_STEP, LEFT);
            case RIGHT:
                return new Direction().new Coords((lab_obj.getY()) / ONE_STEP, (lab_obj.getX() + ONE_STEP) / ONE_STEP, RIGHT);
            case UP:
                return new Direction().new Coords((lab_obj.getY() - ONE_STEP) / ONE_STEP, (lab_obj.getX()) / ONE_STEP, UP);
            case DOWN:
                return new Direction().new Coords((lab_obj.getY() + ONE_STEP) / ONE_STEP, (lab_obj.getX()) / ONE_STEP, DOWN);
        }
        return null;
    }
}