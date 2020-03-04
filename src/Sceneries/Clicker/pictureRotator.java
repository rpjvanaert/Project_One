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

    private ArrayList<BufferedImage> textures;
    private BufferedImage texture;
    private File textureFolder;

    public pictureRotator(){
        this.images = new ArrayList<>();
        this.textures = new ArrayList<>();
        this.rng = new Random();


        this.imageFolder = new File("resource/clicker");
        for (File fileEntry : this.imageFolder.listFiles()){
            try {
                this.images.add(ImageIO.read(fileEntry));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        this.textureFolder = new File("resource/textures");
        for (File fileEntry : this.textureFolder.listFiles()){
            try {
                this.textures.add(ImageIO.read(fileEntry));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        this.roll();
    }

    public double getScale(){
        return 880.0 / (double)this.currentImage.getHeight();
    }

    public int getWidth(){ return this.currentImage.getWidth(); }

    public void roll(){
        this.currentImage = this.images.get(this.rng.nextInt(this.images.size()));
        this.texture = this.textures.get(this.rng.nextInt(this.textures.size()));
    }

    public BufferedImage getImage(){ return this.currentImage; }

    public BufferedImage getTexture(){ return this.texture; }

    public int getTextureWidth(){ return this.texture.getWidth(); }

    public int getTextureHeight(){ return this.texture.getHeight(); }
}
