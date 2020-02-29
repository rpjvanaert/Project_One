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

    private GridPane gridPane;

    public IndexScene(Stage primaryStage, ArrayList<Scenery> sceneries){
        this.title = "Index";
        this.name = this.title;
        this.primaryStage = primaryStage;

        this.gridPane = new GridPane();
        int indexI = 0;
        for (Scenery each : sceneries){
            this.gridPane.add(this.presetText("Go to scene:\t"), 0, indexI);
            this.gridPane.add(this.presetButton(each.getName(), each), 1, indexI);
            ++indexI;
        }
        this.gridPane.setBackground(new Background(new BackgroundFill(Color.color((double)222/256, (double)174/256, (double)84/256), CornerRadii.EMPTY, Insets.EMPTY)));

        Text youAreOK = new Text("You Are OK");
        youAreOK.setFont(Font.font("Candara", FontWeight.BOLD, 200));
        youAreOK.setFill(Color.color((double)90/256, (double)90/256, (double)255/256));
        youAreOK.setEffect(new DropShadow());
        this.gridPane.add(youAreOK, 3, 4);



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
        });
        buttonReturn.setStyle("-fx-font: 40 Verdana; -fx-base: #3399ff");
        return buttonReturn;
    }

    public void setNextScene(Scenery nextScene){
        this.nextScene = nextScene;
        this.gridPane.add(presetButton("Check Code!", this.nextScene), 0, 4);
    }

    public String getTitle(){ return this.title; }

    public Scene getScene(){
        return this.scene;
    }

    public String getName(){ return this.name; }
}
