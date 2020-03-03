package Sceneries.End;

import org.jfree.fx.FXGraphics2D;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class FlowerCollection {
    private ArrayList<BufferedImage> flowers;
    private int index;
    private int count;
    private FXGraphics2D g2d;
    private int width;
    private int height;

    public FlowerCollection(int width, int height, FXGraphics2D g2d){
        this.flowers = new ArrayList<>();
        this.index = 0;
        this.width = width;
        this.height = height;
        this.g2d = g2d;
        this.count = 0;
        File imageFolder = new File("resource/Flowers");
        for (File entry : imageFolder.listFiles()){
            if (entry.toString().contains(".png") || entry.toString().contains(".jpg") || entry.toString().contains(".jfif"))

            try {
                this.flowers.add(ImageIO.read(entry));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void draw(){
        BufferedImage drawing = this.flowers.get(this.index);
        int scaling = this.width / drawing.getWidth();
        this.g2d.drawImage(drawing, AffineTransform.getScaleInstance(scaling, scaling), null);

    }

    public void update(){
        ++count;
        if (count >= 300){
            this.index++;
            if (this.index >= this.flowers.size()){
                this.index = 0;
            }
            count = 0;
        }
    }
}
