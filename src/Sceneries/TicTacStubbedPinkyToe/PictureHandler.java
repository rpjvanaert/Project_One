package Sceneries.TicTacStubbedPinkyToe;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import java.awt.image.BufferedImage;
import java.util.Random;

public class PictureHandler {
    private ArrayList<BufferedImage> mila;
    private ArrayList<BufferedImage> ralf;
    private ArrayList<BufferedImage> both;
    private Random rng;

    public PictureHandler(){
        this.mila = new ArrayList<>();
        this.ralf = new ArrayList<>();
        this.both = new ArrayList<>();
        this.rng = new Random();
        File dirMila = new File("resource/StubbedPinkyToe/Mila");
        File dirRalf = new File("resource/StubbedPinkyToe/Ralf");
        File dirBoth = new File("resource/StubbedPinkyToe/Both");

        for (File each : dirMila.listFiles()){
            if (each.toString().contains(".jpg")
                    || each.toString().contains(".png")
                    || each.toString().contains(".jfif")
                    || each.toString().contains(".jpeg")){
                try {
                    this.mila.add(ImageIO.read(each));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        for (File each : dirRalf.listFiles()){
            if (each.toString().contains(".jpg")
                    || each.toString().contains(".png")
                    || each.toString().contains(".jfif")
                    || each.toString().contains(".jpeg")){
                try {
                    this.ralf.add(ImageIO.read(each));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        for (File each : dirBoth.listFiles()){
            if (each.toString().contains(".jpg")
                    || each.toString().contains(".png")
                    || each.toString().contains(".jfif")
                    || each.toString().contains(".jpeg")){
                try {
                    this.both.add(ImageIO.read(each));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        this.both.addAll(this.ralf);
        this.both.addAll(this.mila);
    }

    public int getMilaInt(){
        return this.rng.nextInt(this.mila.size());
    }

    public int getRalfInt(){
        return this.rng.nextInt(this.ralf.size());
    }

    public int getInt(){
        ArrayList<BufferedImage> all = new ArrayList<>();
        all.addAll(this.ralf);
        all.addAll(this.mila);
        return this.rng.nextInt(all.size());
    }

    public BufferedImage getMilaIMG(int index){
        return this.mila.get(index);
    }

    public BufferedImage getRalfIMG(int index){
        return this.ralf.get(index);
    }

    public BufferedImage getIMG(int index){
        ArrayList<BufferedImage> all = new ArrayList<>();
        all.addAll(this.ralf);
        all.addAll(this.mila);
        return all.get(index);
    }
}
