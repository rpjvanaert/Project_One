package Sceneries.Clicker;

import Sceneries.Scenery;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ClickerScene implements Scenery {
    private Scene scene;
    private Scenery nextScene;

    private VBox vBox;

    private Button buttonNext;

    private Canvas canvas;
    private int canvasWidth;
    private int canvasHeight;

    private FXGraphics2D g2d;

    private ClickSquare clickSquare;

    private pictureRotator pictureRotator;
    private BufferedImage texture;
    private File textureFile;

    private int count;
    private int max;

    public ClickerScene(Stage primaryStage){
        this.canvasWidth = 1920;
        this.canvasHeight = 880;
        this.canvas = new Canvas(this.canvasWidth, this.canvasHeight);

        this.count = 0;
        this.max = 14;

        this.clickSquare = new ClickSquare(canvasWidth, canvasHeight);

        this.pictureRotator = new pictureRotator();

        this.textureFile = new File("resource/textures/broccoli.jpg");
        try {
            this.texture = ImageIO.read(this.textureFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.canvas.setOnMouseClicked(event -> {
            if (this.clickSquare.check((int)event.getX(), (int)event.getY())){
                this.pictureRotator.rollImage();
                if (++this.count == this.max){
                    primaryStage.setScene(this.nextScene.getScene());
                    primaryStage.setTitle(this.nextScene.getTitle());
                }
            }
            this.draw();
        });

        this.g2d = new FXGraphics2D(this.canvas.getGraphicsContext2D());

        this.draw();

        this.buttonNext = new Button("Next Scene");
        this.buttonNext.setOnAction(event -> {
            primaryStage.setScene(this.nextScene.getScene());
            primaryStage.setTitle(this.nextScene.getTitle());
        });

        this.vBox = new VBox();
        this.vBox.getChildren().add(this.buttonNext);
        this.vBox.getChildren().add(this.canvas);

        this.scene = new Scene(this.vBox);
    }

    public void draw(){
        this.g2d.setBackground(Color.BLACK);
        this.g2d.clearRect(0,0, this.canvasWidth, this.canvasHeight);

        g2d.setPaint(new TexturePaint(this.texture, new Rectangle2D.Double(0,0, this.texture.getWidth(), this.texture.getHeight())));
        g2d.fill(new Rectangle2D.Double(0,0, this.canvasWidth, this.canvasHeight));

        AffineTransform aT = new AffineTransform();
        aT.scale(this.pictureRotator.getScale(), this.pictureRotator.getScale());
        aT.translate(this.canvasWidth/2 - (this.pictureRotator.getWidth() * this.pictureRotator.getScale()/2), 0);
        this.g2d.drawImage(this.pictureRotator.getImage(), aT, null);

        this.clickSquare.draw(g2d);
    }

    public Scene getScene(){ return this.scene; }

    public void setNextScene(Scenery nextScene){ this.nextScene = nextScene; }

    public String getTitle(){ return "Clicker!"; }
}
