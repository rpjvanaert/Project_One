package Sceneries.ScreenSaver;

import org.jfree.fx.FXGraphics2D;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;

public class EasterEggMessage {
    private int width;
    private int height;
    private GeneralPath generalPath;
    private Font font;
    private Shape textShape1;
    private Shape textShape2;
    private FXGraphics2D g2d;

    private boolean on;

    public EasterEggMessage(int width, int height, FXGraphics2D g2d){
        this.width = width;
        this.height = height;
        this.generalPath = new GeneralPath();
        this.font = new Font("freestyle script", Font.PLAIN, 100);
        this.g2d = g2d;
        this.textShape1 = font.createGlyphVector(g2d.getFontRenderContext(), "I Love U!").getOutline();
        this.textShape2 = font.createGlyphVector(g2d.getFontRenderContext(), "1").getOutline();

        this.on = false;
        this.generalPath.moveTo(this.xFromMid(0), this.yFromMid(300));
        this.generalPath.lineTo(this.xFromMid(250), this.yFromMid(-100));
        this.generalPath.moveTo(this.xFromMid(0), this.yFromMid(300));
        this.generalPath.lineTo(this.xFromMid(-250), this.yFromMid(-100));
        this.generalPath.curveTo(
                this.xFromMid(-250), this.yFromMid(-250),
                this.xFromMid(0), this.yFromMid(-250),
                this.xFromMid(0), this.yFromMid(-100)
        );

        this.generalPath.curveTo(
                this.xFromMid(0), this.yFromMid(-250),
                this.xFromMid(250), this.yFromMid(-250),
                this.xFromMid(250), this.yFromMid(-100)
        );
        this.generalPath.moveTo(this.xFromMid(0), this.yFromMid(300));
        this.generalPath.closePath();
    }

    public void draw(){
        if (this.on){
            this.g2d.setBackground(Color.black);
            this.g2d.clearRect(0,0,this.width, this.height);

            this.g2d.setPaint(Color.RED);
            this.g2d.fill(this.generalPath);


            Shape tfShape1 = AffineTransform.getTranslateInstance(this.xFromMid(-125), this.yFromMid(0)).createTransformedShape(this.textShape1);
            this.g2d.setPaint(Color.BLACK);
            this.g2d.fill(tfShape1);
            this.g2d.setPaint(Color.WHITE);
            this.g2d.draw(tfShape1);

            Shape tfShape2 = AffineTransform.getTranslateInstance(this.xFromMid(-10), this.yFromMid(200)).createTransformedShape(this.textShape2);
            this.g2d.setPaint(Color.BLACK);
            this.g2d.fill(tfShape2);
            this.g2d.setPaint(Color.WHITE);
            this.g2d.draw(tfShape2);
        }
    }

    public int xFromMid(int x){ return this.width/2 + x; }

    public int yFromMid(int y){ return this.height/2 + y; }

    public void turnOn(){ this.on = true; }

    public void turnOff(){ this.on = false; }
}
