package Sceneries.Experimental;

import Sceneries.Player;
import Sceneries.Scenery;
import Sceneries.TicTacStubbedPinkyToe.PictureHandler;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class ExperimentalScene implements Scenery {
    private Scenery nextScene;
    private String title;
    private Scene scene;
    private int timesVisited;
    private String songPath = "resource/Music/Soundtrack/Katrina & The Waves - Walking On Sunshine (Official Video).mp3";

    private VBox vBox;
    private Button buttonNext;

    private Canvas canvas;
    private FXGraphics2D g2d;

    private ArrayList<BufferedImage> art;
    private ArrayList<Shape> shapes;
    private Random rng;
    private int indexArt;
    private int indexShape;
    private Point2D mousePos;


    public ExperimentalScene(Stage primaryStage, Player player){
        this.title = "EXPERIMENTAL";
        this.canvas = new Canvas(1920, 880);
        this.g2d = new FXGraphics2D(this.canvas.getGraphicsContext2D());
        this.mousePos = new Point2D.Double(canvas.getWidth()/2 - 25, canvas.getHeight()/2 - 25);
        this.init();
        this.draw();
        this.timesVisited = 1;


        canvas.setOnMouseClicked(event -> {
            this.indexShape = this.rng.nextInt(this.shapes.size());
            this.indexArt = this.rng.nextInt(this.art.size());
            this.draw();
            System.out.println(this.indexShape);
        });

        canvas.setOnMouseMoved(event -> {
            this.mousePos = new Point2D.Double(event.getX(), event.getY());
            this.draw();
        });

        this.buttonNext = new Button("Next Scene");
        this.buttonNext.setOnAction(event -> {
            ++this.timesVisited;
            if (this.timesVisited >= 3){
                this.title = "Three time's the charm, but honestly YOU are my charm <3 ! Number for code: 3.";
            }
            primaryStage.setScene(this.nextScene.getScene());
            primaryStage.setTitle(this.nextScene.getTitle());
            player.stop();
            player.setSong(this.nextScene.getSongPath());
        });

        this.vBox = new VBox();
        this.vBox.getChildren().addAll(this.buttonNext, this.canvas);

        this.scene = new Scene(this.vBox);
    }

    public void init(){
        this.art = new ArrayList<>();
        File folderArt = new File("resource/EXPERIMENTAL");
        for (File each : folderArt.listFiles()){
            try {
                this.art.add(ImageIO.read(each));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        this.shapes = new ArrayList<>();
        this.shapes.add(new Ellipse2D.Double(0, 0, 40,300));
        this.shapes.add(new Rectangle2D.Double(0, 0, 300, 10));
        Font font1 = new Font("Brush Script MT", Font.PLAIN, 60);
        Font font2 = new Font("Century Gothic", Font.PLAIN, 60);
        this.shapes.add(font1.createGlyphVector(this.g2d.getFontRenderContext(), "Mila").getOutline());
        this.shapes.add(font2.createGlyphVector(this.g2d.getFontRenderContext(), "Ralf").getOutline());
        this.shapes.add(font2.createGlyphVector(this.g2d.getFontRenderContext(), "Henk").getOutline());

        this.rng = new Random();
        this.indexArt = rng.nextInt(this.art.size());
        this.indexShape = rng.nextInt(this.shapes.size());
    }

    public void draw(){
        g2d.setBackground(Color.getHSBColor(getFloatRGB(148), getFloatRGB(201), getFloatRGB(205)));
        g2d.clearRect(0,0, 1920, 880);

        Shape formClip = this.shapes.get(this.indexShape);
        Shape clip = AffineTransform.getTranslateInstance(this.mousePos.getX() - this.getHalfWidth(this.indexShape), this.mousePos.getY() - this.getHalfHeight(this.indexShape)).createTransformedShape(formClip);
        this.g2d.setClip(clip);

        this.g2d.drawImage(this.art.get(this.indexArt), AffineTransform.getScaleInstance(this.getScaleArt(), this.getScaleArt()), null);
        this.g2d.setClip(null);
    }

    public float getFloatRGB(int f){
        return (float)f/256.f;
    }

    public double getScaleArt(){
        double scaleW = canvas.getWidth()/(double)this.art.get(this.indexArt).getWidth();
        double scaleH = canvas.getHeight()/(double)this.art.get(this.indexArt).getHeight();
        if (scaleH > scaleW){
            return scaleH;
        } else {
            return scaleW;
        }
    }

    public int getHalfHeight(int index){
        if (index == 0){
            return 150;
        } else if (index == 1){
            return 5;
        } else if(index == 2 || index == 3 || index == 4){
            return 0;
        }
        return 0;
    }

    public int getHalfWidth(int index){
        if (index == 0){
            return 20;
        } else if(index == 1){
            return 150;
        } else if(index == 2 || index == 3 || index == 4){
            return 70;
        }
        return 0;
    }

    public String getTitle(){ return this.title; }

    public void setNextScene(Scenery nextScene){ this.nextScene = nextScene; }

    public Scene getScene(){ return this.scene; }

    public String getName(){ return "Animation"; }

    public String getSongPath(){ return this.songPath; }
}
