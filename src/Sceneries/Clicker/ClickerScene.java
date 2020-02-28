package Sceneries.Clicker;

import Sceneries.Scenery;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

import java.awt.*;

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

    private Image background;
    private pictureRotator pictureRotator;

    public ClickerScene(Stage primaryStage){
        this.canvasWidth = 1920;
        this.canvasHeight = 880;
        this.canvas = new Canvas(this.canvasWidth, this.canvasHeight);

        this.clickSquare = new ClickSquare(canvasWidth, canvasHeight);

        this.pictureRotator = new pictureRotator();
        this.background = this.pictureRotator.getImage();

        this.canvas.setOnMouseClicked(event -> {
            if (this.clickSquare.check((int)event.getX(), (int)event.getY())){

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
        this.vBox.getChildren().addAll(this.buttonNext, this.canvas);

        this.scene = new Scene(this.vBox);
    }

    public void draw(){
        this.g2d.setBackground(Color.BLACK);
        this.g2d.clearRect(0,0, this.canvasWidth, this.canvasHeight);
        this.g2d.drawImage(this.background, null, null);
        this.clickSquare.draw(g2d);
    }

    public Scene getScene(){ return this.scene; }

    public void setNextScene(Scenery nextScene){ this.nextScene = nextScene; }

    public String getTitle(){ return "Clicker!"; }
}
