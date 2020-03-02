package Sceneries.End;

import com.sun.deploy.panel.JSmartTextArea;
import org.jfree.fx.FXGraphics2D;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class HeartAnimation {
    private int canvasWidth;
    private int canvasHeight;
    private GeneralPath generalPath;
    private FXGraphics2D g2d;


    private int indexAnimation;
    private ArrayList<Shape> hearts;
    private int amountHearts;
    private int timer;

    public HeartAnimation(int canvasWidth, int canvasHeight, FXGraphics2D g2d){
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
        this.generalPath = new GeneralPath();
        this.g2d = g2d;

        this.indexAnimation = 0;
        this.generalPath.moveTo(this.xFromMid(0), this.yFromMid(450));
        this.generalPath.lineTo(this.xFromMid(400), this.yFromMid(-170));
        this.generalPath.moveTo(this.xFromMid(0), this.yFromMid(450));
        this.generalPath.lineTo(this.xFromMid(-400), this.yFromMid(-170));
        this.generalPath.curveTo(
                this.xFromMid(-600), this.yFromMid(-500),
                this.xFromMid(0), this.yFromMid(-500),
                this.xFromMid(0), this.yFromMid(-240)
        );

        this.generalPath.curveTo(
                this.xFromMid(0), this.yFromMid(-500),
                this.xFromMid(600), this.yFromMid(-500),
                this.xFromMid(400), this.yFromMid(-170)
        );
        this.generalPath.moveTo(this.xFromMid(0), this.yFromMid(450));
        this.generalPath.closePath();

        this.indexAnimation = 1;
        this.timer = 0;
        this.amountHearts = 20;
        this.hearts = new ArrayList<>(this.amountHearts);
        Shape smallingPath = this.generalPath;
        this.hearts.add(smallingPath);
        for (int i = 0; i < this.amountHearts; ++i){
            smallingPath = this.getTransform().createTransformedShape(smallingPath);
            this.hearts.add(smallingPath);
        }
    }

    public void draw(){
        for (int i = this.indexAnimation; i >= 1; i--){
            if (i % 2 == 0){
                this.g2d.setPaint(Color.RED);
            } else {
                this.g2d.setPaint(Color.PINK);
            }
            this.g2d.fill(this.hearts.get(this.amountHearts - i));
        }
    }

    public void update(){
        ++this.timer;

        if (this.timer == 15){
            ++this.indexAnimation;
            if (this.indexAnimation >= this.amountHearts){
                this.indexAnimation = 1;
            }
            this.timer = 0;
        }

    }



    private int xFromMid(int x){ return this.canvasWidth/2 + x; }

    private int yFromMid(int y){ return this.canvasHeight/2 + y; }

    private AffineTransform getTransform(){
        AffineTransform tx = new AffineTransform();
//        Point2D point2D = new Point2D.Double(this.canvasWidth/2, this.canvasHeight/2);
        tx.translate(-this.canvasWidth/2, -(this.canvasHeight/2 -240));
        tx.scale(0.8, 0.8);
//        AffineTransform ta = AffineTransform.getScaleInstance(0.8, 0.8);
//        tx.translate(this.canvasWidth/2.5, this.canvasHeight/2.5);
        tx.translate( this.canvasWidth/2 * 1.5, (this.canvasHeight/2 - 240) * 1.6);
        return tx;
    }
}
