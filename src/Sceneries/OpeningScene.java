package Sceneries;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.awt.*;

public class OpeningScene implements Scenery{

    private Scene scene;
    private Scenery nextScene;
    private String title;

    private BorderPane borderPane;
    private TextFlow textFlow;
    private Text welcomeTitle;
    private Text explanation;

    public OpeningScene(Stage primaryStage){
        this.title = "Bit cheesy, but hey! It's true!";

        this.borderPane = new BorderPane();

        this.textFlow = new TextFlow();
        this.welcomeTitle = new Text("Hello there!");
        this.welcomeTitle.setStyle("-fx-font: 100 Leelawadee;");
        this.welcomeTitle.setFill(Color.BLUE);

        this.textFlow.getChildren().addAll(this.welcomeTitle);

        this.borderPane.setCenter(this.textFlow);


        this.scene = new Scene(this.borderPane);
    }

    public Scene getScene(){ return this.scene; }

    public String getTitle(){ return this.title; }

    public void setNextScene(Scenery nextScene){
        this.nextScene = nextScene;
    }
}
