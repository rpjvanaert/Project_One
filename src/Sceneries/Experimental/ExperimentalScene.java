package Sceneries.Experimental;

import Sceneries.Scenery;
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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ExperimentalScene implements Scenery {
    private Scenery nextScene;
    private String title;
    private Scene scene;
    private int timesVisited;

    private VBox vBox;
    private Button buttonNext;

    private Canvas canvas;
    private FXGraphics2D g2d;

    private BufferedImage[] walkCycle;
    private int indexAnimation;
    private int fps;
    private float animationSpeed;
    private long beginTime;

    public ExperimentalScene(Stage primaryStage){
        this.title = "EXPERIMENTAL";
        this.init();
        this.canvas = new Canvas(1920, 880);
        this.g2d = new FXGraphics2D(this.canvas.getGraphicsContext2D());
        this.draw();
        this.timesVisited = 1;

        this.fps = 30;
        this.animationSpeed = 1.0f/10.0f;
        this.beginTime = System.currentTimeMillis();

        new AnimationTimer() {
            long last = -1;
            @Override
            public void handle(long now) {
                if(last == -1)
                    last = now;
                update((now - last) / 1000000000.0);
                last = now;
                draw();
            }
        }.start();

        this.buttonNext = new Button("Next Scene");
        this.buttonNext.setOnAction(event -> {
            ++this.timesVisited;
            if (this.timesVisited >= 3){
                this.title = "Three time's the charm, but honestly YOU are my charm <3 ! Number for code: 3.";
            }
            primaryStage.setScene(this.nextScene.getScene());
            primaryStage.setTitle(this.nextScene.getTitle());
        });

        this.vBox = new VBox();
        this.vBox.getChildren().addAll(this.buttonNext, this.canvas);

        this.scene = new Scene(this.vBox);
    }

    public void init(){
        try {
            BufferedImage image = ImageIO.read(new File("resource/sprite.png"));
            this.walkCycle = new BufferedImage[65];
            for (int y = 0; y < 8; ++y){
                for (int x = 0; x < 8; ++x){
                    this.walkCycle[(y*8 + x)] = image.getSubimage(x * 64, y * 64, 64, 64);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(double deltaTime){
        long timeElapsed = System.currentTimeMillis() - this.beginTime;
        if (timeElapsed >= 1.0f /this.animationSpeed * 10.0f){
            this.beginTime = System.currentTimeMillis();
            ++this.indexAnimation;
        }

        double frameTime = 1 / this.fps;
        if (frameTime > deltaTime){
            try {
                Thread.sleep((long) (frameTime - deltaTime));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void draw(){
        g2d.setBackground(Color.BLUE);
        g2d.clearRect(0,0, 1920, 880);

        g2d.drawImage(this.walkCycle[this.indexAnimation % 64], AffineTransform.getTranslateInstance(canvas.getWidth()/2, canvas.getHeight()/2), null);
    }

    public String getTitle(){ return this.title; }

    public void setNextScene(Scenery nextScene){ this.nextScene = nextScene; }

    public Scene getScene(){ return this.scene; }

    public String getName(){ return "Animation"; }
}
