package handler;

import core.StartGame;
import goods.Bomb;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class PrintMap {
    /**
     * 几种不同的打印地图方式
     * @param gameframe
     * @param type
     */
    public static void print(StartGame gameframe, int type) {
        System.out.println("================================   类型: "+type);
        
        System.out.print("       		        ");
        for(int i = 0;i<gameframe.dates.length; i++) {
        	if(i > 9)
        		System.out.print(" "+1);
        }
        System.out.println();
        
        for(int i = 0;i<gameframe.dates.length; i++) 
        	System.out.print(" "+i%10);
        
        System.out.println();
        
        System.out.println("_______________________________");
        for (int i = 0; i < gameframe.dates.length; i++) {
        	System.out.print("|");
        	for (int j = 0; j < gameframe.dates[i].length; j++) {
        		if(gameframe.dates[i][j] == type) {
        			System.out.print("■|");
        		}else {
        			System.out.print(" |");
        		}
        	}
        	System.out.println(" "+i);
        }
        System.out.println("¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯");
    }



    public static void printBomb(StartGame gameframe) {
        Set<Map.Entry<String, Bomb>> set = gameframe.aBomberMan.bombMap.entrySet();
        for (Iterator<Map.Entry<String, Bomb>> iterator = set.iterator(); iterator.hasNext(); ) {
            Map.Entry<String, Bomb> abomb = iterator.next();
            System.out.println(abomb.getKey() + ".." + abomb.getValue().getClass().getName());
        }
    }
    
}
