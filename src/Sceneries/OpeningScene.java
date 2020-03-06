package Sceneries;
import javafx.scene.Scene;
import javafx.scene.effect.Glow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.effect.Shadow;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.nio.file.Paths;

public class OpeningScene implements Scenery{

    private Scene scene;
    private Scenery nextScene;
    private String title;
    private String songPath = "resource/Music/Soundtrack/Panic! At The Disco - Into the Unknown (From Frozen 2).mp3";

    private BorderPane borderPane;
    private TextFlow textFlow;
    private Text welcomeTitle;
    private Text explanation;
    private Text smallLetters;

    private ImageView imageViewSolo;

    private Button buttonReady;

    public OpeningScene(Stage primaryStage, Player player){
        this.title = "Bit cheesy, but hey! It's true!";
        player.setSong(this.songPath);
        this.borderPane = new BorderPane();

        this.textFlow = new TextFlow();
        this.welcomeTitle = new Text("Hello there!\n");
        this.welcomeTitle.setStyle("-fx-font: 200 Leelawadee;");
        this.welcomeTitle.setFill(Color.CADETBLUE);
        this.welcomeTitle.setEffect(new Glow());

        this.explanation = new Text("You will have a 5 number long code,\nthat will complete this game, enjoy everything in this game!\n");
        this.explanation.setStyle("-fx-font: 45 Leelawadee;");
        this.explanation.setFill(Color.WHITE);
        this.explanation.setEffect(new Shadow());

        this.smallLetters = new Text("\tALSO: LOOK AT THE WINDOW TITLE FOR TIPS, EVERYTIME");
        this.smallLetters.setStyle("-fx-font: 15 Arial; -fx-base: #73a274;");
        this.smallLetters.setFill(Color.CADETBLUE);

        this.textFlow.getChildren().addAll(this.welcomeTitle, this.explanation, this.smallLetters);

        this.imageViewSolo = new ImageView(new Image("han_solo.jpeg"));
        this.imageViewSolo.setFitWidth(640);
        this.imageViewSolo.setFitHeight(380);

        this.buttonReady = new Button("Ready? Player One!");
        this.buttonReady.setStyle("-fx-font: 100 Leelawadee; -fx-border-color: transparent; -fx-border-width: 30; -fx-base: #ffffff;");
        this.buttonReady.setOnAction(event -> {
            primaryStage.setScene(this.nextScene.getScene());
            primaryStage.setTitle(this.nextScene.getTitle());
            player.stop();
            player.setSong(this.nextScene.getSongPath());
        });

        this.borderPane.setLeft(this.textFlow);
        this.borderPane.setRight(this.imageViewSolo);
        this.borderPane.setBottom(this.buttonReady);
        this.borderPane.setBackground(new Background(new BackgroundFill(new Color(1, 1, 1, 1), null, null)));


        this.scene = new Scene(this.borderPane);
    }

    public Scene getScene(){ return this.scene; }

    public String getTitle(){ return this.title; }

    public void setNextScene(Scenery nextScene){
        this.nextScene = nextScene;
    }

    public String getName(){ return "Welcome"; }

    public String getSongPath(){ return this.songPath; }
}
