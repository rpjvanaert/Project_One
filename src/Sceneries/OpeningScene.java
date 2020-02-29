package Sceneries;

import javafx.scene.Scene;
import javafx.scene.effect.Glow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class OpeningScene implements Scenery{

    private Scene scene;
    private Scenery nextScene;
    private String title;

    private BorderPane borderPane;
    private TextFlow textFlow;
    private Text welcomeTitle;
    private Text explanation;
    private Text smallLetters;

    private ImageView imageViewSolo;

    private Button buttonReady;

    public OpeningScene(Stage primaryStage){
        this.title = "Bit cheesy, but hey! It's true!";

        this.borderPane = new BorderPane();

        this.textFlow = new TextFlow();
        this.welcomeTitle = new Text("Hello there!\n");
        this.welcomeTitle.setStyle("-fx-font: 200 Leelawadee;");
        this.welcomeTitle.setFill(Color.WHITE);
        this.welcomeTitle.setEffect(new InnerShadow());

        this.explanation = new Text("You will have N long code of numbers,\nthat will complete this game, enjoy everything in this game!\n");
        this.explanation.setStyle("-fx-font: 45 Leelawadee;");
        this.explanation.setFill(Color.BLUE);
        this.explanation.setEffect(new Glow());

        this.smallLetters = new Text("\tALSO: LOOK AT THE WINDOW TITLE FOR TIPS");
        this.smallLetters.setStyle("-fx-font: 15 Arial; -fx-base: #73a274;");
        this.smallLetters.setFill(Color.DARKOLIVEGREEN);

        this.textFlow.getChildren().addAll(this.welcomeTitle, this.explanation, this.smallLetters);

        this.imageViewSolo = new ImageView(new Image("han_solo.jpeg"));
        this.imageViewSolo.setFitWidth(640);
        this.imageViewSolo.setFitHeight(380);

        this.buttonReady = new Button("Ready? Player One!");
        this.buttonReady.setStyle("-fx-font: 100 Leelawadee; -fx-border-color: transparent; -fx-border-width: 30; -fx-base: #2d46bf;");
        this.buttonReady.setOnAction(event -> {
            primaryStage.setScene(this.nextScene.getScene());
            primaryStage.setTitle(this.nextScene.getTitle());
        });

        this.borderPane.setLeft(this.textFlow);
        this.borderPane.setRight(this.imageViewSolo);
        this.borderPane.setBottom(this.buttonReady);
        this.borderPane.setBackground(new Background(new BackgroundFill(new Color(0.7, 0.4, 0.2, 1), null, null)));


        this.scene = new Scene(this.borderPane);
    }

    public Scene getScene(){ return this.scene; }

    public String getTitle(){ return this.title; }

    public void setNextScene(Scenery nextScene){
        this.nextScene = nextScene;
    }

    public String getName(){ return "Welcome"; }
}
