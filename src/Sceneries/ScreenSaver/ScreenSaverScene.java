package Sceneries.ScreenSaver;

import Sceneries.Player;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

import Sceneries.Scenery;

import java.awt.*;

public class ScreenSaverScene implements Scenery{
    private Scenery nextScene;
    private String songPath = "resource/Music/Soundtrack/Star Wars Main Theme (Full).mp3";

    private Scene scene;
    private HBox hBox;
    private VBox vBox;

    private Canvas canvas;
    private FXGraphics2D g2d;
    private int widthCanvas;
    private int heightCanvas;

    private Button buttonNext;

    private Button buttonShowSaver;

    private TextField tfSaverPoints;
    private TextField tfStepSize;
    private TextField tfFadeLines;
    private TextField tfFade;
    private TextField tfHueCycleSpeed;

    private LineCollection lineCollection;
    private int saverPoints;
    private int stepSize;
    private int fadeLines;
    private float fade;
    private int hueCycleSpeed;

    private EasterEggMessage egg;

    private Label errorText;
    private Stage popUpWindow;
    private Button buttonClose;

    public ScreenSaverScene(Stage primaryStage, Player player){

        this.saverPoints = 6;
        this.stepSize = 10;
        this.fadeLines = 30;
        this.fade = 0.3f;
        this.hueCycleSpeed = 3;

        this.widthCanvas = 1920;
        this.heightCanvas = 880;

        this.errorText = new Label(
                "The only non-number character\n" +
                "you can use is '-'. \n" +
                "When we made it official.\nOh you light up my sky!");
        this.errorText.setStyle("-fx-font: 30 Leelawadee; -fx-border-color: transparent; -fx-border-width: 7; -fx-text-fill: white;");

        this.buttonClose = new Button("You are OK");
        this.buttonClose.setStyle("-fx-font: 100 Leelawadee; -fx-base: orange");
        this.buttonClose.setOnAction(event -> {
            this.popUpWindow.close();
        });
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(this.errorText);
        borderPane.setBottom(this.buttonClose);
        this.popUpWindow = new Stage();
        this.popUpWindow.setHeight(480);
        borderPane.setStyle("-fx-background-color: #66ccff;");
        Scene scene = new Scene(borderPane);
        this.popUpWindow.setScene(scene);


        this.lineCollection = new LineCollection(widthCanvas, heightCanvas, saverPoints, stepSize, fadeLines, fade, hueCycleSpeed);

        this.canvas = new Canvas(this.widthCanvas, this.heightCanvas);
        this.g2d = new FXGraphics2D(canvas.getGraphicsContext2D());

        this.egg = new EasterEggMessage(widthCanvas, heightCanvas, g2d);

        this.draw();

        this.buttonNext = new Button("Next Scene");
        this.buttonNext.setOnAction(event -> {
            primaryStage.setScene(this.nextScene.getScene());
            primaryStage.setTitle(this.nextScene.getTitle());
            player.stop();
            player.setSong(this.nextScene.getSongPath());
        });


        this.buttonShowSaver = new Button("\t\tSubmit\t\t\t");
        this.buttonShowSaver.setOnAction(event -> {
            this.checkTextFields();
        });

        this.tfSaverPoints = new TextField("" + this.saverPoints);
        this.tfStepSize = new TextField("" + this.stepSize);
        this.tfFadeLines = new TextField("" + this.fadeLines);
        this.tfFade = new TextField("" + this.fade);
        this.tfHueCycleSpeed = new TextField("" + this.hueCycleSpeed);

        this.hBox = new HBox();
        this.hBox.getChildren().addAll(
                this.buttonShowSaver,
                getPresetLabel("\tAmount of points: "),             this.tfSaverPoints,
                getPresetLabel("\tStep size: "),                    this.tfStepSize,
                getPresetLabel("\tAmount of faded lines: "),        this.tfFadeLines,
                getPresetLabel("\tFade float: "),                   this.tfFade,
                getPresetLabel("\tCycle speed through colors: "),   this.tfHueCycleSpeed);

        this.vBox = new VBox();
        this.vBox.getChildren().addAll(this.buttonNext, this.hBox, this.canvas);
        this.scene = new Scene(this.vBox);

        new AnimationTimer(){
            public void handle(long now){
                update();
                draw();
            }
        }.start();
    }

    public void update(){
        this.lineCollection.takeStep();
    }

    private void draw(){
        this.g2d.setBackground(Color.black);
        this.g2d.clearRect(0,0,this.widthCanvas,this.heightCanvas);
        this.egg.draw();

        this.lineCollection.draw(this.g2d);
    }

    private Label getPresetLabel(String text){
        Label returnLabel = new Label(text);
        return returnLabel;
    }

    private boolean checkTextFields(){
        try{

            int saverPoints = Integer.parseInt(this.tfSaverPoints.getText());
            String stepString = this.tfStepSize.getText();
            int fadeLines = Integer.parseInt(this.tfFadeLines.getText());
            String fadeString = this.tfFade.getText();
            int hueCycleSpeed = Integer.parseInt(this.tfHueCycleSpeed.getText());

            if (saverPoints == 23 && stepString.equals("-") && fadeLines == 12 && fadeString.equals("-") && (hueCycleSpeed == 2019 || hueCycleSpeed == 19)){
                this.lineCollection = new LineCollection(widthCanvas, heightCanvas, 0, 0,0,0,0);
                this.egg.turnOn();
            } else {
                int stepSize = Integer.parseInt(stepString);
                float fade = Float.parseFloat(fadeString);
                this.lineCollection = new LineCollection(widthCanvas, heightCanvas, saverPoints, stepSize, fadeLines, fade, hueCycleSpeed);
                this.egg.turnOff();
            }

            this.draw();
        } catch (Exception e){
            e.printStackTrace();
            this.popUpWindow.showAndWait();
            //@TODO Add error pop up

        }

        return true;
    }

    public Scene getScene(){ return this.scene; }

    public void setNextScene(Scenery nextScene){
        this.nextScene = nextScene;
    }

    public String getTitle(){ return "Screen Saver Exercise"; }

    public String getName() { return "Screen Saver"; }

    public String getSongPath(){ return this.songPath; }
}
