package Sceneries.Clicker;

import org.jfree.fx.FXGraphics2D;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Random;

public class ClickSquare {
    private Color colorOut;
    private Color colorIn;
    private Shape square;

    private int squareWidth;
    private int x;
    private int y;

    private Random rng;

    private int canvasWidth;
    private int canvasHeight;

    public ClickSquare(int canvasWidth, int canvasHeight){
            this.canvasWidth = canvasWidth;
            this.canvasHeight = canvasHeight;

            this.rng = new Random();

            this.squareWidth = 30;

            this.randomize();

            this.colorOut = Color.RED;
            this.colorIn = Color.WHITE;
            this.square = new Rectangle2D.Double(this.x, this.y, this.squareWidth, this.squareWidth);
    }

    private void randomize(){
        this.x = this.rng.nextInt(this.canvasWidth - this.squareWidth);
        this.y = this.rng.nextInt(this.canvasHeight - this.squareWidth);
    }

    private boolean withinX(int xC){
        if (xC > this.x && xC < this.x + this.squareWidth){
            return true;
        } else {
            return false;
        }
    }

    public boolean withinY(int yC){
        if (yC > this.y && yC < this.y + this.squareWidth){
            return true;
        } else {
            return false;
        }
    }

    public boolean check(int mouseX, int mouseY){
        if (this.withinX(mouseX) && this.withinY(mouseY)){
            this.randomize();
            this.square = new Rectangle2D.Double(this.x, this.y, this.squareWidth, this.squareWidth);
            return true;
        } else {
            return false;
        }
    }

    public void draw(FXGraphics2D g2d){
        g2d.setPaint(Color.RED);
        g2d.fill(this.square);
        g2d.setPaint(Color.WHITE);
        g2d.draw(this.square);
    }
}
