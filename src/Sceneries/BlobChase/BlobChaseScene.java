package Sceneries.BlobChase;

import Sceneries.Player;
import Sceneries.Scenery;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
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

public class BlobChaseScene implements Scenery {

    private javafx.scene.canvas.Canvas canvas;
    private FXGraphics2D g2d;
    private Color color;
    private int countCollided;
    private int countRunning;
    private boolean collided;
    private Scene scene;
    private Scenery nextScene;
    private String songPath = "resource/Music/BlindingLights.mp3";
    private boolean running = false;
    private Shape textShape;

    public BlobChaseScene(Stage stage, Player player) throws Exception {
        VBox mainPane = new VBox();
        canvas = new Canvas(1920, 880);
        this.g2d = new FXGraphics2D(canvas.getGraphicsContext2D());
        this.color = Color.WHITE;
        this.countCollided = 0;
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
//            if (running){
//                for(Blob blob : blobs) {
//                    blob.setTarget(new Point2D.Double(e.getX(), e.getY()));
//                }
//            }
            Point2D manP2D = this.blobs.get(this.blobs.size() - 1).getPosition();
            for (int i = 0; i < blobs.size() - 1; ++i){
                blobs.get(i).setTarget(manP2D);
            }
            this.blobs.get(this.blobs.size() - 1).setTarget(new Point2D.Double(e.getX(), e.getY()));
        });

        HBox hBox = new HBox();

        Button buttonNext = new Button("Next Scene");
        buttonNext.setOnAction(event -> {
            stage.setScene(this.nextScene.getScene());
            stage.setTitle(this.nextScene.getTitle());
            player.stop();
            player.setSong(this.nextScene.getSongPath());
            this.blobs = null;
            this.running = false;
        });

        Button buttonStart = new Button("Start new Game");
        buttonStart.setOnAction(event -> {
            this.init();
            this.running = true;
        });

        Button buttonPause = new Button("Resume/Pause Game");
        buttonPause.setOnAction(event -> {
            this.running = !this.running;
            if (this.running){
                player.play();
            } else {
                player.pause();
            }
        });

                hBox.getChildren().addAll(buttonNext, buttonStart, buttonPause);

        mainPane.getChildren().addAll(hBox, this.canvas);
        this.scene = new Scene(mainPane, 1920, 980);

        Font font = new Font("Arial Rounded MT", Font.PLAIN, 200);
        this.textShape = font.createGlyphVector(this.g2d.getFontRenderContext(), "7").getOutline();
    }


    ArrayList<Blob> blobs;


    public void init() {
        this.blobs = new ArrayList<>();
        this.countCollided = 0;
        this.countRunning = 0;

        BufferedImage food = null;
        BufferedImage blobMan = null;
        try {
            food = ImageIO.read(new File("resource/ReversePacMan/food.png"));
            blobMan = ImageIO.read(new File("resource/ReversePacMan/blobMan.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int x = 0; x < 1920; x += 65 * 3){
            for (int y = 0; y > -200; y -= 65 * 2){
                this.blobs.add(new Food(new Point2D.Double(x, y), food));
            }
        }
        this.blobs.add(new BlobMan(new Point2D.Double(860, 540), blobMan));
    }

    public void reset(FXGraphics2D g2){
        g2.setTransform(new AffineTransform());
        g2.setBackground(this.color);
        g2.clearRect(0,0,(int)canvas.getWidth(), (int)canvas.getHeight());
    }


    public void draw(FXGraphics2D g2)
    {
        if (running){
            this.reset(g2);
            for(Blob blob : blobs) {
                blob.draw(this.g2d);
            }
        }
    }

    public void update(double frameTime) {
        if (running){
            if (this.collided) {
                this.countCollided++;
            }
            for(Blob blob : blobs) {
                if (blob.update(blobs)){
                    this.collided = true;
                    this.color = Color.BLACK;
                    this.gameOver();
                    this.running = false;
                } else {
                    if (this.countCollided > 100){
                        this.countCollided = 0;
                        this.color = Color.WHITE;
                    }
                }
            }

            ++this.countRunning;
            switch (this.countRunning){
                case 200:
                case 1000:
                case 2000:
                    this.setBlobsSpeed(0);
                    break;
                case 250:
                    this.setBlobsSpeed(4);
                    break;
                case 600:
                case 1300:
                    this.setBlobsSpeed(5);
                    break;
                case 1100:
                    this.setBlobsSpeed(14);
                    break;
                case 1150:
                    this.setBlobsSpeed(1);
                    break;
                case 2300:
                    this.setBlobsSpeed(27);
                    break;
                case 2335:
                    this.setBlobsSpeed(3);
                    break;
                case 2500:
                    this.running = false;
                    this.drawEnd();
            }
        }

    }

    public void setBlobsSpeed(int speed){
        for (Blob blob : this.blobs){
            blob.setSpeed(speed);
        }
    }

    public void drawEnd(){
        this.reset(this.g2d);
        BufferedImage weeknd = null;
        try {
            weeknd = ImageIO.read(new File("resource/TheWeeknd.jpeg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        double scale = this.canvas.getWidth()/(double)weeknd.getWidth();
        this.g2d.drawImage(weeknd, AffineTransform.getScaleInstance(scale, scale), null);

        Shape tfShape = AffineTransform.getTranslateInstance(this.canvas.getWidth()/2, this.canvas.getHeight()/2).createTransformedShape(this.textShape);
        this.g2d.setPaint(Color.YELLOW);
        this.g2d.fill(tfShape);
        this.g2d.setPaint(Color.BLACK);
        this.g2d.draw(tfShape);
    }

    public void gameOver(){
        this.init();
        this.color = Color.RED;
        this.reset(this.g2d);
        Font font = new Font("Arial Rounded MT", Font.PLAIN, 200);
        Shape textShape1 = font.createGlyphVector(this.g2d.getFontRenderContext(), "Game Over!").getOutline();
        Shape tfShape1 = AffineTransform.getTranslateInstance(this.canvas.getWidth()/2 - 500, this.canvas.getHeight()/2).createTransformedShape(textShape1);
        this.g2d.setPaint(Color.WHITE);
        this.g2d.fill(tfShape1);
        this.g2d.setPaint(Color.BLACK);
        this.g2d.draw(tfShape1);
        this.color = Color.WHITE;
    }

    public Scene getScene(){ return this.scene; }

    public void setNextScene(Scenery nextScene){
        this.nextScene = nextScene;
    }

    public String getTitle(){ return "Running man"; }

    public String getName() { return "Chasing Blobs"; }

    public String getSongPath(){ return this.songPath; }
}