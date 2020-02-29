package Sceneries;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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

        this.scene = new Scene(this.gridPane);
    }

    private Text presetText(String text){
        Text textReturn = new Text(text);
        textReturn.setFont(Font.font("Verdana", 20));
        return  textReturn;
    }

    private Label presetLabel(String text){
        Label labelReturn = new Label(text);
        return labelReturn;
    }

    private Button presetButton(String text, Scenery setScene){
        Button buttonReturn = new Button(text);
        buttonReturn.setOnAction(event -> {
            this.primaryStage.setScene(setScene.getScene());
            this.primaryStage.setTitle(setScene.getTitle());
        });
        return buttonReturn;
    }

    public void setNextScene(Scenery nextScene){ this.nextScene = nextScene; }

    public String getTitle(){ return this.title; }

    public Scene getScene(){ return this.scene; }

    public String getName(){ return this.name; }
}
