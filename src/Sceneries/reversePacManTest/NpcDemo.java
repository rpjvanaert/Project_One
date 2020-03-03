package Sceneries.reversePacManTest;

import Sceneries.Player;
import Sceneries.Scenery;
import com.sun.org.apache.xerces.internal.impl.xs.SchemaNamespaceSupport;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class NpcDemo implements Scenery {

    private javafx.scene.canvas.Canvas canvas;
    private FXGraphics2D g2d;
    private Color color;
    private int count;
    private boolean collided;
    private Scene scene;
    private Scenery nextScene;
    private String songPath = "resource/Music/BlindingLights.mp3";
    private boolean running = false;

    public NpcDemo(Stage stage, Player player) throws Exception {
        VBox mainPane = new VBox();
        canvas = new Canvas(1920, 880);
        this.g2d = new FXGraphics2D(canvas.getGraphicsContext2D());
        this.color = Color.WHITE;
        this.count = 0;
        this.collided = false;
        new AnimationTimer() {
            long last = -1;
            @Override
            public void handle(long now) {
                if(last == -1)
                    last = now;
                update((now - last) / 1000000000.0);
                last = now;
                draw(g2d);
            }
        }.start();

        draw(g2d);

        canvas.setOnMouseMoved(e ->
        {
            if (running){
                for(Blob blob : blobs) {
                    blob.setTarget(new Point2D.Double(e.getX(), e.getY()));
                }
            }
        });

        HBox hBox = new HBox();

        Button buttonNext = new Button("Next Scene");
        buttonNext.setStyle("-fx-font: 40 Verdana;");
        buttonNext.setOnAction(event -> {
            stage.setScene(this.nextScene.getScene());
            stage.setTitle(this.nextScene.getTitle());
            player.stop();
            player.setSong(this.nextScene.getSongPath());
            this.blobs = null;
            this.running = false;
        });

        Button buttonStart = new Button("Start Game");
        buttonStart.setStyle("-fx-font: 40 Verdana;");
        buttonStart.setOnAction(event -> {
            this.init();
            this.running = true;
        });

        hBox.getChildren().addAll(buttonNext, buttonStart);

        mainPane.getChildren().addAll(hBox, this.canvas);
        this.scene = new Scene(mainPane, 1920, 980);
    }


    ArrayList<Blob> blobs;


    public void init() {
        this.blobs = new ArrayList<>();

        BufferedImage food = null;
        BufferedImage blobMan = null;
        try {
            food = ImageIO.read(new File("resource/ReversePacMan/food.png"));
            blobMan = ImageIO.read(new File("resource/ReversePacMan/blobMan.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

//        Random rng = new Random();
//        for(int i = 0; i < 50; i++) {
//            this.blobs.add(new Food(new Point2D.Double((rng.nextInt(1920/65) * 65), (rng.nextInt(1080/65) * 65)), image));
//        }

        for (int x = 0; x < 1920; x += 65 * 3){
            for (int y = 0; y > -200; y -= 65 * 2){
                this.blobs.add(new Food(new Point2D.Double(x, y), food));
            }
        }
        this.blobs.add(new BlobMan(new Point2D.Double(860, 540), blobMan));
    }


    public void draw(FXGraphics2D g2)
    {
        g2.setTransform(new AffineTransform());
        g2.setBackground(this.color);
        g2.clearRect(0,0,(int)canvas.getWidth(), (int)canvas.getHeight());


        if (running){
            for(Blob blob : blobs) {
                blob.draw(this.g2d);
            }
        }




    }

    public void update(double frameTime) {
        if (running){
            if (this.collided) {
                this.count++;
            }
            for(Blob blob : blobs) {
                if (blob.update(blobs)){
                    this.collided = true;
                    this.color = Color.BLACK;
                } else {
                    if (this.count > 100){
                        this.count = 0;
                        this.color = Color.WHITE;
                    }
                }
            }
        }
    }

    public Scene getScene(){ return this.scene; }

    public void setNextScene(Scenery nextScene){
        this.nextScene = nextScene;
    }

    public String getTitle(){ return "Running man"; }

    public String getName() { return "Chasing Blobs"; }

    public String getSongPath(){ return this.songPath; }
}