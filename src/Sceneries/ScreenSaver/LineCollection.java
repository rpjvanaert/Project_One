package Sceneries.ScreenSaver;

import org.jfree.fx.FXGraphics2D;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class LineCollection {
    private ArrayList<LinePoint> linePoints;

    private Color mainColor;
    private Color fadedColor;
    private float hue;
    private float fade;
    private int hueCycleSpeed;

    public LineCollection(int width, int height, int amountOfPoints, int maxStepSize, int countFadeLines, float fade, int speedCyclingHue){
        Random rng = new Random();

        this.linePoints = new ArrayList<>(amountOfPoints);
        for (int i = 0; i < amountOfPoints; ++i){
            this.linePoints.add(new LinePoint(width, height, rng.nextInt( 1920), rng.nextInt(1080), rng.nextBoolean(), rng.nextBoolean(), maxStepSize, countFadeLines));
        }

        this.hue = 0.0f;
        this.fade = fade;
        this.hueCycleSpeed = speedCyclingHue;
        this.mainColor = Color.getHSBColor(this.hue, 1.0f, 1.0f);
        this.fadedColor = Color.getHSBColor(this.hue, this.fade, this.fade);
    }

    public void takeStep(){
        for(LinePoint each : this.linePoints){
            if (each.takeStep()){
                this.hue += 0.01f * (float)this.hueCycleSpeed;
                this.fadedColor = Color.getHSBColor(this.hue, this.fade, this.fade);
                this.mainColor = Color.getHSBColor(this.hue, 1.0f, 1.0f);
            }
        }
    }

    public void draw(FXGraphics2D g2d){
        LinePoint last;

        for (LinePoint each : this.linePoints){
            if (this.linePoints.indexOf(each) == 0){
                last = this.linePoints.get(this.linePoints.size() - 1);
            } else {
                last = this.linePoints.get(this.linePoints.indexOf(each) - 1);
            }

            g2d.setPaint(this.fadedColor);
            for(int i = 0; i < each.getHistoryX().size(); ++i){
                g2d.drawLine(each.getHistoryX().get(i), each.getHistoryY().get(i), last.getHistoryX().get(i), last.getHistoryY().get(i));
            }
        }

        g2d.setPaint(this.mainColor);
        for (LinePoint each : this.linePoints){
            if (this.linePoints.indexOf(each) == 0){
                last = this.linePoints.get(this.linePoints.size() - 1);
            } else {
                last = this.linePoints.get(this.linePoints.indexOf(each) - 1);
            }

            g2d.drawLine(each.getX(), each.getY(), last.getX(), last.getY());
        }

    }
}
