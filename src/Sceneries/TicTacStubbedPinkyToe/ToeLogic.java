package Sceneries.TicTacStubbedPinkyToe;

import java.awt.geom.Point2D;
import java.util.Arrays;
import java.util.Random;
import java.util.ArrayList;

public class ToeLogic {
    private int[][] toes;
    private int width;
    private int height;

    //@TODO Add logic that check if it's a tie
    //@TODO fix diagonal checkForWin

    public ToeLogic(int width, int height){
        this.toes = new int[width][height];
        this.width = width; this.height = height;
        for (int x = 0; x < width; ++x){
            for (int y = 0; y < height; ++y){
                this.toes[x][y] = 0;
            }
        }
    }

    public int[][] getToes(){ return toes; }

    public boolean placePos(Point2D place, int player){
        if (toes[(int)place.getX()][(int)place.getY()] == 0){
            toes[(int)place.getX()][(int)place.getY()] = player;
            return true;
        }
        return false;
    }

    public void botStep(){
        boolean foundPlace = false;
        Random r = new Random();
        int rW;
        int rH;
        while(!foundPlace){
            rW = r.nextInt(this.width);
            rH = r.nextInt(this.height);
            if (this.toes[rW][rH] == 0){
                foundPlace = true;
                this.toes[rW][rH] = 2;
            }
        }
    }

    public int checkForWin(){
        ArrayList<Integer> checks = new ArrayList<>(Arrays.asList(this.checkX(), this.checkY(), this.checkDiagonal()));
        if (checks.contains(1)){
            return 1;
        } else if (checks.contains(2)){
            return 2;
        }
        return 0;
    }

    private int checkX(){
        int value;
        for (int x = 0; x < width - 4; ++x){
            for (int y = 0; y < height; ++y){
                value = this.checkPlaces(new Point2D.Double(x, y), new Point2D.Double(x + 1, y), new Point2D.Double(x + 2, y), new Point2D.Double(x + 3, y), new Point2D.Double(x + 4, y));
                if (value == 1){
                    return 1;
                } else if (value == 2){
                    return 2;
                }
            }
        }
        return 0;
    }

    private int checkY(){
        int value;
        for (int y = 0; y < height - 4; ++y){
            for (int x = 0; x < width; ++x){
                value = this.checkPlaces(new Point2D.Double(x, y), new Point2D.Double(x, y + 1), new Point2D.Double(x, y + 2), new Point2D.Double(x, y + 3), new Point2D.Double(x, y + 4));
                if (value == 1){
                    return 1;
                } else if (value == 2){
                    return 2;
                }
            }
        }
        return 0;
    }

    private int checkDiagonal(){
//        int value;
//        for (int x = 0; x < width - 3; ++x){
//            for (int y = 0; y < height; ++y){
//                value = this.checkPlaces(new Point2D.Double(x, y), new Point2D.Double(x + 1, y + 1), new Point2D.Double(x + 2, y + 2), new Point2D.Double(x + 3, y + 3));
//                if (value == 1){
//                    return 1;
//                } else if (value == 2){
//                    return 2;
//                }
//            }
//        }
//
//        for (int x = 0; x < width - 3; ++x){
//            for (int y = 0; y < height; ++y){
//                value = this.checkPlaces(new Point2D.Double(x, y + 3), new Point2D.Double(x + 1, y + 2), new Point2D.Double(x + 2, y + 1), new Point2D.Double(x + 3, y));
//                if (value == 1){
//                    return 1;
//                } else if (value == 2){
//                    return 2;
//                }
//            }
//        }
        return 0;
    }

    private int checkPlaces(Point2D p1, Point2D p2, Point2D p3, Point2D p4, Point2D p5){
        int t1 = toes[(int)p1.getX()][(int)p1.getY()];
        int t2 = toes[(int)p2.getX()][(int)p2.getY()];
        int t3 = toes[(int)p3.getX()][(int)p3.getY()];
        int t4 = toes[(int)p4.getX()][(int)p4.getY()];
        int t5 = toes[(int)p5.getX()][(int)p5.getY()];

        if (t1 == 1 && t2 == 1 && t3 == 1 && t4 == 1 && t5 == 1){
            return 1;
        } else if (t1 == 2 && t2 == 2 && t3 == 2 && t4 == 2 && t5 == 2){
            return 2;
        }
        return 0;
    }
}
