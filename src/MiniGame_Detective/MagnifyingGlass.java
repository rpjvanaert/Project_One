package MiniGame_Detective;

import org.jfree.fx.FXGraphics2D;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class MagnifyingGlass {
    private Shape shapeGlass;
    private int x;
    private int y;
    private int width;

    public MagnifyingGlass(){
        this.shapeGlass = new Ellipse2D.Double(1920/2 - 75,1080/2 - 75,150,150);
        this.x = 1920/2 - 75;
        this.y = 1080/2 - 75;
        this.width = 150;
    }

    public void draw(FXGraphics2D g2d){
        g2d.setPaint(Color.black);
        g2d.draw(this.shapeGlass);
    }

    public Shape getShapeGlass() {
        return shapeGlass;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth(){
        return this.width;
    }

    public void setPos(int x, int y){
        this.shapeGlass = new Ellipse2D.Double(x, y, 150,150);
        this.x = x;
        this.y = y;
    }

    public void setX(int x){
        this.setPos(x, this.y);
    }

    public void setY(int y){
        this.setPos(this.x, y);
    }
}
