package Block_Sample;

import javafx.geometry.Point2D;
import org.jfree.fx.FXGraphics2D;

import java.awt.*;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;

public class Block {

    private Shape square;
    private Point2D place;
    private Color color;

    public Block(Point2D point2D, Color color, int width){
        this.square = new Rectangle2D.Double(point2D.getX(), point2D.getY(), width, width);
        this.color = color;
    }

    public Block(double x, double y, Color color, int width){
        this(new Point2D(x, y), color, width);
    }

    public void draw(FXGraphics2D g2d){
        g2d.setPaint(this.color);
        g2d.fill(this.square);
    }

    public Point2D getPlace(){
        return this.place;
    }

    public void setPlace(Point2D point2D){
        this.place = point2D;
    }

    public void setPlace(double x, double y){
        this.place = new Point2D(x, y);
    }

    public void moveRel(double x, double y){
        this.place = new Point2D(this.place.getX() + x, this.place.getY() + y);
    }

    public Color getColor(){
        return this.color;
    }

    public void setColor(Color color){
        this.color = color;
    }

    public static ArrayList<Block> randomBlocks(int screenWidth, int screenHeight, int amount){
        ArrayList<Block> blocks = new ArrayList<>(amount);
        Block block;
        Random rng = new Random();

        for (int i = 0; i < amount; ++i){
            block = new Block(rng.nextInt(screenWidth - 50), rng.nextInt(screenHeight-50), new Color(rng.nextFloat(), rng.nextFloat(), rng.nextFloat()), 50);
            blocks.add(block);
        }

        return blocks;
    }

    @Override
    public String toString(){
        return this.square.toString() + " with the color: " + this.color;
    }
}
