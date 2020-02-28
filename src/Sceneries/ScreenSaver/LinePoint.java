package Sceneries.ScreenSaver;

import java.util.LinkedList;
import java.util.Random;

public class LinePoint {
    private int width;
    private int height;

    private int x;
    private int y;

    private boolean dirX;
    private boolean dirY;

    private int dX;
    private int dY;

    private int maxStepSize;
    private int maxSizeHistory;

    private Random rng;

    private LinkedList<Integer> xHistory;
    private LinkedList<Integer> yHistory;

    public LinePoint(int width, int height, int x, int y, boolean dirX, boolean dirY, int maxStepSize, int sizeHistory){
        this.rng = new Random();

        this.width = width;
        this.height = height;

        this.maxStepSize = maxStepSize;
        this.maxSizeHistory = sizeHistory;

        this.x = x;
        this.y = y;

        this.dirX = dirX;
        this.dirY = dirY;

        this.dX = rng.nextInt(maxStepSize);
        this.dY = rng.nextInt(maxStepSize);

        this.xHistory = new LinkedList<>();
        this.yHistory = new LinkedList<>();
    }

    public boolean takeStep(){
        this.manageHistory();

        if (this.dirX){
            this.x += this.dX;
        } else {
            this.x -= this.dX;
        }

        if (this.dirY){
            this.y += this.dY;
        } else {
            this.y -= this.dY;
        }

        if (this.x >= this.width){
            this.dirX = !this.dirX;
            this.dX = this.rng.nextInt(this.maxStepSize) + 1;
            this.x = this.width - 1;
            return true;

        } else if (this.x < 0){
            this.dirX = !this.dirX;
            this.dX = this.rng.nextInt(this.maxStepSize) + 1;
            this.x = 0;
            return true;
        }

        if (this.y >= this.height){
            this.dirY = !this.dirY;
            this.dY = this.rng.nextInt(this.maxStepSize) + 1;
            this.y = this.height - 1;
            return true;

        } else if (this.y < 0){
            this.dirY = !this.dirY;
            this.dY = this.rng.nextInt(this.maxStepSize) + 1;
            this.y = 0;
            return true;
        }

        return false;
    }

    private void manageHistory(){
        this.xHistory.add(this.x);
        this.yHistory.add(this.y);

        if (this.xHistory.size() >= this.maxSizeHistory){
            this.xHistory.removeFirst();
            this.yHistory.removeFirst();
        }


    }

    public int getX(){ return this.x; }

    public int getY(){ return this.y; }

    public LinkedList<Integer> getHistoryX(){
        return this.xHistory;
    }

    public LinkedList<Integer> getHistoryY(){
        return this.yHistory;
    }
}
