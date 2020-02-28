package Sceneries.Clicker;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class pictureRotator {
    private ArrayList<BufferedImage> images;
    private BufferedImage currentImage;
    private Random rng;
    private File imageFolder;

    public pictureRotator(){
        this.images = new ArrayList<>();
        this.rng = new Random();
        this.imageFolder = new File("resource/clicker");

        for (File fileEntry : this.imageFolder.listFiles()){
            String filePath = fileEntry.toString().replaceAll("resource", "");
            try {
                this.images.add(ImageIO.read(fileEntry));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        this.rollImage();
    }

    public double getScale(){
        return 880.0 / (double)this.currentImage.getHeight();
    }

    public int getWidth(){ return this.currentImage.getWidth(); }

    public void rollImage(){
        this.currentImage = this.images.get(this.rng.nextInt(this.imageFolder.listFiles().length));
    }

    public BufferedImage getImage(){ return this.currentImage; }
}
