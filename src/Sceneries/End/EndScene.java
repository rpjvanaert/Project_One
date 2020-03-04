package Sceneries.End;

import Sceneries.Player;
import Sceneries.Scenery;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

import java.awt.*;

public class EndScene implements Scenery {
    private Scenery nextScene;
    private String title;
    private Scene scene;
    private Canvas canvas;
    private FXGraphics2D g2d;
    private String songPath = "resource/Music/FlowersOnTheGrave.mp3";

    private HeartAnimation heartAnimation;
    private FlowerCollection flowerCollection;

    public EndScene(Stage primaryStage, Player player){
        this.title = "Can I have a mug with your face on it?  x)";
        this.canvas = new Canvas(1920, 980);
        this.g2d = new FXGraphics2D(this.canvas.getGraphicsContext2D());

        this.flowerCollection = new FlowerCollection((int)this.canvas.getWidth(), (int)this.canvas.getHeight(), this.g2d);
        this.heartAnimation = new HeartAnimation((int)this.canvas.getWidth(), (int)this.canvas.getHeight(), this.g2d);

        this.draw();

        this.scene = new Scene(new Group(this.canvas));

        new AnimationTimer(){
            public void handle(long now){
                update();
                draw();
            }
        }.start();

    }

    public void update(){
        this.heartAnimation.update();
        this.flowerCollection.update();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void draw(){
        this.g2d.setBackground(Color.BLACK);
        this.g2d.clearRect(0,0, (int)this.canvas.getWidth(), (int)this.canvas.getHeight());
        this.flowerCollection.draw();
        this.heartAnimation.draw();
    }

    public void setNextScene(Scenery nextScene){ this.nextScene = nextScene; }

    public Scene getScene(){ return this.scene; }

    public String getTitle(){ return this.title; }

    public String getName(){ return this.title; }

    public String getSongPath(){ return this.songPath; }
}
