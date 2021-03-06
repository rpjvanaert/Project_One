package Sceneries;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.util.ArrayList;

public class IndexScene implements Scenery {
    private Stage primaryStage;
    private Scenery nextScene;
    private String title;
    private String name;
    private Scene scene;
    private String songPath = "resource/Music/I Feel It All Over.mp3";
    private Player player;
    private  int indexI;

    private GridPane gridPane;

    public IndexScene(Stage primaryStage, ArrayList<Scenery> sceneries, Player player){
        this.player = player;
        this.title = "Index";
        this.name = this.title;
        this.primaryStage = primaryStage;

        this.gridPane = new GridPane();
        this.indexI = 0;
        for (Scenery each : sceneries){
            this.gridPane.add(this.presetText("Go to scene:\t"), 0, indexI);
            this.gridPane.add(this.presetButton(each.getName(), each), 1, indexI);
            ++indexI;
        }
        this.gridPane.setBackground(new Background(new BackgroundFill(Color.color((double)222/256, (double)174/256, (double)84/256), CornerRadii.EMPTY, Insets.EMPTY)));

        Text youAre = new Text("You Are");
        youAre.setFont(Font.font("Franklin Gothic", FontWeight.BOLD, 200));
        youAre.setFill(Color.color((double)0/256, (double)102/256, (double)255/256));
        youAre.setEffect(new DropShadow());
        this.gridPane.add(youAre, 3, indexI + 1);

        Text OK = new Text("OK");
        OK.setFont(Font.font("Franklin Gothic", FontWeight.THIN, 300));
        OK.setFill(Color.color((double)0/256, (double)102/256, (double)255/256));
//        youAre.setEffect(new DropShadow());
        this.gridPane.add(OK, 4, indexI + 1);




        this.scene = new Scene(this.gridPane);
    }

    private Text presetText(String text){
        Text textReturn = new Text(text);
        textReturn.setFont(Font.font("Verdana", 20));
        return  textReturn;
    }

    private Button presetButton(String text, Scenery setScene){
        Button buttonReturn = new Button(text);
        buttonReturn.setOnAction(event -> {
            this.primaryStage.setScene(setScene.getScene());
            this.primaryStage.setTitle(setScene.getTitle());
            player.stop();
            this.player.setSong(setScene.getSongPath());
        });
        buttonReturn.setStyle("-fx-font: 30 Verdana; -fx-base: #3399ff");
        return buttonReturn;
    }

    public void setNextScene(Scenery nextScene){
        this.nextScene = nextScene;
        this.gridPane.add(presetButton("Check Code!", this.nextScene), 0, this.indexI + 1);
    }

    public String getTitle(){ return this.title; }

    public Scene getScene(){
        return this.scene;
    }

    public String getName(){ return this.name; }

    public String getSongPath(){ return this.songPath; }
}
